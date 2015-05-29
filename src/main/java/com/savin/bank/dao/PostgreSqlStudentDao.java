package com.savin.bank.dao;

import com.savin.bank.java.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PostgreSqlStudentDao extends AbstractJDBCDao<Client, Integer> implements GenericDao<Client, Integer> {

    public PostgreSqlStudentDao(Connection connection) {
        super(connection);
    }

    @Override
    public Client create() throws SQLException, PersistException {
        Client client = new Client();
        return persist(client);
    }

    @Override
    public String getSelectQuery() {
        return "select * from client";
    }

    @Override
    public String getCreateQuery() {
        return "insert into client (name, surname, second_name, date_of_birth, date_of_registration) values(?,?,?,?,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "update client set name = ?, surname = ?, second_name = ?, date_of_birth = ?, date_of_registration = ?  where id = ? ;";
    }

    @Override
    public String getDeleteQuery() {
        return "delete from client where id = ?;";
    }

    @Override
    protected List<Client> parseResultSet(ResultSet set) throws PersistException {
        LinkedList<Client> clientList = new LinkedList<>();
        try {
            while (set.next()) {
                Client client = new Client();
                client.setId(set.getInt("id"));
                client.setName(set.getString("name"));
                client.setSurname(set.getString("surname"));
                client.setSecondName(set.getString("second_name"));
                client.setDateOfBirth(set.getDate("date_of_birth"));
                client.setDateOfRegistration(set.getTimestamp("date_of_registration"));
                clientList.add(client);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return clientList;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Client object) throws PersistException {
        try {
            statement.setString(1,object.getName());
            statement.setString(2,object.getSurname());
            statement.setString(3,object.getSecondName());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

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
