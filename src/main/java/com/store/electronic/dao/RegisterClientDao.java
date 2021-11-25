package com.store.electronic.dao;

import com.store.electronic.connectionpool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public interface RegisterClientDao {
    default void close(AutoCloseable autoCloseable){
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalStateException("Cannot close " + autoCloseable);
            }
        }
    }

    default void closeConnectionWithCommitTrue(Connection connection){
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalStateException("Cannot close " + connection);
            }
        }
    }

    ConnectionPool connectionPool = ConnectionPool.getInstance();;

    default Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = connectionPool.getConnection();
        return connection;
    }
}
