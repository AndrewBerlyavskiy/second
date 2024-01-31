package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import static jm.task.core.jdbc.util.Util.*;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;
    private final Statement statement;
    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
        try {
            this.statement = (Statement) connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create statement");
        }
    }

    public void createUsersTable() {
        String creation = "CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(150), lastName VARCHAR(150), age TINYINT)";
        try {
            statement.executeUpdate(creation);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS users";
        try {
            statement.executeUpdate(drop);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insert = "INSERT INTO users(name, lastName, age) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + "добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void removeUserById(long id) {
        String removal = "DELETE FROM users WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(removal);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<User> getAllUsers() {
        String getAll = "SELECT * FROM users";
        ArrayList<User> userList = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(getAll);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                Byte age = resultSet.getByte("age");
                User obj = new User();
                obj.setId(id);
                obj.setName(name);
                obj.setLastName(lastName);
                obj.setAge(age);
                userList.add(obj);
                System.out.println(userList);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String clean = "TRUNCATE TABLE users";
        try {
            statement.executeUpdate(clean);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
}
