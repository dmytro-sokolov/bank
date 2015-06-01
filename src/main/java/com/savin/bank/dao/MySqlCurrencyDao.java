package com.savin.bank.dao;

import com.savin.bank.src.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MySqlCurrencyDao  extends AbstractJDBCDao<Currency, Integer> implements GenericDao<Currency, Integer> {

    public MySqlCurrencyDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQueryByEntity(String entity) {
        return "select * from currency where id = " + entity+";";
    }

    @Override
    public String getSelectQuery() {
        return "select * from currency;";
    }

    @Override
    public String getCreateQuery() {
        return "insert into currency (valute, type) values (?,?) ";
    }

    @Override
    public String getUpdateQuery() {
        return "update currency set valute = ?, type = ? where id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "delete from currency where id = ?;";
    }

    @Override
    protected Map<String, Currency> parseResultSet(ResultSet set) throws PersistException {
        ConcurrentHashMap<String, Currency> currencyMap = new ConcurrentHashMap<>();
        try {
            while (set.next()) {
                Currency currency = new Currency();
                currency.setId(set.getInt("id"));
                currency.setType(set.getString("type"));
                currency.setValute(set.getDouble("valute"));
                currencyMap.put(""+currency.getId(), currency);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return currencyMap;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Currency object) throws PersistException {
        //todo must be implemented
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Currency object) throws PersistException {
        //todo must be implemented
    }

    @Override
    public Currency create() throws SQLException, PersistException {
        Currency currency = new Currency();
        return persist(currency);
    }
}
