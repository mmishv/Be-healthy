package by.fpmibsu.be_healthy.service;

import by.fpmibsu.be_healthy.dao.RecipeDao;
import by.fpmibsu.be_healthy.entity.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RecipeService {
    private static final Logger logger = LogManager.getLogger(RecipeService.class);
    public List<Recipe> getAll() {
        logger.debug("Get all recipes");
        var recipes = new RecipeDao().getAll();
        for (var recipe : recipes){
            recipe.setCategories(new RecipeCategoryService().getRecipeCategoriesByRecipeId(recipe.getId()));
            recipe.setIngredients(new IngredientService().getIngredientsByRecipeId(recipe.getId()));
        }
        return recipes;
    }


    public Recipe getEntityById(Integer id) {
        logger.debug("Get recipe by id");
        var recipe = new RecipeDao().getEntityById(id);
        if (recipe != null){
            recipe.setCategories(new RecipeCategoryService().getRecipeCategoriesByRecipeId(recipe.getId()));
            recipe.setIngredients(new IngredientService().getIngredientsByRecipeId(recipe.getId()));
        }
        return recipe;
    }


    public boolean update(Recipe entity) {
        logger.info("Update recipe");
        var result =  new RecipeDao().update(entity);
        if (entity!=null){
            new IngredientService().deleteRecipeIngredients(entity.getId());
            for (var i : entity.getIngredients()) {
                i.setRecipeId(entity.getId());
                new IngredientService().create(i);
            }
        }
        return result;
    }

    public boolean delete(int id) {
        logger.warn("Delete recipe");
        return new RecipeDao().delete(id);
    }

    public boolean create(Recipe entity) {
        logger.info("Create recipe");
        var result = new RecipeDao().create(entity);
        if (entity!=null){
            var id = getMaxId();
            for (var i : entity.getIngredients()) {
                i.setRecipeId(id);
                new IngredientService().create(i);
            }
        }
        return result;
    }

    public String getAllJSON() throws JsonProcessingException {
        logger.debug("Get all recipes in JSON format");
        return new ObjectMapper().writeValueAsString(new RecipeService().getAll());
    }

    public List<Recipe> getCategoryPage(int page, int per_page, int category_id) {
        logger.debug("Get page of recipes in one category");
        var recipes = new RecipeDao().getCategoryPage(page, per_page, category_id);
        for (var recipe : recipes){
            recipe.setCategories(new RecipeCategoryService().getRecipeCategoriesByRecipeId(recipe.getId()));
            recipe.setIngredients(new IngredientService().getIngredientsByRecipeId(recipe.getId()));
        }
        return recipes;
    }

    public String getCategoryPageJSON(int page, int per_page, int category_id) throws JsonProcessingException {
        logger.debug("Get page of recipes in one category in JSON format");
        return new ObjectMapper().writeValueAsString((getCategoryPage(page, per_page, category_id)));
    }
    public String getEntityByIdJSON(Integer id) throws JsonProcessingException {
        logger.debug("Get recipe by id in JSON format");
        return new ObjectMapper().writeValueAsString(getEntityById(id));
    }

    public String getPageJSON(int page, int per_page, boolean moderated) throws JsonProcessingException {
        logger.debug("Get page of recipes in JSON format");
        return new ObjectMapper().writeValueAsString(getPage(page, per_page, moderated));
    }

    public String getAuthorPagJSON(int page, int per_page, int id) throws JsonProcessingException {
        logger.debug("Get page of user's recipes in JSON format");
        return new ObjectMapper().writeValueAsString(getAuthorPage(page, per_page, id));
    }
    public List<Recipe> getPage(int page, int per_page, boolean moderated) {
        logger.debug("Get page of recipes");
        var recipes = new RecipeDao().getPage(page, per_page, moderated);
        for (var recipe : recipes){
            recipe.setCategories(new RecipeCategoryService().getRecipeCategoriesByRecipeId(recipe.getId()));
            recipe.setIngredients(new IngredientService().getIngredientsByRecipeId(recipe.getId()));
        }
        return recipes;
    }


    public int getNumberOfRecipesInCategory(int id) {
        logger.debug("Get number of recipes in one category");
        return new RecipeDao().getNumberOfRecipesInCategory(id);
    }

    public int getNumberOfRecipes(boolean moderated) {
        logger.debug("Get number of recipes");
        return new RecipeDao().getNumberOfRecipes(moderated);
    }

    public int getNumberOfRecipesWrittenBy(int id) {
        logger.debug("Get number of user's recipes");
        return new RecipeDao().getNumberOfRecipesWrittenBy(id);
    }

    public List<Recipe> getAuthorPage(int page, int per_page, int id) {
        logger.debug("Get page of user's recipes");
        var recipes = new RecipeDao().getAuthorPage(page, per_page, id);
        for (var recipe : recipes){
            recipe.setCategories(new RecipeCategoryService().getRecipeCategoriesByRecipeId(recipe.getId()));
            recipe.setIngredients(new IngredientService().getIngredientsByRecipeId(recipe.getId()));
        }
        return recipes;
    }
    public boolean updateModerationStatus(int id, boolean moderated){
        logger.info("Update recipe moderation status");
        return new RecipeDao().updateModerationStatus(id, moderated);
    }

    public int getMaxId() {
       return new RecipeDao().getMaxId();
    }
}