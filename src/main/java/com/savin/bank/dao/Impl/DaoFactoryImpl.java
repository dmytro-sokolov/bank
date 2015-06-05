package com.savin.bank.dao.Impl;

import com.savin.bank.dao.DaoFactory;
import com.savin.bank.dao.GenericDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactoryImpl implements DaoFactory {

    private static final String sqlDriver = "com.mysql.jdbc.Driver";
    private static final String urlConnection = "jdbc:mysql://localhost:3306/bank";
    private static final String dbUserName = "root";
    private static final String dbPassword = "1234";

//    static {
//        try {
//            File fXmlFile = new File("src\\main\\resources\\databaseConfig.xml");
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(fXmlFile);
//            doc.getDocumentElement().normalize();
//            NodeList nodeList = doc.getElementsByTagName("mysql");
//            Element element = (Element) nodeList.item(0);
//            sqlDriver = element.getElementsByTagName("driver").item(0).getTextContent();
//            urlConnection = element.getElementsByTagName("connection").item(0).getTextContent();
//            dbUserName = element.getElementsByTagName("login").item(0).getTextContent();
//            dbPassword = element.getElementsByTagName("password").item(0).getTextContent();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Could not init class",e);
//        }
//    }

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
        return new ClientDAOImpl(connection);
    }

    @Override
    public GenericDao getCurrencyDao(Connection connection) {
        return new CurrencyDAOImpl(connection);
    }

    @Override
    public GenericDao getAccountDao(Connection connection) {
        return new AccountDAOImpl(connection);
    }
}
