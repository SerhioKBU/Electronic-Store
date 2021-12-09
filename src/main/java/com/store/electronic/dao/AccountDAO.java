package com.store.electronic.dao;

import com.store.electronic.entity.Account;
import com.store.electronic.entity.Role;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.List;

public class AccountDAO extends EntityDAO<Account> {
    static final String INSERT_DATA
            = "INSERT account(login) VALUE(?))";
    static final String DELETE_ACCOUNT = "DELETE FROM account WHERE id = (?)";
    static final String FIND_ACCOUNT_BY_ID =
            "SELECT login, password, name, create_time FROM account WHERE id = (?)";
    private static final String FIND_ACCOUNT_BY_LOGIN =
            "SELECT id, login, password, create_time FROM account WHERE login = (?)";

    /**
     * Insert account's data in database
     * @param account
     * @return
     * @throws DaoException
     */
    @Override
    public Integer create(Account account) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_DATA, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, account.getId());
            preparedStatement.setString(2, account.getLogin());
            preparedStatement.setString(3, account.getPassword());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public Account getById(int id) throws DaoException {
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement
//                     = connection.prepareStatement(FIND_ACCOUNT_BY_ID)
//        ) {
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                int userId = resultSet.getInt(1);
//                String login = resultSet.getString(2);
//                String password = resultSet.getString(3);
//                Role role = (resultSet.getString(4));
//
//                return new Account(userId, login, password, role);
//            }
//            return null;
//        } catch (SQLException throwables) {
//            throw new DaoException();
//        }
        return null;
    }

    public boolean find(String login) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Account resultAccount = null;
        boolean flag = false;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_ACCOUNT_BY_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                resultAccount = new Account().setRole(new Role());
                resultAccount.setLogin(resultSet.getString("login"));
                resultAccount.setPassword(resultSet.getString("password"));
                resultAccount.setCreateTime(Timestamp.valueOf(resultSet.getString("create_time")).toLocalDateTime());
                resultAccount.setId(resultSet.getInt("account.id"));
                flag = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Failed to connect table account");
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return flag;
    }

    @Override
    public void delete(Account account) throws DaoException {

    }

    @Override
    public List findAll() throws DaoException {
        return null;
    }


}
