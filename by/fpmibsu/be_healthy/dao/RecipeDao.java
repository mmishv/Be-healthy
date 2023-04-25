package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.Recipe;
import by.fpmibsu.be_healthy.pg.JDBCPostgreSQL;

import javax.sql.rowset.serial.SerialBlob;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import  java.util.Base64;

public class RecipeDao extends JDBCPostgreSQL implements Dao<Recipe> {
    private Connection connection = getConnection();

    @Override
    public List<Recipe> getAll() throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM RECIPE";
        Statement statement = null;
        PreparedStatement inner_statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getInt("ID"));
                recipe.setAuthorId(resultSet.getInt("AUTHOR_ID"));
                recipe.setTitle(resultSet.getString("TITLE"));
                recipe.setText(resultSet.getString("DESCRIPTION"));
                recipe.setDateOfPublication(resultSet.getDate("PUBL_DATE"));
                recipe.setCookingTime(resultSet.getInt("COOKING_TIME"));
                recipe.setImage(resultSet.getBytes("PHOTO"));

                byte[] encodeBase64 = Base64.getEncoder().encode(resultSet.getBytes("PHOTO"));
                String base64encoded = new String(encodeBase64, StandardCharsets.UTF_8);
                recipe.setBase64image(base64encoded);

                recipe.setCategories(new RecipeCategoryDao().getArticleCategoriesByArticleId(recipe.getAuthorId()));
                recipe.setIngredients(new IngredientDao().getIngredientsByRecipeId(recipe.getId()));
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return recipes;
    }

    @Override
    public Recipe getEntityById(long id) throws SQLException {
        PreparedStatement preparedStatement = null, inner_statement = null;
        String sql = "SELECT * FROM RECIPE WHERE ID=?";
        Recipe recipe = new Recipe();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                recipe.setId(resultSet.getInt("ID"));
                recipe.setAuthorId(resultSet.getInt("AUTHOR_ID"));
                recipe.setTitle(resultSet.getString("TITLE"));
                recipe.setText(resultSet.getString("DESCRIPTION"));
                recipe.setDateOfPublication(resultSet.getDate("PUBL_DATE"));
                recipe.setCookingTime(resultSet.getInt("COOKING_TIME"));
                recipe.setImage(resultSet.getBytes("PHOTO"));

                byte[] encodeBase64 = Base64.getEncoder().encode(resultSet.getBytes("PHOTO"));
                String base64encoded = new String(encodeBase64, StandardCharsets.UTF_8);
                recipe.setBase64image(base64encoded);

                recipe.setCategories(new RecipeCategoryDao().getArticleCategoriesByArticleId(recipe.getAuthorId()));
                recipe.setIngredients(new IngredientDao().getIngredientsByRecipeId(recipe.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return recipe;
    }

    @Override
    public boolean update(Recipe entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE RECIPE SET TITLE=?, PUBL_DATE=?, COOKING_TIME=?, DESCRIPTION=?, PHOTO=?, AUTHOR_ID=? WHERE ID=?";
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setDate(2, (Date) (entity.getDateOfPublication()));
            preparedStatement.setInt(3, entity.getCookingTime());
            preparedStatement.setString(4, entity.getText());
            preparedStatement.setBytes(5,
                    entity.getImage() != null ? entity.getImage(): null);
            preparedStatement.setInt(6, entity.getAuthorId());
            preparedStatement.setInt(7, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return success;
    }

    @Override
    public boolean delete(Recipe entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM RECIPE WHERE ID=?";
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return success;
    }

    @Override
    public boolean create(Recipe entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO RECIPE (ID,TITLE, PUBL_DATE, COOKING_TIME, DESCRIPTION, PHOTO, AUTHOR_ID) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement inner_statement1 = null, inner_statement2 = null;
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getTitle());
            preparedStatement.setDate(3, (Date) (entity.getDateOfPublication()));
            preparedStatement.setInt(4, entity.getCookingTime());
            preparedStatement.setString(5, entity.getText());
            preparedStatement.setBlob(6,
                    entity.getImage() != null ? new SerialBlob(entity.getImage()) : null);
            preparedStatement.setInt(7, entity.getAuthorId());
            preparedStatement.executeUpdate();
            for (var i : entity.getCategories()) {
                String inner_sql = "INSERT INTO MM_CATEGORY_RECIPE (RECIPE_ID, CATEGORY_ID) VALUES(?, ?)";
                try {
                    inner_statement1 = connection.prepareStatement(inner_sql);
                    inner_statement1.setInt(1, entity.getId());
                    inner_statement1.setInt(2, i.getId());
                    inner_statement1.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (inner_statement1 != null) {
                        inner_statement1.close();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return success;
    }
    List<Recipe> getWrittenRecipesByUserId(int id) throws SQLException {
        PreparedStatement statement = null;
        String sql  = "SELECT ID FROM RECIPE WHERE AUTHOR_ID=?";
        List<Recipe> recipes = new ArrayList<>();
        try{
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet recipesIds = statement.executeQuery();
            while (recipesIds.next()){
                recipes.add(new RecipeDao().getEntityById(recipesIds.getInt("ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if (statement != null) {
                statement.close();
            }
        }
        return recipes;
    }
}
