package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      Car car1 = new Car("bmw", 34);
      user1.setCar(car1);
      userService.add(user1);

      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      Car car3 = new Car("Tesla", 3);
      user3.setCar(car3);
      userService.add(user3);

      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      List<User> users = userService.listUsersByCar("bmw", 34);

      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar().toString());
         System.out.println();
      }

      context.close();
   }
}
