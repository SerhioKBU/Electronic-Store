package com.store.electronic.dao;

import com.store.electronic.dto.LoginPageDto;

import java.sql.*;

public class LoginPageDtoDAO implements BaseDAO {
    private static final String FIND_ACCOUNT_USER_DATA =
            "SELECT account.login, account.password, users.userName, users.email " +
                    "FROM account JOIN users ON account.id = users.accountid WHERE account.login = (?)";


    private static final String SQL_FIND_ACCOUNT_BY_LOGIN =
            "SELECT login, password, name, create_time, account.id, role.id " +
                    "FROM account JOIN role ON role.id = account.role_id WHERE account.login = ?";

    public LoginPageDto assemble(String login) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        LoginPageDto loginPageDtoResult = null;

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
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Failed to connect table account");
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return loginPageDtoResult;
    }
}
