package com.savin.bank.dao;

import com.savin.bank.dao.Impl.DaoFactoryImpl;
import com.savin.bank.src.Account;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class DaoFactoryImplTest {

    @Test
    public void testGetConnection() throws Exception {
//        DaoFactoryImpl daoFactory = new DaoFactoryImpl();
//        assertNotEquals(daoFactory.getConnection(), null);
    }

    @Test
    public void testGetClientDao() throws Exception {
        DaoFactory daoFactory = new DaoFactoryImpl();
        Connection connection = daoFactory.getConnection();
        GenericDao<Account, Integer> mySqlAccountDao = daoFactory.getClientDao(connection);
        assertNotEquals(mySqlAccountDao, null);
    }

    @Test
    public void testGetCurrencyDao() throws Exception {

    }

    @Test
    public void testGetAccountDao() throws Exception {

    }
}