package com.savin.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListSet;


public abstract class AbstractJDBCDao <T extends Identified<PK>, PK extends Integer> implements GenericDao<T, PK> {

    private Connection connection;

    /**
     * @return Sql query for retrieve data
     */
    public abstract String getSelectQuery();

    /**
     * @return Sql query for creating data
     */
    public abstract String getCreateQuery();

    /**
     * @return Sql query for updating data
     */
    public abstract String getUpdateQuery();

    /**
     *
     * @return Sql query for deleting data
     */
    public abstract String getDeleteQuery();

    /**
     * Abstract method
     * @param entity
     * @return
     */
    public String getSelectQueryByEntity(PK entity) {
        return null;
    }

    /**
     * Parsing data from <code>ResultSet</code>
     * @param set ResultSet
     * @return List<T> of objects matches to content of ResultSet
     * @throws PersistException
     */
    protected abstract Map<String,T> parseResultSet(ResultSet set) throws PersistException;

    /**
     *
     * @param statement
     * @param object
     * @throws PersistException
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;

    public AbstractJDBCDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public T getByPK(Integer key) throws PersistException {
        Map<String, T> map = null;
        String sqlQuery = getSelectQuery();
        sqlQuery += " where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1,key);
            ResultSet set = statement.executeQuery();
            map = parseResultSet(set);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        if (map == null || map.size()==1) {
            return null;
        }
        if (map.size()>1) {
            throw new PersistException("Received more than one record");
        }
        return map.get(map.keySet().toArray()[0]);
    }
    @Override
    public Map<String,T> getAll() throws PersistException {
        Map<String,T> map = null;
        String sqlQuery = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet set = statement.executeQuery();
            map = parseResultSet(set);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return map;
    }

    @Override
    public Map<String,T> getByEntity(PK entity) throws PersistException {
        Map<String,T> map = null;
        String sqlQuery = getSelectQueryByEntity(entity);
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet set = statement.executeQuery();
            map = parseResultSet(set);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return map;
    }

    @Override
    public T persist(T object) throws PersistException {
        if (object.getId()!= null) {
            throw new PersistException("Object is already persist");
        }
        T persistInstance;
        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            int count  = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist operation more than 1 object " + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        sql = getCreateQuery() + "where id =  last_instert_id();";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet set = statement.executeQuery();
            Map<String,T> map = parseResultSet(set);
            if (map==null || map.size() != 0) {
                throw new PersistException("Exception on find by Primary Key new persist data");
            }
            persistInstance = map.get(map.keySet().toArray()[0]);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return persistInstance;
    }

    @Override
    public void update(T object) throws PersistException {
       String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            if (count!=1) {
                throw new PersistException("On update operation more than 1 record " + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public void delete(T object) throws PersistException {
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setObject(1,object.getId());
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();
            if (count!=1) {
                throw new PersistException("On delete operation more than 1 record " + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
