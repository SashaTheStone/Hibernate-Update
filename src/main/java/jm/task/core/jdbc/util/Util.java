package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {

    private static final String USERNAME = "root";
    private static final String PASS = "root";
    private static final String CONNECT_URL = "jdbc:mysql://localhost:3306/taskJDBC";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connection;

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

}
