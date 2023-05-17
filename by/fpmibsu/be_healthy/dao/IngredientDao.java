package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.Ingredient;
import by.fpmibsu.be_healthy.postgres.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientDao implements Dao<Ingredient> {
    private static final Logger logger = LogManager.getLogger(IngredientDao.class);

    @Override
    public List<Ingredient> getAll() {
        List<Ingredient> ingredients = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM INGREDIENT");
            while (resultSet.next()) {
                Ingredient product = new Ingredient(new ProductDao().getEntityById(resultSet.getInt("PRODUCT_ID")));
                product.setIngredientId(resultSet.getInt("ID"));
                product.setQuantity(resultSet.getInt("QUANTITY"));
                product.setRecipe_id(resultSet.getInt("RECIPE_ID"));
                ingredients.add(product);
            }
        } catch (SQLException e) {
            logger.error("Error getting all ingredients");
            e.printStackTrace();
        }
        return ingredients;
    }

    @Override
    public Ingredient getEntityById(long id) {
        Ingredient ingredient = null;
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM INGREDIENT WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ingredient = new Ingredient(new ProductDao().getEntityById(resultSet.getInt("PRODUCT_ID")));
                ingredient.setIngredientId(resultSet.getInt("ID"));
                ingredient.setQuantity(resultSet.getInt("QUANTITY"));
                ingredient.setRecipe_id(resultSet.getInt("RECIPE_ID"));
            }
        } catch (SQLException e) {
            logger.error("Error getting ingredient by id");
            e.printStackTrace();
        }
        return ingredient;
    }

    @Override
    public boolean update(Ingredient entity) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE INGREDIENT SET RECIPE_ID=?, PRODUCT_ID=?, QUANTITY=? WHERE ID=?")) {
            preparedStatement.setInt(1, entity.getRecipe_id());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.setInt(3, entity.getQuantity());
            preparedStatement.setInt(4, entity.getIngredientId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating ingredient");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM INGREDIENT WHERE ID=?")) {
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting ingredient");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean create(Ingredient entity) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO INGREDIENT (RECIPE_ID, PRODUCT_ID, QUANTITY) VALUES(?, ?, ?)")) {
            preparedStatement.setInt(1, entity.getRecipe_id());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.setInt(3, entity.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error creating ingredient");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Ingredient> getIngredientsByRecipeId(int id) {
        List<Ingredient> ingredients = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT ID FROM INGREDIENT WHERE RECIPE_ID=?")) {
            statement.setInt(1, id);
            ResultSet ingredientsIds = statement.executeQuery();
            while (ingredientsIds.next()) {
                ingredients.add(new IngredientDao().getEntityById(ingredientsIds.getInt("ID")));
            }
        } catch (SQLException e) {
            logger.error("Error getting ingredients by recipe id");
            e.printStackTrace();
        }
        return ingredients;
    }

    public boolean deleteRecipeIngredients(int id) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM INGREDIENT WHERE RECIPE_ID=?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting recipe ingredients");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
