package com.store.electronic.dao;

import com.store.electronic.entity.Category;
import com.store.electronic.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.store.electronic.utils.JdbcConnect.getConnection;

public class ProductDAO extends EntityDAO<Product> {
    public Product product;
    private static final String INSERT_SQL = "INSERT INTO product" +
            "(CategoryId, name, cost) VALUES(?, ?, ?, ?)";
    private static final String FIND_BY_ID =
            "SELECT id, CategoryId, Name, Description, Cost from product WHERE id = '?';";
    public static final String SELECT_ALL_DATA = "SELECT * FROM product";
    public static final String DELETE_DATA = "DELETE FROM product WHERE id = ?";

    @Override
    Integer create(Product product) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, product.getCost());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(4, product.getCategory().getId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    void delete(Product product) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DATA)) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Failed connection while deleting");
        }
    }
    @Override
    public List<Product> findAll() throws DaoException {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_DATA);
            List<Product> products = new ArrayList<>();
            while (resultSet.next()){
                new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setCategory(new Category(
                        resultSet.getInt("productId"),
                        resultSet.getString("productName")));

                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new DaoException("Failed connection");
        }
    }

    @Override
    public Product getById(int id) throws DaoException {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Category category =
                        new Category(resultSet.getInt("categoryId"),
                                     resultSet.getString("categoryName"));

                new Product();
                product.setCategory(category);
                product.setId(resultSet.getInt("productId"));
                product.setName(resultSet.getString("productName"));
                product.setCost(resultSet.getInt("productCost"));
                return product;
            }

            return null;
        } catch (SQLException throwables) {
            throw new DaoException("failed to get by Id");
        }
    }

    public void update(Product product) {

    }
}
