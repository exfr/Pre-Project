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

        userService.add(new User(
                "User1", "LastName1", "user1@mail.ru",
                new Car("Car1", 111)));
        userService.add(new User(
                "User2", "LastName2", "user2@mail.ru",
                new Car("Car2", 222)));
        userService.add(new User(
                "User3", "LastName3", "user3@mail.ru",
                new Car("Car3", 333)));
        userService.add(new User(
                "User4", "LastName4", "user4@mail.ru",
                new Car("Car4", 444)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar().getName());
            System.out.println("Car series = " + user.getCar().getSeries());
            System.out.println();
        }

        System.out.println(userService.getUserByCar(new Car("Car1", 111)).toString());

        context.close();
    }
}

/*
Задание:
1. Создайте соединение к своей базе данных и схему. Запустите приложение. Проверьте, что оно полностью работает.
2. Создайте сущность Car с полями String model и int series, на которую будет ссылаться User с помощью связи one-to-one.
3. Добавьте этот класс в настройки hibernate.
4. Создайте несколько пользователей с машинами, добавьте их в базу данных, вытащите обратно.
5. В сервис добавьте метод, который с помощью hql-запроса будет доставать юзера, владеющего машиной по ее модели и серии.

 */