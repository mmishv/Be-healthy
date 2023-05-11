package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.dao.RecipeDao;
import by.fpmibsu.be_healthy.entity.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class RecipeService {
    public List<Recipe> getAll() {
        return new RecipeDao().getAll();
    }


    public Recipe getEntityById(long id) {
        return new RecipeDao().getEntityById(id);
    }


    public boolean update(Recipe entity) {
        return new RecipeDao().update(entity);
    }

    public boolean delete(Recipe entity) {
        return new RecipeDao().delete(entity);
    }

    public boolean create(Recipe entity) {
        return new RecipeDao().create(entity);
    }

    public String getAllJSON() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new RecipeService().getAll());
    }

    public List<Recipe> getAllInCategory(int id) {
        return new RecipeDao().getAllInCategory(id);
    }

    public String getAllInCategoryJSON(int id) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(getAllInCategory(id));
    }

    public List<Recipe> getCategoryPage(int page, int per_page, int category_id) {
        return new RecipeDao().getCategoryPage(page, per_page, category_id);
    }

    public String getCategoryPageJSON(int page, int per_page, int category_id) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString((getCategoryPage(page, per_page, category_id)));
    }

    public List<Recipe> getPage(int page, int per_page) {
        return new RecipeDao().getPage(page, per_page);
    }

    public String getPageJSON(int page, int per_page) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString((getPage(page, per_page)));
    }

    public int getNumberOfRecipesInCategory(int id) {
        return new RecipeDao().getNumberOfRecipesInCategory(id);
    }

    public int getNumberOfRecipes() {
        return new RecipeDao().getNumberOfRecipes();
    }

    public int getNumberOfRecipesWrittenBy(int id) {
        return new RecipeDao().getNumberOfRecipesWrittenBy(id);
    }

    public List<Recipe> getAuthorPage(int page, int per_page, int id) {
        return new RecipeDao().getAuthorPage(page, per_page, id);
    }

    public String getAuthorPageJSON(int page, int per_page, int id) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString((getAuthorPage(page, per_page, id)));
    }
}