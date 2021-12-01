package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = Util.getConnection();


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(" +
                    "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " name VARCHAR (50), lastName VARCHAR(50), age TINYINT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")){
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.printf("User с именем - %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM users WHERE id = ?")){
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")){
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
