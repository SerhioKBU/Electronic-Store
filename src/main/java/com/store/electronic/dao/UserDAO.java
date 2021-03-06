package com.store.electronic.dao;

import com.store.electronic.entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends EntityDAO<User> {
    public static final String INSERT_DATA = "INSERT into users(username, email) VALUES (?, ?)";
    public static final String SELECT_FIND_BY_NAME  = "SELECT id, username, email  FROM users WHERE username = (?)";
    public static final String SELECT_FIND_BY_ID = "SELECT id, username, email FROM users WHERE id = ?";
    public static final String SELECT_ALL_DATA = "SELECT * FROM users";
    public static final String DELETE_DATA = "DELETE FROM users WHERE id = ?";

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
                String email = resultSet.getString(3);
                return new User(id, usernameField, email);

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
//                user.setId(resultSet.getInt(1));
//                user.setUserName(resultSet.getString(2));
//                user.setEmail(resultSet.getString(3));
                int userId = resultSet.getInt(1);
                String userName = resultSet.getString(2);
                String email = resultSet.getString(3);

                return new User(userId, userName, email);
            }

//            new User();
//            user.setId(resultSet.getInt(1));
//            user.getAccount().setId(resultSet.getInt(2));
//            user.setUserName(resultSet.getString(3));
//            user.setEmail(resultSet.getString(4));
//            return user;

            return null;
        } catch (SQLException throwables) {
            throw new DaoException();
        }
    }

    @Override
    Integer create(User user) throws  DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_DATA, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getEmail());

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
