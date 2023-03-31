package by.fpmibsu.be_healthy.dao;

import java.sql.*;
import java.util.*;
public interface Dao<E> {
    public abstract List<E> getAll() throws SQLException;
    public abstract E getEntityById(long id) throws SQLException;
    public abstract boolean update(E entity) throws SQLException;
    public abstract boolean delete(E entity) throws SQLException;
    public abstract boolean create(E entity) throws SQLException;
}