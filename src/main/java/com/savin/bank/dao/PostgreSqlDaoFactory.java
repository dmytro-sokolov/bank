package com.savin.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSqlDaoFactory implements DaoFactory {

    private String sqlDriver = "org.postgresql.Driver";
    private String urlConnection = "jdbc:postgresql://localhost:5432/bank";
    private String dbUserName = "postgres";
    private String dbPassword = "123";

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
        return new PostgreSqlStudentDao(connection);
    }
}
