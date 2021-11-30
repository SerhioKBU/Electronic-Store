package com.store.electronic.dao;

import com.store.electronic.entity.User;
import com.store.electronic.utils.JdbcConnect;
import lombok.SneakyThrows;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static com.store.electronic.utils.JdbcConnect.getConnection;

public class UserDAO extends EntityDAO<User> {
    public static final String INSERT_DATA = "INSERT into user(UserName, Password, Email) VALUES (?, ?)";
    public static final String SELECT_FIND_BY_NAME  = "SELECT id, username, password, email  FROM users WHERE username = (?)";
    public static final String SELECT_FIND_BY_ID = "SELECT id, username, password, email FROM users WHERE id = ?";
    public static final String SELECT_ALL_DATA = "SELECT * FROM users";
    public static final String DELETE_DATA = "DELETE FROM user WHERE id = ?";

    public User user;




    public User findByUsername(String username) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_FIND_BY_NAME)
        ) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String usernameField = resultSet.getString(2);
                String password = resultSet.getString(3);
                String email = resultSet.getString(4);
                return new User(id, usernameField, password, email);

            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DaoException();
        }
    }

    @Override
    public User getById(int id) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(SELECT_FIND_BY_ID)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String userName = resultSet.getString(2);
                String password = resultSet.getString(3);
                String email = resultSet.getString(4);
                return new User(userId, userName, password, email);
            }

            return null;
        } catch (SQLException throwables) {
            throw new DaoException();
        }
    }

    @SneakyThrows
    @Override
    Integer create(User user) throws  DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_DATA, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(4, user.getEmail());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    void delete(User type) throws DaoException {
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DATA)) {
                preparedStatement.setInt(1, user.getId());
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new DaoException("Failed connection while deleting");
            }
    }

    @Override
    List<User> findAll() throws DaoException {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_DATA);
            List<User> users = new ArrayList<>();
            while (resultSet.next()){
                new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new DaoException("Failed connection");
        }
    }

    public void update(User user) {
        
    }

}
