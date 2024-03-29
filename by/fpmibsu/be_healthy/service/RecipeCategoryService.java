package by.fpmibsu.be_healthy.service;

import by.fpmibsu.be_healthy.dao.RecipeCategoryDao;
import by.fpmibsu.be_healthy.entity.RecipeCategory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RecipeCategoryService {
    private static final Logger logger = LogManager.getLogger(RecipeCategoryService.class);
    public List<RecipeCategory> getAll() {
        logger.debug("Get all recipe categories");
        return new RecipeCategoryDao().getAll();
    }


    public RecipeCategory getEntityById(Integer id) {
        logger.debug("Get recipe category by id");
        return new RecipeCategoryDao().getEntityById(id);
    }

    public boolean update(RecipeCategory entity) {
        logger.info("Update recipe category");
        return new RecipeCategoryDao().update(entity);
    }

    public boolean delete(int id) {
        logger.warn("Delete recipe category");
        return new RecipeCategoryDao().delete(id);
    }

    public boolean create(RecipeCategory entity) {
        logger.info("Create recipe category");
        return new RecipeCategoryDao().create(entity);
    }

    public List<RecipeCategory> getRecipeCategoriesByRecipeId(int id) {
        logger.debug("Get recipe categories by article id");
        return new RecipeCategoryDao().getRecipeCategoriesByRecipeId(id);
    }

    public String getAllJSON() throws JsonProcessingException {
        logger.debug("Get all recipe categories in JSON format");
        return new ObjectMapper().writeValueAsString(new RecipeCategoryService().getAll());
    }
    public String getEntityByIdJSON(Integer id) throws JsonProcessingException {
        logger.debug("Get recipe category by id in JSON format");
        return new ObjectMapper().writeValueAsString(getEntityById(id));
    }

    public String getRecipeCategoriesByRecipeIdJSON(int id) throws JsonProcessingException {
        logger.debug("Get recipe categories by article id in JSON format");
        return new ObjectMapper().writeValueAsString(getRecipeCategoriesByRecipeId(id));
    }
}
