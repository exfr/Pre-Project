package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserServiceImpl implements UserService {
    //    private static UserDaoJDBCImpl getUserDao() {
    //        return new UserDaoJDBCImpl(Util.getMysqlConnection());
    //    }
    private UserDaoHibernateImpl userDaoHibernate = UserDaoHibernateImpl.getInstance(Util.getSessionFactory());
    private static UserServiceImpl userService;

    public UserServiceImpl() {}


    public static UserServiceImpl getInstance() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }
    @Override
    public void createUsersTable() {
        userDaoHibernate.createUsersTable();
        //        getUserDao().createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable();
        //        getUserDao().dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernate.saveUser(name, lastName, age);
        //        getUserDao().saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        userDaoHibernate.removeUserById(id);
        //        getUserDao().removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDaoHibernate.getAllUsers();
        //        return getUserDao().getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userDaoHibernate.cleanUsersTable();
        //        getUserDao().cleanUsersTable();
    }
}
