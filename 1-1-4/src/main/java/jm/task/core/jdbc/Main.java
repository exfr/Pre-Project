package jm.task.core.jdbc;

//import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
//        реализуйте алгоритм здесь
//        UserServiceImpl userService = new UserServiceImpl();
        UserServiceImpl userService = UserServiceImpl.getInstance();
        userService.createUsersTable();
        userService.saveUser("1", "qw", (byte) 9);
        userService.saveUser("2", "er", (byte) 8);
        userService.saveUser("3", "ty", (byte) 7);
        userService.saveUser("4", "yu", (byte) 6);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
