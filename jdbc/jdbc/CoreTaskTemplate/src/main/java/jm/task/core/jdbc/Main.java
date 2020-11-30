package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь

        UserServiceImpl userService = new UserServiceImpl();


        userService.createUsersTable();
        userService.saveUser ("Henry", "Cavill", (byte) 37);
        userService.saveUser ("Cris", "Evans", (byte) 39);
        userService.saveUser ("Gal", "Gadot", (byte) 35);
        userService.saveUser ("Scarlett", "Johansson", (byte) 35);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
