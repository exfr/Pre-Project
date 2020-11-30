package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() throws HibernateException {

        Session session = buildSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS users (	" +
                "id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,	" +
                "name VARCHAR(64) NOT NULL,	" +
                "lastName VARCHAR(64) NOT NULL ,	" +
                "age TINYINT NOT NULL " +
                ");";
        try {
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            t.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() throws HibernateException {

        Session session = buildSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "DROP TABLE if exists users;";
        try {
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            t.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws HibernateException {

        Session session = buildSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            User user = new User(name, lastName, age);
            session.save(user);
            t.commit();
            System.out.println("User с именем " + user.getName() + " добавлен в базу");
        } catch (Exception e) {
            e.printStackTrace();
            t.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) throws HibernateException {

        Session session = buildSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "DELETE FROM users WHERE ID=?;";
        try {
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.setParameter(0, id);
            query.executeUpdate();
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            t.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() throws HibernateException {

        Session session = buildSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "SELECT * FROM users";
        List<User> userList = null;
        try {
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            userList = query.list();
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            t.rollback();
        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {

        Session session = buildSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "TRUNCATE TABLE users;";
        try {
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            t.rollback();
        } finally {
            session.close();
        }
    }
}
