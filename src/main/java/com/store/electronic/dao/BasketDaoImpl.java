package com.store.electronic.dao;

import com.store.electronic.entity.Basket;
import com.store.electronic.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.store.electronic.utils.JdbcConnect.getConnection;

public class BasketDaoImpl implements BasketDAO<Basket>{
    private static final String INSERT_BASKET_SQL = "INSERT INTO Basket(UserId, ProductId) VALUES(?, ?)";
    private static final String DELETE_BASKET_SQL = "DELETE FROM Basket WHERE userId = ?";

    @Override
    public Basket insertOrUpdate(Basket basket) throws DaoException {
        delete(basket);
        try {
            for (Product product : basket.getProducts()) {
                try (Connection connection = getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BASKET_SQL)) {
                    preparedStatement.setInt(1, basket.getUser().getId());
                    preparedStatement.setInt(2, product.getId());
                    preparedStatement.execute();
                }
            }
            return basket;
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public void delete(Basket basket) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BASKET_SQL)) {
            preparedStatement.setInt(1, basket.getUser().getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public List findAll() throws DaoException {
        return null;
    }

    @Override
    public Basket getById(int id) throws DaoException {
        return null;
    }
}
