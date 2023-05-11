package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.entity.Ingredient;
import by.fpmibsu.be_healthy.dao.IngredientDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class IngredientService {
    public List<Ingredient> getAll() {
        return new IngredientDao().getAll();
    }


    public Ingredient getEntityById(long id) {
        return new IngredientDao().getEntityById(id);
    }


    public boolean update(Ingredient entity) {
        return new IngredientDao().update(entity);
    }

    public boolean delete(Ingredient entity) {
        return new IngredientDao().delete(entity);
    }

    public boolean create(Ingredient entity) {
        return new IngredientDao().create(entity);
    }

    public List<Ingredient> getIngredientsByRecipeId(int id) {
        return new IngredientDao().getIngredientsByRecipeId(id);
    }

    public String getAllJSON() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new IngredientService().getAll());
    }

    public boolean deleteRecipeIngredients(int id) {
        return new IngredientDao().deleteRecipeIngredients(id);
    }
}
