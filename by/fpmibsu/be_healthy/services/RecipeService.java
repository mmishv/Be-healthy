package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.dao.RecipeDao;
import by.fpmibsu.be_healthy.entity.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeService {
    public List<Recipe> getAll() throws SQLException {
        return new RecipeDao().getAll();
    }


    public Recipe getEntityById(long id) throws SQLException {
        return new RecipeDao().getEntityById(id);
    }


    public boolean update(Recipe entity) throws SQLException {
        return new RecipeDao().update(entity);
    }

    public boolean delete(Recipe entity) throws SQLException {
        return new RecipeDao().delete(entity);
    }

    public boolean create(Recipe entity) throws SQLException {
        return new RecipeDao().create(entity);
    }

    public List<Recipe> getWrittenRecipesByUserId(int id) throws SQLException {
        return new RecipeDao().getWrittenRecipesByUserId(id);
    }

    public ArrayList<String> getAllJSON() throws JsonProcessingException, SQLException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        recipes = (ArrayList<Recipe>) new RecipeService().getAll();
        ArrayList<String> json_rec = new ArrayList<>();
        for (var r: recipes){
            json_rec.add(new ObjectMapper().writeValueAsString(r));
        }
        return json_rec;
    }
}