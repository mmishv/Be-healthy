package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.Recipe;
import by.fpmibsu.be_healthy.postgres.DataSource;
import by.fpmibsu.be_healthy.services.IngredientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;

public class RecipeDao implements Dao<Recipe> {
    private static final Logger logger = LogManager.getLogger(ProfileDao.class);

    @Override
    public List<Recipe> getAll() {
        List<Recipe> recipes = new ArrayList<>();
        try (Connection connection = DataSource.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM RECIPE ORDER BY PUBL_DATE DESC");
            initRecipe(recipes, resultSet);
        } catch (SQLException e) {
            logger.error("Error getting all recipes");
            e.printStackTrace();
        }
        return recipes;
    }

    public List<Recipe> getPage(int page, int per_page, boolean moderated) {
        List<Recipe> recipes = new ArrayList<>();
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM RECIPE WHERE moderated = ? ORDER BY PUBL_DATE DESC LIMIT ? OFFSET ?")) {
            statement.setBoolean(1, moderated);
            statement.setInt(2, per_page);
            statement.setInt(3, per_page * (page - 1));
            ResultSet resultSet = statement.executeQuery();
            initRecipe(recipes, resultSet);
        } catch (SQLException e) {
            logger.error("Error getting page of recipes");
            e.printStackTrace();
        }
        return recipes;
    }

    public List<Recipe> getCategoryPage(int page, int per_page, int category_id) {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM RECIPE WHERE ID IN (SELECT RECIPE_ID ID " + "FROM MM_CATEGORY_RECIPE WHERE CATEGORY_ID = ?) AND MODERATED = TRUE " + "ORDER BY PUBL_DATE DESC LIMIT ? OFFSET ?";
        return helpGetPage(page, per_page, category_id, recipes, sql);
    }

    private List<Recipe> helpGetPage(int page, int per_page, int category_id, List<Recipe> recipes, String sql) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, category_id);
            statement.setInt(2, per_page);
            statement.setInt(3, per_page * (page - 1));
            ResultSet resultSet = statement.executeQuery();
            initRecipe(recipes, resultSet);
        } catch (SQLException e) {
            logger.error("Error getting page of recipes");
            e.printStackTrace();
        }
        return recipes;
    }

    public List<Recipe> getAuthorPage(int page, int per_page, int author_id) {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM RECIPE WHERE AUTHOR_ID = ?" + "ORDER BY PUBL_DATE DESC LIMIT ? OFFSET ?";
        return helpGetPage(page, per_page, author_id, recipes, sql);
    }

    @Override
    public Recipe getEntityById(long id) {
        Recipe recipe = new Recipe();
        try (Connection connection = DataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM RECIPE WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                setRecipe(recipe, resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error getting recipe by id");
            e.printStackTrace();
        }
        return recipe;
    }

    private void setRecipe(Recipe recipe, ResultSet resultSet) throws SQLException {
        recipe.setId(resultSet.getInt("ID"));
        recipe.setAuthorId(resultSet.getInt("AUTHOR_ID"));
        recipe.setTitle(resultSet.getString("TITLE"));
        recipe.setText(resultSet.getString("DESCRIPTION"));
        recipe.setDateOfPublication(resultSet.getDate("PUBL_DATE"));
        recipe.setCookingTime(resultSet.getInt("COOKING_TIME"));
        recipe.setImage(resultSet.getBytes("PHOTO"));
        recipe.setModerated(resultSet.getBoolean("MODERATED"));
        byte[] encodeBase64 = Base64.getEncoder().encode(resultSet.getBytes("PHOTO"));
        String base64encoded = new String(encodeBase64, StandardCharsets.UTF_8);
        recipe.setBase64image(base64encoded);
        recipe.setCategories(new RecipeCategoryDao().getRecipeCategoriesByRecipeId(recipe.getId()));
        recipe.setIngredients(new IngredientDao().getIngredientsByRecipeId(recipe.getId()));
    }

    @Override
    public boolean update(Recipe entity) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("UPDATE RECIPE SET TITLE=?, PUBL_DATE=?, COOKING_TIME=?, DESCRIPTION=?, PHOTO=?, AUTHOR_ID=? WHERE ID=?")) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setDate(2, (Date) (entity.getDateOfPublication()));
            preparedStatement.setInt(3, entity.getCookingTime());
            preparedStatement.setString(4, entity.getText());
            preparedStatement.setBytes(5, entity.getImage() != null ? entity.getImage() : null);
            preparedStatement.setInt(6, entity.getAuthorId());
            preparedStatement.setInt(7, entity.getId());
            preparedStatement.executeUpdate();
            new IngredientService().deleteRecipeIngredients(entity.getId());
            deleteFromMM(entity.getId());
            initCategoriesAndIngredients(entity, entity.getId());
        } catch (SQLException e) {
            logger.error("Error updating recipe");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateModerationStatus(int id, boolean moderated) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("UPDATE RECIPE SET MODERATED=? WHERE ID=?")) {
            preparedStatement.setBoolean(1, moderated);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating recipe moderation status");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void deleteFromMM(int recipe_id) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement inner_statement1 = connection.prepareStatement("DELETE FROM MM_CATEGORY_RECIPE WHERE RECIPE_ID=?")) {
            inner_statement1.setInt(1, recipe_id);
            inner_statement1.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting recipe categories");
            e.printStackTrace();
        }
    }

    private void initCategoriesAndIngredients(Recipe entity, int recipe_id) {
        for (var i : entity.getCategories()) {
            try (Connection connection = DataSource.getConnection(); PreparedStatement inner_statement1 = connection.prepareStatement("INSERT INTO MM_CATEGORY_RECIPE (RECIPE_ID, CATEGORY_ID) VALUES(?, ?)")) {
                inner_statement1.setInt(1, recipe_id);
                inner_statement1.setInt(2, i.getId());
                inner_statement1.executeUpdate();
            } catch (SQLException e) {
                logger.error("Error adding recipe categories");
                e.printStackTrace();
            }
        }
        for (var i : entity.getIngredients()) {
            i.setRecipe_id(recipe_id);
            new IngredientDao().create(i);
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM RECIPE WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting recipe category");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean create(Recipe entity) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO RECIPE (TITLE, COOKING_TIME, DESCRIPTION, PHOTO, AUTHOR_ID) VALUES(?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setInt(2, entity.getCookingTime());
            preparedStatement.setString(3, entity.getText());
            preparedStatement.setBytes(4, entity.getImage() != null ? entity.getImage() : null);
            preparedStatement.setInt(5, entity.getAuthorId());
            preparedStatement.executeUpdate();
            int recipe_id = getMaxId();
            initCategoriesAndIngredients(entity, recipe_id);
        } catch (SQLException e) {
            logger.error("Error creating recipe category");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getMaxId() {
        try (Connection connection = DataSource.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) AS max_id FROM RECIPE");
            if (resultSet.next()) {
                return resultSet.getInt("max_id");
            }
        } catch (SQLException e) {
            logger.error("Error getting max recipe category id");
            e.printStackTrace();
        }
        return -1;
    }

    private void initRecipe(List<Recipe> recipes, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Recipe recipe = new Recipe();
            setRecipe(recipe, resultSet);
            recipes.add(recipe);
        }
    }

    public int getNumberOfRecipes(boolean moderated) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) RES FROM RECIPE WHERE MODERATED=?")) {
            statement.setBoolean(1, moderated);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) return resultSet.getInt("RES");
        } catch (SQLException e) {
            logger.error("Error getting number of recipes");
            e.printStackTrace();
        }
        return -1;
    }

    public int getNumberOfRecipesInCategory(int id) {
        String sql = "SELECT COUNT(*) RES FROM RECIPE WHERE MODERATED = TRUE AND ID IN " + "(SELECT RECIPE_ID ID FROM MM_CATEGORY_RECIPE WHERE CATEGORY_ID = ?)";
        return getAmount(id, sql);
    }

    public int getNumberOfRecipesWrittenBy(int id) {
        String sql = "SELECT COUNT(*) RES FROM RECIPE WHERE AUTHOR_ID = ?";
        return getAmount(id, sql);
    }

    private int getAmount(int id, String sql) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) return resultSet.getInt("RES");
        } catch (SQLException e) {
            logger.error("Error getting number of recipes with condition");
            e.printStackTrace();
        }
        return -1;
    }
}
