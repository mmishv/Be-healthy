package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.Ingredient;
import by.fpmibsu.be_healthy.postgres.JDBCPostgreSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientDao extends JDBCPostgreSQL implements Dao<Ingredient> {
    private final Connection connection = getConnection();

    @Override
    public List<Ingredient> getAll() {
        List<Ingredient> ingredients = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM INGREDIENT");
            while (resultSet.next()) {
                Ingredient product = new Ingredient(new ProductDao().getEntityById(resultSet.getInt("PRODUCT_ID")));
                product.setIngredientId(resultSet.getInt("ID"));
                product.setQuantity(resultSet.getInt("QUANTITY"));
                product.setRecipe_id(resultSet.getInt("RECIPE_ID"));
                ingredients.add(product);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    @Override
    public Ingredient getEntityById(long id) {
        Ingredient ingredient = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM INGREDIENT WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ingredient = new Ingredient(new ProductDao().getEntityById(resultSet.getInt("PRODUCT_ID")));
                ingredient.setIngredientId(resultSet.getInt("ID"));
                ingredient.setQuantity(resultSet.getInt("QUANTITY"));
                ingredient.setRecipe_id(resultSet.getInt("RECIPE_ID"));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredient;
    }

    @Override
    public boolean update(Ingredient entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE INGREDIENT SET RECIPE_ID=?, PRODUCT_ID=?, QUANTITY=? WHERE ID=?")) {
            preparedStatement.setInt(1, entity.getRecipe_id());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.setInt(3, entity.getQuantity());
            preparedStatement.setInt(4, entity.getIngredientId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Ingredient entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM INGREDIENT WHERE ID=?")) {
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean create(Ingredient entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO INGREDIENT (RECIPE_ID, PRODUCT_ID, QUANTITY) VALUES(?, ?, ?)")) {
            preparedStatement.setInt(1, entity.getRecipe_id());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.setInt(3, entity.getQuantity());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Ingredient> getIngredientsByRecipeId(int id) {
        List<Ingredient> ingredients = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT ID FROM INGREDIENT WHERE RECIPE_ID=?")) {
            statement.setInt(1, id);
            ResultSet ingredientsIds = statement.executeQuery();
            while (ingredientsIds.next()) {
                ingredients.add(new IngredientDao().getEntityById(ingredientsIds.getInt("ID")));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    public boolean deleteRecipeIngredients(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM INGREDIENT WHERE RECIPE_ID=?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
