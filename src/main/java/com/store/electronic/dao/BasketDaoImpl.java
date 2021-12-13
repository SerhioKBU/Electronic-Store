package com.store.electronic.dao;

import com.store.electronic.entity.Basket;
import com.store.electronic.entity.Category;
import com.store.electronic.entity.Product;
import com.store.electronic.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasketDaoImpl implements BasketDAO<Basket>, BaseDAO{
    private static final String INSERT_BASKET_SQL = "INSERT INTO Basket(UserId, ProductId) VALUES(?, ?)";
    private static final String DELETE_BASKET_SQL = "DELETE FROM Basket WHERE userId = ?";
    private static final String FIND_BY_ID = "select " +
            "basket.id as basketId, " +
            "p.id       as productId, " +
            "p.name     as productName, " +
            "p.price    as productPrice, " +
            "c.id       as categoryId, " +
            "c.name     as categoryName " +
            "FROM basket basket " +
            " INNER JOIN users u on u.id = basket.userId " +
            " INNER JOIN product p on p.id = basket.productId " +
            " INNER JOIN category c on c.id = c.categoryid    " +
            " where u.id = ?";

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

    public void delete(Basket basket) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BASKET_SQL)) {
            preparedStatement.setInt(1, basket.getUser().getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    public Basket findById(User user) throws DaoException {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            Basket basket = new Basket(null, null, null);
            ArrayList<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                int basketId = resultSet.getInt("basketId");
                basket.setId(basketId);

                int catId = resultSet.getInt("catId");
                String catName = resultSet.getString("catName");
                Category category = new Category(catId, catName);

                int productId = resultSet.getInt("productId");
                String productName = resultSet.getString("productName");
                int productPrice = resultSet.getInt("productPrice");
                int quantity = resultSet.getInt("quantity");

                Product product = new Product(productId, productName, productPrice, category, quantity);
                products.add(product);
            }
            basket.setProducts(products);
            basket.setUser(user);

            if (basket.getId() == null) {
                return null;
            }

            return basket;
        } catch (SQLException  e) {
            throw new DaoException();
        }
    }
}
