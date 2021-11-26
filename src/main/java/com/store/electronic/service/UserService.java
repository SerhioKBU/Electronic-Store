package com.store.electronic.service;

import com.store.electronic.connectionpool.ConnectionPool;
import com.store.electronic.dao.DaoException;
import com.store.electronic.dao.ProductDAO;
import com.store.electronic.dao.UserDAO;
import com.store.electronic.entity.Product;
import com.store.electronic.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService implements Service{

    private final UserDAO userDao = new UserDAO();
    private final ProductDAO productDAO = new ProductDAO();
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public User findByUserName(String username) {
        try {
            return userDao.findByUsername(username);
        } catch (DaoException e) {
            e.printStackTrace();
            System.out.println("Failed to find");
            return null;
        }
    }

    @Override
    public List findAll() throws ServiceException {
        return null;
    }

    @Override
    public User findById(Integer id) {
        try {
            return userDao.getById(id);
        } catch (DaoException e) {
            System.out.println("Failed to find");
            return null;
        }
    }

    @Override
    public void buy(User user, Product product) {
        Connection connection = null;
        connection = connectionPool.getConnection();
        try {

            user.setMoney(user.getMoney() - product.getCost());
            product.setQuantity(product.getQuantity() - 1);
            productDAO.update(product);
            userDao.update(user);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Failed to save");
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
