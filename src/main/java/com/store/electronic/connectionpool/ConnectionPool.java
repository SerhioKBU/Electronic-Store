package com.store.electronic.connectionpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;
import java.util.concurrent.Executor;

public class ConnectionPool implements ConnectionBuilder{

    private static final Logger logger = LoggerFactory.getLogger(ConnectionPool.class);

    private final Stack<Connection> connectionPool = new Stack<>();
    private final Set<Connection> occupiedConnection = new HashSet<>();
    private Properties properties;
    private static final String USER  = "root";
    private static final String PASSWORD  = "7454378";
    private static final String URL = "jdbc:mysql://localhost:3306/electronic-store";
    private final int minPool = 4;
    private final int maxPool = 8;
    private int connNum = 0;

    private static final String SQL_VERIFICATION_CONNECTION = "select 1";

    private ConnectionPool() {
    }

    static ConnectionPool connPool = new ConnectionPool();

    public static ConnectionPool getInstance(){
        return connPool;
    }

    @Override
    public synchronized Connection getConnection() {
        Connection connection = null;
        if (isFull()) {
            try {
                throw new SQLException("The connection pool is full");
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("Fail to get connection", e);
            }
        }

        connection = getConnectionFromPool();
        if (connection == null) {
            connection = createNewConnectionForPool();
        }
        connection = makeAvailable(connection);
        return connection;
    }

    /**
     * @return check the probability to put a connection
     */
    private synchronized boolean isFull(){
        return ((connectionPool.size() == 0) && (connNum >= maxPool));
    }

    /**
     * @return free connection from pool connection add put it in Occupied Connection
     */
    private Connection getConnectionFromPool(){
        Connection connection = null;
        if (connectionPool.size() > 0){
            connection = connectionPool.pop();
            occupiedConnection.add(connection);
        }
        return connection;
    }

    private Connection createNewConnectionForPool() {
        Connection connection = createNewConnection();
        for (int i = 0; i < minPool; i++) {
            connNum++;
            occupiedConnection.add(connection);
        }
        return connection;
    }

    /**
     * @return create new connection
     */
    private Connection createNewConnection() {
        Connection connection = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * @param connection verification
     * @return working connection
     * if connection doesn't work we delete it from the Occupied Connection
     * and recreate new one
     */
    private Connection makeAvailable(Connection connection) {
        if (isConnectionAvailable(connection)) {
            return connection;
        }
        occupiedConnection.remove(connection);
        connNum--;
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connection = createNewConnection();
        occupiedConnection.add(connection);
        connNum++;
        return  connection;
    }

    /**
     * @param connection is working checking
     * @return true if the connection is working
     */
    private boolean isConnectionAvailable(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL_VERIFICATION_CONNECTION);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param connection returned from Occupied Connection on free place in Connection Pool
     */
    public synchronized void returnConnection(Connection connection) {
        if (connection == null) {
            throw new NullPointerException();
        }

        if (!occupiedConnection.remove(connection)) {
            try {
                throw new SQLException("The connection returned already or isn't for this pool");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connectionPool.push(connection);
    }


    public class ConnectionWrapper implements Connection {

        private Connection connection;

        public ConnectionWrapper(Connection connection) {
            this.connection = connection;
        }

        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        @Override
        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return connection.prepareStatement(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }

        @Override
        public void close() throws SQLException {
            connectionPool.add(this);
        }

        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return getMetaData();
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        @Override
        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }

        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            connection.setTypeMap(map);
        }

        @Override
        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }

        @Override
        public void rollback(Savepoint savepoint) throws SQLException {
            connection.rollback(savepoint);
        }

        @Override
        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            connection.releaseSavepoint(savepoint);
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        @Override
        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }

        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        @Override
        public void setClientInfo(Properties properties) throws SQLClientInfoException {
            connection.setClientInfo(properties);
        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        @Override
        public void setSchema(String schema) throws SQLException {
            connection.setSchema(schema);
        }

        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        @Override
        public void abort(Executor executor) throws SQLException {
            connection.abort(executor);
        }

        @Override
        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
            connection.setNetworkTimeout(executor, milliseconds);
        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
        }
    }
}

