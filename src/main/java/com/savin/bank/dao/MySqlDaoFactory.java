package com.savin.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDaoFactory implements DaoFactory {

    private final String sqlDriver;
    private String urlConnection;
    private String dbUserName;
    private String dbPassword;

    {
        sqlDriver = "dsa";

    }

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
