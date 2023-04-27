package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.entity.RecipeCategory;
import by.fpmibsu.be_healthy.dao.RecipeCategoryDao;

import java.sql.SQLException;
import java.util.List;

public class RecipeCategoryService {
    public List<RecipeCategory> getAll() throws SQLException {
        return new RecipeCategoryDao().getAll();
    }


    public RecipeCategory getEntityById(long id) throws SQLException {
        return new RecipeCategoryDao().getEntityById(id);
    }

    public boolean update(RecipeCategory entity) throws SQLException {
        return new RecipeCategoryDao().update(entity);
    }

    public boolean delete(RecipeCategory entity) throws SQLException {
        return new RecipeCategoryDao().delete(entity);
    }

    public boolean create(RecipeCategory entity) throws SQLException {
        return new RecipeCategoryDao().create(entity);
    }

    public List<RecipeCategory> getRecipeCategoriesByArticleId(int id) throws SQLException {
        return new RecipeCategoryDao().getRecipeCategoriesByArticleId(id);
    }
}
