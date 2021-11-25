package com.store.electronic.dao;

import com.store.electronic.entity.Account;
import com.store.electronic.entity.User;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.List;

public class AccountDAO extends EntityDAO<Account> {
    static final String INSERT_DATA
            = "INSERT account(login, password) VALUE(?, ?))";
    static final String DELETE_ACCOUNT = "DELETE FROM account WHERE id = ?";
    static final String FIND_ACCOUNT_BY_ID =
            "SELECT login, password, name, create_time, account.id FROM account WHERE account.id = ?";

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
    public void delete(Account account) throws DaoException {

    }

    @Override
    public List findAll() throws DaoException {
        return null;
    }

    @SneakyThrows
    @Override
    public Account getById(int id) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(FIND_ACCOUNT_BY_ID)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);

                return new Account(userId, login, password);
            }
            return null;
        } catch (SQLException throwables) {
            throw new DaoException();
        }
    }
}
