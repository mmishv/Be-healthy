package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.dao.RecipeDao;
import by.fpmibsu.be_healthy.entity.RecipeCategory;
import by.fpmibsu.be_healthy.dao.RecipeCategoryDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class RecipeCategoryService {
    public List<RecipeCategory> getAll() {
        return new RecipeCategoryDao().getAll();
    }


    public RecipeCategory getEntityById(long id) {
        return new RecipeCategoryDao().getEntityById(id);
    }

    public boolean update(RecipeCategory entity) {
        return new RecipeCategoryDao().update(entity);
    }

    public boolean delete(RecipeCategory entity) {
        return new RecipeCategoryDao().delete(entity);
    }

    public boolean create(RecipeCategory entity) {
        return new RecipeCategoryDao().create(entity);
    }

    public List<RecipeCategory> getRecipeCategoriesByArticleId(int id) {
        return new RecipeCategoryDao().getCategoriesByRecipeId(id);
    }

    public String getAllJSON() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new RecipeCategoryService().getAll());
    }

    public int getNumberOfRecipes() {
        return new RecipeDao().getNumberOfRecipes();
    }

    public int getNumberOfRecipesInCategory(int id) {
        return new RecipeDao().getNumberOfRecipesInCategory(id);
    }
}
