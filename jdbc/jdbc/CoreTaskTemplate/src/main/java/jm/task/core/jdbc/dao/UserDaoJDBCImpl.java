package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    private Connection connection = getConnection();

    public void createUsersTable() throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS users (	" +
                "id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,	" +
                "name VARCHAR(64) NOT NULL,	" +
                "lastName VARCHAR(64) NOT NULL ,	" +
                "age TINYINT NOT NULL " +
                ");";

        connection.setAutoCommit(false);
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void dropUsersTable() throws SQLException {

        String sql = "DROP TABLE if exists users;";

        connection.setAutoCommit(false);
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {

        String sql = "INSERT INTO users (NAME, LASTNAME, AGE) VALUES(?, ?, ?);";

        connection.setAutoCommit(false);
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void removeUserById(long id) throws SQLException {

        String sql = "DELETE FROM users WHERE ID=?;";

        connection.setAutoCommit(false);
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public List<User> getAllUsers() throws SQLException {

        List<User> userList = new ArrayList<>();

        String sql = "SELECT NAME, LASTNAME, AGE FROM users;";

        connection.setAutoCommit(false);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));

                userList.add(user);
                System.out.println("User с именем " + user.getName() + " добавлен в базу");
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {

        String sql = "TRUNCATE TABLE users;";

        connection.setAutoCommit(false);
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }

    }
}
