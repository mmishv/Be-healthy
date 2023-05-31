package by.fpmibsu.be_healthy.service;

import by.fpmibsu.be_healthy.dao.IngredientDao;
import by.fpmibsu.be_healthy.entity.Ingredient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class IngredientService {
    private static final Logger logger = LogManager.getLogger(IngredientService.class);
    public List<Ingredient> getAll() {
        logger.debug("Get all ingredients");
        List<Ingredient> ingredients = new java.util.ArrayList<>(List.of());
        for (var full_ingredient : new IngredientDao().getAll()){
            var ingredient = full_ingredient;
            full_ingredient = new Ingredient(new ProductService().getEntityById(ingredient.getId()));
            full_ingredient.setIngredientId(ingredient.getIngredientId());
            full_ingredient.setQuantity(ingredient.getQuantity());
            full_ingredient.setRecipeId(ingredient.getRecipeId());
            ingredients.add(full_ingredient);
        }
        return ingredients;
    }


    public Ingredient getEntityById(Integer id) {
        logger.debug("Get ingredient by id");
        var ingredient = new IngredientDao().getEntityById(id);
        if (ingredient!=null){
            var full_ingredient = new Ingredient(new ProductService().getEntityById(ingredient.getId()));
            full_ingredient.setIngredientId(ingredient.getIngredientId());
            full_ingredient.setQuantity(ingredient.getQuantity());
            full_ingredient.setRecipeId(ingredient.getRecipeId());
            return full_ingredient;
        }
        return null;
    }


    public boolean update(Ingredient entity) {
        logger.info("Update ingredient");
        return new IngredientDao().update(entity);
    }

    public boolean delete(int id) {
        logger.warn("Delete ingredient");
        return new IngredientDao().delete(id);
    }

    public boolean create(Ingredient entity) {
        logger.info("Create ingredient");
        return new IngredientDao().create(entity);
    }

    public List<Ingredient> getIngredientsByRecipeId(int id) {
        logger.debug("Get ingredients by recipe id");
        List<Ingredient> ingredients = new java.util.ArrayList<>(List.of());
        for (var full_ingredient : new IngredientDao().getIngredientsByRecipeId(id)){
            var ingredient = full_ingredient;
            full_ingredient = new Ingredient(new ProductService().getEntityById(ingredient.getId()));
            full_ingredient.setIngredientId(ingredient.getIngredientId());
            full_ingredient.setQuantity(ingredient.getQuantity());
            full_ingredient.setRecipeId(ingredient.getRecipeId());
            ingredients.add(full_ingredient);
        }
        return ingredients;
    }

    public String getAllJSON() throws JsonProcessingException {
        logger.debug("Get all ingredients in JSON format");
        return new ObjectMapper().writeValueAsString(new IngredientService().getAll());
    }

    public String getIngredientsByRecipeIdJSON(int id) throws JsonProcessingException {
        logger.debug("Get ingredients by recipe id in JSON format");
        return new ObjectMapper().writeValueAsString(getIngredientsByRecipeId(id));
    }

    public String getEntityByIdJSON(Integer id) throws JsonProcessingException {
        logger.debug("Get ingredient by id in JSON format");
        return new ObjectMapper().writeValueAsString((getEntityById(id)));
    }
    public void deleteRecipeIngredients(int id) {
        new IngredientDao().deleteRecipeIngredients(id);
    }
}
