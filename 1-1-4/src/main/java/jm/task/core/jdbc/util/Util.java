package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

//import java.lang.reflect.InvocationTargetException;
//import java.sql.Connection;
//import java.sql.Driver;
//import java.sql.DriverManager;
//import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

//    public static Connection getMysqlConnection() {
//        try {
//            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance());
//
//            StringBuilder url = new StringBuilder();
//
//            url.
//                    append("jdbc:mysql://").        //db type
//                    append("localhost:").           //host name
//                    append("3306/").                //port
//                    append("db_example?").          //db name
//                    append("user=root&").          //login
//                    append("password=root").       //password
//                    append("&serverTimezone=UTC");   //setup server time
//
//            return DriverManager.getConnection(url.toString());
//        } catch (SQLException | InstantiationException | IllegalAccessException
//                | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
//            e.printStackTrace();
//            throw new IllegalStateException();
//        }
//    }

    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example?"
                + "verifyServerCertificate=false"
                + "&serverTimezone=UTC"
                + "&useSSL=false"
                + "&requireSSL=false"
                + "&useLegacyDatetimeCode=false"
                + "&amp");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        return configuration;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


}
