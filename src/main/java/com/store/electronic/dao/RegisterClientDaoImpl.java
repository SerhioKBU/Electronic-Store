package com.store.electronic.dao;

import com.store.electronic.entity.User;

import java.sql.*;

public class RegisterClientDaoImpl implements RegisterClientDao{
    private static final String ADD_ACCOUNT =
            "INSERT account(login, password, email) VALUE(?, ?, ?)";
    private static final String ADD_USER =
            "INSERT into users(accountId, UserName, Password, Email) VALUES (?, ?, ?, ?)";

    public boolean create(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean flag = false;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(ADD_ACCOUNT, Statement.RETURN_GENERATED_KEYS);

            int i = 1;
            preparedStatement.setString(i++, user.getAccount().getLogin());
            preparedStatement.setString(i++, user.getAccount().getPassword());
            preparedStatement.setString(i++, user.getAccount().getEmail());

            if (preparedStatement.executeUpdate() > 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    user.getAccount().setId((int) resultSet.getLong(1));
                }
            }

            preparedStatement = connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS);

            i = 1;
            preparedStatement.setInt(i++, user.getAccount().getId());
            preparedStatement.setString(i++, user.getUserName());
            preparedStatement.setString(i++, user.getPassword());
            preparedStatement.setString(i++, user.getEmail());

            if (preparedStatement.executeUpdate() > 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    user.setId((int) resultSet.getLong(1));
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
