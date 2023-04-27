package by.fpmibsu.be_healthy.services;
import by.fpmibsu.be_healthy.dao.RecipeDao;
import by.fpmibsu.be_healthy.entity.Recipe;
import by.fpmibsu.be_healthy.pg.JDBCPostgreSQL;

import java.sql.SQLException;
import java.util.List;

public class RecipeService {
    public List<Recipe> getAll() throws SQLException{
        return new RecipeDao().getAll();
    };
    public Recipe getEntityById(long id) throws SQLException{
        return new RecipeDao().getEntityById(id);
    };
    public boolean update(Recipe entity) throws SQLException{
        return new RecipeDao().update(entity);
    }
    public boolean delete(Recipe entity) throws SQLException{
        return new RecipeDao().delete(entity);
    }
    public boolean create(Recipe entity) throws SQLException{
        return new RecipeDao().create(entity);
    }
}