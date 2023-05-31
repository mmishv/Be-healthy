package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.Entity;

import java.util.List;
public interface Dao<K, E extends Entity> {
    public abstract List<E> getAll();
    public abstract E getEntityById(K id);
    public abstract boolean update(E entity);
    public abstract boolean delete(K id);
    public abstract boolean create(E entity);
}