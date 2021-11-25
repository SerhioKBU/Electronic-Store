package com.store.electronic.dao;

import com.store.electronic.connectionpool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BasketDAO<T> {
    T insertOrUpdate(T type) throws DaoException;
    void delete(T type) throws DaoException;
    List<T> findAll() throws DaoException;
    T getById(int id) throws DaoException;

    ConnectionPool connectionPool = ConnectionPool.getInstance();;

    default Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = connectionPool.getConnection();
        return connection;
    }
}
