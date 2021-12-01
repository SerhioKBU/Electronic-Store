package com.store.electronic.dao;

import com.store.electronic.entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.store.electronic.utils.JdbcConnect.getConnection;

public class CategoryDAO extends EntityDAO<Category> {

    private static final String INSERT_SQL = "Insert Into category(name) Values(?)";
    private static final String SELECT_ALL = "Select id, name from category";
    public static final String DELETE_DATA = "Delete from category Where id = (?)";
    public static final String SELECT_ALL_DATA = "SELECT * FROM product";

    @Override
    public Integer create(Category category) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            return preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException();
        }
    }

    @Override
    void delete(Category type) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DATA)) {
            preparedStatement.setInt(1, type.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Failed connection while deleting");
        }
    }

    @Override
    public List<Category> findAll() throws DaoException {
        List<Category> categories = new ArrayList<>();
        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_ALL)
        ) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Category category = new Category(id, name);

                categories.add(category);
            }
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return categories;
    }

    @Override
    public Category getById(int id) throws DaoException {
        return null;
    }
}