package com.store.electronic.dao;
import com.store.electronic.connectionpool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BasketDAO<T> {
    T insertOrUpdate(T type) throws DaoException;
}
