package com.savin.bank.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


public interface GenericDao<T, PK extends Serializable> {
    T create() throws SQLException, PersistException;
    T persist(T object) throws  PersistException;
    T getByPK(Integer key) throws  PersistException;
    void update(T object) throws  PersistException;
    void delete(T object) throws PersistException;
    List<T> getAll() throws  PersistException;
}
