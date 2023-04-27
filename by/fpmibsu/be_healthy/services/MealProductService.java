package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.entity.MealProduct;
import by.fpmibsu.be_healthy.dao.MealProductDao;

import java.sql.SQLException;
import java.util.List;

public class MealProductService {
    public List<MealProduct> getAll() throws SQLException {
        return new MealProductDao().getAll();
    }


    public MealProduct getEntityById(long id) throws SQLException {
        return new MealProductDao().getEntityById(id);
    }


    public boolean update(MealProduct entity) throws SQLException {
        return new MealProductDao().update(entity);
    }

    public boolean delete(MealProduct entity) throws SQLException {
        return new MealProductDao().delete(entity);
    }

    public boolean create(MealProduct entity) throws SQLException {
        return new MealProductDao().create(entity);
    }

    public List<MealProduct> getProductsByMealId(int id) throws SQLException {
        return new MealProductDao().getProductsByMealId(id);
    }
}
