package com.store.electronic.dao;

import com.store.electronic.entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends EntityDAO<Category> {

    private static final String INSERT_SQL = "Insert Into category(categoryName) Values(?)";
    private static final String SELECT_ALL = "Select id, categoryName from category";
    public static final String DELETE_DATA = "Delete from category Where id = (?)";
    public static final String SELECT_ALL_DATA = "SELECT * FROM category";
    private static final String SELECT_BY_ID = "SELECT id, categoryName FROM category WHERE id = (?)";

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
                String name = resultSet.getString("categoryName");
                Category category = new Category(id, name);

                categories.add(category);
            }
        } catch (SQLException throwables) {
            throw new DaoException("Failed to find all");
        }
        return categories;
    }

    @Override
    public Category getById(int id) throws DaoException {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("categoryName"));
                return category;
            }

            return null;
        } catch (SQLException throwables) {
            throw new DaoException("Failed to get by ID");
        }
    }
}