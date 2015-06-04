package com.savin.bank.dao;


import com.savin.bank.src.Account;
import com.savin.bank.src.Currency;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MySqlAccountDao extends AbstractJDBCDao<Account, Integer> implements GenericDao<Account, Integer>  {

    public MySqlAccountDao(Connection connection) {
        super(connection);
    }

//    @Override
//    public String getSelectQueryByEntity(String entity) {
//        return "select * from currency where id = " + entity+";";
//    }


    @Override
    public String getSelectQueryByEntity(Integer entity) {
        return "select * from account where client_id = "+entity+";";
    }

    @Override
    public String getSelectQuery() {
        return "select * from account where client_id = ?;";
    }

    @Override
    public String getCreateQuery() {
        return "insert into account (client_id, balance, currency, type) values (?,?,?,?) ";
    }

    @Override
    public String getUpdateQuery() {
        return "update account set balance = ?, currency = ?, type = ? where id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "delete from account where id = ?;";
    }

    @Override
    protected Map<String, Account> parseResultSet(ResultSet set) throws PersistException {
        ConcurrentHashMap<String , Account> accountMap = new ConcurrentHashMap<>();
        try {
            while (set.next()) {
                Account account = new Account();
                account.setId(set.getInt("id"));
                account.setUser_id(set.getInt("client_id"));
                account.setBalance(set.getDouble("balance"));
                account.setType(set.getString("type"));
                DaoFactory factory = new MySqlDaoFactory();
                Connection connection = factory.getConnection();
                GenericDao currencyDao = factory.getCurrencyDao(connection);
                Map<String,Currency> map = currencyDao.getByEntity(set.getInt("currency"));
                account.setCurrency((Currency) map.values().toArray()[0]);
                accountMap.put(""+account.getId(),account);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return accountMap;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Account object) throws PersistException {

    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Account object) throws PersistException {
        //todo need to be implemented
    }

    @Override
    public Account create() throws SQLException, PersistException {
        return null;
    }
}
