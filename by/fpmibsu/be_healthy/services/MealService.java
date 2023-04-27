package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.entity.Meal;
import by.fpmibsu.be_healthy.dao.MealDao;

import java.sql.SQLException;
import java.util.List;

public class MealService {
    public List<Meal> getAll() throws SQLException {
        return new MealDao().getAll();
    }


    public Meal getEntityById(long id) throws SQLException {
        return new MealDao().getEntityById(id);
    }


    public boolean update(Meal entity) throws SQLException {
        return new MealDao().update(entity);
    }

    public boolean delete(Meal entity) throws SQLException {
        return new MealDao().delete(entity);
    }

    public boolean create(Meal entity) throws SQLException {
        return new MealDao().create(entity);
    }

    public List<Meal> getMealsByUserId(int id) throws SQLException {
        return new MealDao().getMealsByUserId(id);
    }
}
