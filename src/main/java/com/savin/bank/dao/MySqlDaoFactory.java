package com.savin.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDaoFactory implements DaoFactory {

    private String sqlDriver = "com.mysql.jdbc.Driver";
    private String urlConnection = "jdbc:mysql://localhost:3306/bank";
    private String dbUserName = "root";
    private String dbPassword = "1234";

    //todo : Ask mentor for this next cases: 1) Do I have to handle exception inside method or not; 2) What to do in case of unsuccessful connection
    @Override
    public Connection getConnection() {
        try {
            Class <?> cl = Class.forName(sqlDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(urlConnection, dbUserName, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return connection;
        }
    }

    @Override
    public GenericDao getClientDao(Connection connection) {
        return new MySqlClientDao(connection);
    }

    @Override
    public GenericDao getCurrencyDao(Connection connection) {
        return new MySqlCurrencyDao(connection);
    }

    @Override
    public GenericDao getAccountDao(Connection connection) {
        return new MySqlAccountDao(connection);
    }
}
