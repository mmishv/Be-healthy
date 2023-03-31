package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.Recipe;
import by.fpmibsu.be_healthy.entity.RecipeCategory;
import by.fpmibsu.be_healthy.pg.JDBCPostgreSQL;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao extends JDBCPostgreSQL implements Dao<Recipe>  {
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
                var blob = resultSet.getBlob("PHOTO");
                if (blob!=null)
                    recipe.setImage(blob.getBytes(1l, (int)blob.length()));

                String inner_sql = "SELECT CATEGORY_ID AS ID FROM MM_CATEGORY_RECIPE WHERE RECIPE_ID=?";
                try {
                    inner_statement = connection.prepareStatement(inner_sql);
                    inner_statement.setInt(1, recipe.getId());
                    ResultSet messagesIds = inner_statement.executeQuery();
                    List<RecipeCategory> categories = new ArrayList<>();
                    while (messagesIds.next()){
                        categories.add(new RecipeCategoryDao().getEntityById(messagesIds.getInt("ID")));
                    }
                    recipe.setCategories(categories);
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally{
                    if (inner_statement != null) {
                        inner_statement.close();
                    }
                }
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
        Recipe recipe= new Recipe();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                recipe.setId(resultSet.getInt("ID"));
                recipe.setAuthorId(resultSet.getInt("AUTHOR_ID"));
                recipe.setTitle(resultSet.getString("TITLE"));
                recipe.setText(resultSet.getString("DESCRIPTION"));
                recipe.setDateOfPublication(resultSet.getDate("PUBL_DATE"));
                recipe.setCookingTime(resultSet.getInt("COOKING_TIME"));
                var blob = resultSet.getBlob("PHOTO");
                if (blob!=null)
                    recipe.setImage(blob.getBytes(1l, (int)blob.length()));

                String inner_sql = "SELECT CATEGORY_ID AS ID FROM MM_CATEGORY_RECIPE WHERE RECIPE_ID=?";
                try {
                    inner_statement = connection.prepareStatement(inner_sql);
                    inner_statement.setInt(1, recipe.getId());
                    ResultSet messagesIds = inner_statement.executeQuery();
                    List<RecipeCategory> categories = new ArrayList<>();
                    while (messagesIds.next()){
                        categories.add(new RecipeCategoryDao().getEntityById(messagesIds.getInt("ID")));
                    }
                    recipe.setCategories(categories);
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally{
                    if (inner_statement != null) {
                        inner_statement.close();
                    }
                }
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
            preparedStatement.setDate(2,(Date) (entity.getDateOfPublication()));
            preparedStatement.setInt(3,  entity.getCookingTime());
            preparedStatement.setString(4, entity.getText());
            preparedStatement.setBlob(5,
                    entity.getImage()!=null?new SerialBlob(entity.getImage()):null);
            preparedStatement.setInt(6,  entity.getAuthorId());
            preparedStatement.setInt(7,  entity.getId());
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
            preparedStatement.setInt(1,  entity.getId());
            preparedStatement.setString(2, entity.getTitle());
            preparedStatement.setDate(3,(Date) (entity.getDateOfPublication()));
            preparedStatement.setInt(4,  entity.getCookingTime());
            preparedStatement.setString(5, entity.getText());
            preparedStatement.setBlob(6,
                    entity.getImage()!=null?new SerialBlob(entity.getImage()):null);
            preparedStatement.setInt(7,  entity.getAuthorId());
            preparedStatement.executeUpdate();
            for (var i: entity.getCategories()) {
                String inner_sql = "INSERT INTO MM_CATEGORY_RECIPE (RECIPE_ID, CATEGORY_ID) VALUES(?, ?)";
                try {
                    inner_statement1 = connection.prepareStatement(inner_sql);
                    inner_statement1.setInt(1, entity.getId());
                    inner_statement1.setInt(2, i.getId());
                    inner_statement1.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally{
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
}
