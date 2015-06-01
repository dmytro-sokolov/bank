package com.savin.bank.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListSet;


public interface GenericDao<T, PK extends Serializable> {
    T create() throws SQLException, PersistException;
    T persist(T object) throws  PersistException;
    T getByPK(Integer key) throws  PersistException;
    // todo weird method
    default Map<String,T> getByEntity(PK entity) throws PersistException {
        return null;
    }
    void update(T object) throws  PersistException;
    void delete(T object) throws PersistException;
    Map<String,T> getAll() throws  PersistException;
}
