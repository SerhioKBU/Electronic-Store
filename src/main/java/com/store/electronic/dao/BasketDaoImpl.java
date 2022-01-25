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
    private static final String INSERT_BASKET_SQL = "INSERT INTO Basket(userId, categoryId) VALUES(?, ?)";
    private static final String DELETE_BASKET_SQL = "DELETE FROM Basket WHERE userId = ?";
    private static final String FIND_BY_ID = "SELECT basket.id, category.id, category.categoryName " +
            "FROM basket JOIN users ON users.id = basket.userId " +
            "+ JOIN category ON category.id = basket.categoryId WHERE users.id = (?)";


//            "select " +
//            "basket.id as basketId, " +
//            "c.id       as categoryId, " +
//            "c.name     as categoryName " +
//            "FROM basket basket " +
//            " INNER JOIN users u on u.id = basket.userId " +
//            " INNER JOIN category c on c.id = basket.categoryId " +
//            " where u.id = ?";

    @Override
    public Basket insertOrUpdate(Basket basket) throws DaoException {
        delete(basket);
        try {
            for (Category category : basket.getCategories()) {
                try (Connection connection = getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BASKET_SQL)) {
                    preparedStatement.setInt(1, basket.getUser().getId());
                    preparedStatement.setInt(2, category.getId());
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
            System.out.println("--userId--> " + user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            Basket basket = new Basket(null, null, null);
            ArrayList<Category> categoryList = new ArrayList<>();

            while (resultSet.next()) {
                int basketId = resultSet.getInt("id");
                basket.setId(basketId);
                System.out.println("BASKET ID= " + basketId);

                int categoryId = resultSet.getInt("id");
                String categoryName = resultSet.getString("categoryName");

                System.out.println("CatId= " + categoryId + "CatName= " + categoryName);
                Category category = new Category(categoryId, categoryName);
                System.out.println("CATEGORY: " + category);

//                int productId = resultSet.getInt("productId");
//                String productName = resultSet.getString("productName");
//                int productPrice = resultSet.getInt("productPrice");
//                int quantity = resultSet.getInt("quantity");

               // Product product = new Product(productId, productName, productPrice, category, quantity);
                categoryList.add(category);
            }
            basket.setCategories(categoryList);
            basket.setUser(user);

            if (basket.getId() == null) {
                System.out.println(basket.getId());
                return null;
            }

            return basket;
        } catch (SQLException  e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }
}
