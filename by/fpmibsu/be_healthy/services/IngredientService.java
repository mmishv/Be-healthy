package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.entity.Ingredient;
import by.fpmibsu.be_healthy.dao.IngredientDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class IngredientService {
    private static final Logger logger = LogManager.getLogger(IngredientService.class);
    public List<Ingredient> getAll() {
        logger.debug("Get all ingredients");
        return new IngredientDao().getAll();
    }


    public Ingredient getEntityById(long id) {
        logger.debug("Get ingredient by id");
        return new IngredientDao().getEntityById(id);
    }


    public boolean update(Ingredient entity) {
        logger.debug("Update ingredient");
        return new IngredientDao().update(entity);
    }

    public boolean delete(int id) {
        logger.debug("Delete ingredient");
        return new IngredientDao().delete(id);
    }

    public boolean create(Ingredient entity) {
        logger.debug("Create ingredient");
        return new IngredientDao().create(entity);
    }

    public List<Ingredient> getIngredientsByRecipeId(int id) {
        logger.debug("Get ingredients by recipe id");
        return new IngredientDao().getIngredientsByRecipeId(id);
    }

    public String getAllJSON() throws JsonProcessingException {
        logger.debug("Get all ingredients in JSON format");
        return new ObjectMapper().writeValueAsString(new IngredientService().getAll());
    }

    public boolean deleteRecipeIngredients(int id) {
        logger.debug("Delete recipe ingredients");
        return new IngredientDao().deleteRecipeIngredients(id);
    }
}
