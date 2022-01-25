package com.store.electronic.dao;

import com.store.electronic.dto.LoginPageDto;

import java.sql.*;

public class LoginPageDtoDAO implements BaseDAO {
    private static final String FIND_ACCOUNT_USER_DATA =
            "SELECT account.login, account.password, account.create_time, " +
                    "users.userName, users.email " +
                    "FROM account JOIN users ON account.id = users.accountid WHERE account.login = (?)";

    public LoginPageDto assemble(String login) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        LoginPageDto loginPageDtoResult = null;
        boolean flag = false;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(FIND_ACCOUNT_USER_DATA, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                loginPageDtoResult = new LoginPageDto();
                loginPageDtoResult.setLogin(resultSet.getString("account.login"));
                loginPageDtoResult.setPassword(resultSet.getString("account.password"));
                loginPageDtoResult.setUserName(resultSet.getString("users.userName"));
                loginPageDtoResult.setEmail(resultSet.getString("users.email"));
                loginPageDtoResult.setCreate_time(Timestamp.valueOf(resultSet.getString("create_time")).toLocalDateTime());
                flag = true;
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
        return loginPageDtoResult;
    }
}
