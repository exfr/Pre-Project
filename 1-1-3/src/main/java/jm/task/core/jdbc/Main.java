package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("1", "qw", (byte) 3);
        userService.saveUser("2", "er", (byte) 4);
        userService.saveUser("3", "ty", (byte) 5);
        userService.saveUser("4", "ui", (byte) 9);

        userService.getAllUsers();
        userService.dropUsersTable();
    }
}
