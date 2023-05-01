package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.entity.Ingredient;
import by.fpmibsu.be_healthy.dao.IngredientDao;
import by.fpmibsu.be_healthy.entity.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.ArrayList;
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
    public ArrayList<String> getAllJSON() throws JsonProcessingException, SQLException {
        ArrayList<Ingredient> ing = new ArrayList<>();
        ing = (ArrayList<Ingredient>) new IngredientService().getAll();
        ArrayList<String> json_ing = new ArrayList<>();
        for (var i: ing){
            json_ing.add(new ObjectMapper().writeValueAsString(i));
        }
        return json_ing;
    }
}
