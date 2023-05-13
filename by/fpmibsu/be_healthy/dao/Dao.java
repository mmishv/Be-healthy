package by.fpmibsu.be_healthy.dao;

import java.util.*;
public interface Dao<E> {
    public abstract List<E> getAll();
    public abstract E getEntityById(long id);
    public abstract boolean update(E entity);
    public abstract boolean delete(int id);
    public abstract boolean create(E entity);
}