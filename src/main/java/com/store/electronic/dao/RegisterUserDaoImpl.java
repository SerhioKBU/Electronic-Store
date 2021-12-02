package com.store.electronic.dao;

import com.store.electronic.entity.User;

import java.sql.*;

public class RegisterUserDaoImpl implements BaseDAO {
    private static final String ADD_ACCOUNT =
            "INSERT into account(login, password) VALUE (?, ?)";
    private static final String ADD_USER =
            "INSERT into users(accountId, UserName, Email) VALUES (?, ?, ?)";

    public boolean create(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean flag = false;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(ADD_ACCOUNT, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getAccount().getLogin());
            preparedStatement.setString(2, user.getAccount().getPassword());

            if (preparedStatement.executeUpdate() > 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    user.getAccount().setId( resultSet.getInt(1));
                }
            }

            preparedStatement = connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, user.getAccount().getId());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getEmail());

            if (preparedStatement.executeUpdate() > 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(1));
                    flag = true;
                }
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                assert connection != null;
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            throw new DaoException("Failed to connect table account");
        } finally {
            close(resultSet);
            close(preparedStatement);
            closeConnectionWithCommitTrue(connection);
        }
        return flag;
    }
}
