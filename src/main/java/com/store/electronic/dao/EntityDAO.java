package com.store.electronic.dao;

import com.store.electronic.connectionpool.ConnectionPool;
import com.store.electronic.dao.DaoException;
import com.store.electronic.entity.Category;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

abstract public class EntityDAO<T>{

    abstract Integer create(T type) throws DaoException;
    abstract void delete(T type) throws DaoException;
    abstract List<T> findAll() throws DaoException;
    abstract T getById(int id) throws DaoException;

    void close(AutoCloseable autoCloseable){
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalStateException("Cannot close " + autoCloseable);
            }
        }
    }

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();;

    Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = connectionPool.getConnection();
        return connection;
    }
}
