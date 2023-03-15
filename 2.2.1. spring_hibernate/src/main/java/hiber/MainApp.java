package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

     User user1 = new User("Брюс", "Уэйн", "user1@mail.ru");
     User user2 = new User("Спидди", "Гонщик", "user2@mail.ru");
     User user3 = new User("Барон", "Мюнхгаузен", "user3@mail.ru");
     User user4 = new User("Стьюи","Гриффин","user4@mail.ru");

     Car car1 = new Car("Бэтмобиль", 6548);
     Car car2 = new Car("N5",5555);
     Car car3 = new Car("Пушечное Ядро",123456789);

     userService.add(user1.setCar(car1));
     userService.add(user2.setCar(car2));
     userService.add(user3.setCar(car3));
     userService.add(user4);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car : "+user.getCar());
         System.out.println();
      }

      try {
          System.out.println(userService.getUsedCar("N5", 5555));
      } catch (NoResultException e) {
          System.out.println("User not found");
      }

      context.close();

   }
}
