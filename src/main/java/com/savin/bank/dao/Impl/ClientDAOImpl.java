package com.savin.bank.dao.Impl;


import com.savin.bank.dao.AbstractJDBCDao;
import com.savin.bank.dao.DaoFactory;
import com.savin.bank.dao.GenericDao;
import com.savin.bank.dao.PersistException;
import com.savin.bank.src.Account;
import com.savin.bank.src.Client;

import java.sql.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientDAOImpl extends AbstractJDBCDao<Client, Integer> implements GenericDao<Client, Integer> {

    public ClientDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Client create() throws SQLException, PersistException {
        Client client = new Client();
        return persist(client);
    }

    @Override
    public String getSelectQuery() {
        return "select * from user";
    }

    @Override
    public String getCreateQuery() {
        return "insert into user (name, surname, second_name, email, password, date_of_registration, date_of_birth) values(?,?,?,?,?,?,?) ";
    }

    @Override
    public String getUpdateQuery() {
        return "update user set name = ?, surname = ?, second_name = ?, email = ?, password = ?, date_of_birth = ?, date_of_registration = ?  where id = ? ;";
    }

    @Override
    public String getDeleteQuery() {
        return "delete from user where id = ?;";
    }

    @Override
    protected ConcurrentHashMap<String,Client> parseResultSet(ResultSet set) throws PersistException {
        ConcurrentHashMap<String,Client> clientMap = new ConcurrentHashMap<>();
        try {
            while (set.next()) {
                Client client = new Client();
                client.setId(set.getInt("id"));
                client.setName(set.getString("name"));
                client.setSurname(set.getString("surname"));
                client.setSecondName(set.getString("second_name"));
                client.setEmail(set.getString("email"));
                client.setPassword(set.getString("password"));
                client.setDateOfBirth(set.getDate("date_of_birth"));
                client.setDateOfRegistration(set.getTimestamp("date_of_registration"));
                DaoFactory factory = new DaoFactoryImpl();
                Connection connection = factory.getConnection();
                GenericDao accountDao = factory.getAccountDao(connection);
                Map<String,Account> map = accountDao.getByEntity(client.getId());
                ConcurrentHashMap<String, Account> userMap = new ConcurrentHashMap<>();
                for (Map.Entry<String, Account> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Account value = entry.getValue();
                    if (value.getUser_id() == client.getId()) {
                        userMap.put(key, value);
                    }
                }
                client.setAccountsList(userMap);
                clientMap.put(client.getEmail(), client);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return clientMap;
    }
    // todo This is not valid
    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Client object) throws PersistException {
        try {
            statement.setString(1,object.getName());
            statement.setString(2, object.getSurname());
            statement.setString(3,object.getSecondName());
            statement.setString(4, object.getEmail());
            statement.setString(5, object.getPassword());
            statement.setDate(6, (Date) object.getDateOfRegistration());
            statement.setDate(7, (Date) object.getDateOfBirth());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
    // todo This is not valid
    @Deprecated
    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Client object) throws PersistException {
        try {
            statement.setString(1,object.getName());
            statement.setString(2,object.getSurname());
            statement.setString(3,object.getSecondName());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
