package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {

    private static final String USERNAME = "root";
    private static final String PASS = "root";
    private static final String CONNECT_URL = "jdbc:mysql://localhost:3306/taskJDBC";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connection;
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, DRIVER);
            properties.put(Environment.URL, CONNECT_URL);
            properties.put(Environment.USER, USERNAME);
            properties.put(Environment.PASS, PASS);
//            properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            properties.put(Environment.SHOW_SQL, "true");
//            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//            properties.put(Environment.HBM2DDL_AUTO, "create-drop");

            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e){
            e.printStackTrace();
        }
    }

    static {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(CONNECT_URL, USERNAME, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static void closeSessionFactory(){
        sessionFactory.close();
    }

}
