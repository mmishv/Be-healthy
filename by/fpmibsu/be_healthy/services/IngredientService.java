package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.entity.Ingredient;
import by.fpmibsu.be_healthy.dao.IngredientDao;

import java.sql.SQLException;
import java.util.List;

public class IngredientService {
    public List<Ingredient> getAll() throws SQLException {
        return new IngredientDao().getAll();
    }


    public Ingredient getEntityById(long id) throws SQLException {
        return new IngredientDao().getEntityById(id);
    }


    public boolean update(Ingredient entity) throws SQLException {
        return new IngredientDao().update(entity);
    }

    public boolean delete(Ingredient entity) throws SQLException {
        return new IngredientDao().delete(entity);
    }

    public boolean create(Ingredient entity) throws SQLException {
        return new IngredientDao().create(entity);
    }

    public List<Ingredient> getIngredientsByRecipeId(int id) throws SQLException {
        return new IngredientDao().getIngredientsByRecipeId(id);
    }
}
