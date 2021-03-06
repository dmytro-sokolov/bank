package com.savin.bank.dao;


import java.sql.Connection;

public interface DaoFactory {
    Connection getConnection();
    GenericDao getClientDao(Connection connection);
    GenericDao getCurrencyDao(Connection connection);
    GenericDao getAccountDao(Connection connection);
}