package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    private static SessionFactory sessionFactory;

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/users?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "UserPass";

    public static SessionFactory buildSessionFactory() {
        if (sessionFactory == null) {
            try {

                Properties props = new Properties();
                props.setProperty("hibernate.connection.driver_class", DB_DRIVER);
                props.setProperty("hibernate.connection.url", DB_URL);
                props.setProperty("hibernate.connection.username", DB_USERNAME);
                props.setProperty("hibernate.connection.password", DB_PASSWORD);
                props.setProperty("hibernate.show_sql", "true");
                props.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
                props.setProperty("hibernate.hbm2ddl.auto", "update");


                Configuration configuration = new Configuration();
                configuration.setProperties(props);
                //configuration.getProperties();
                configuration.addAnnotatedClass(User.class);

                StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());

                sessionFactory = configuration.buildSessionFactory(serviceRegistryBuilder.build());

                System.out.println("Hibernate Java Config serviceRegistry created");

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Session creation failed " + e);
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        sessionFactory.close();
    }



    //private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    /*private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/users?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "UserPass";

    public Connection getConnection() {
        Connection connection = null;
        try {
           // Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection successful");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }*/
}
