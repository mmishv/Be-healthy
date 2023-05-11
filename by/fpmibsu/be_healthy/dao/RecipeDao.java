package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.Recipe;
import by.fpmibsu.be_healthy.postgres.JDBCPostgreSQL;
import by.fpmibsu.be_healthy.services.IngredientService;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;

public class RecipeDao extends JDBCPostgreSQL implements Dao<Recipe> {
    private final Connection connection = getConnection();

    @Override
    public List<Recipe> getAll() {
        List<Recipe> recipes = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM RECIPE ORDER BY PUBL_DATE DESC");
            initRecipe(recipes, resultSet);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    public List<Recipe> getPage(int page, int per_page) {
        List<Recipe> recipes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM RECIPE ORDER BY PUBL_DATE DESC LIMIT ? OFFSET ?")) {
            statement.setInt(1, per_page);
            statement.setInt(2, per_page * (page - 1));
            ResultSet resultSet = statement.executeQuery();
            initRecipe(recipes, resultSet);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    public List<Recipe> getCategoryPage(int page, int per_page, int category_id) {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM RECIPE WHERE ID IN (SELECT RECIPE_ID ID " + "FROM MM_CATEGORY_RECIPE WHERE CATEGORY_ID = ?)" + "ORDER BY PUBL_DATE DESC LIMIT ? OFFSET ?";
        return getPage(page, per_page, category_id, recipes, sql);
    }

    public List<Recipe> getAuthorPage(int page, int per_page, int author_id) {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM RECIPE WHERE AUTHOR_ID = ?" + "ORDER BY PUBL_DATE DESC LIMIT ? OFFSET ?";
        return getPage(page, per_page, author_id, recipes, sql);
    }

    private List<Recipe> getPage(int page, int per_page, int author_id, List<Recipe> recipes, String sql) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, author_id);
            statement.setInt(2, per_page);
            statement.setInt(3, per_page * (page - 1));
            ResultSet resultSet = statement.executeQuery();
            initRecipe(recipes, resultSet);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    @Override
    public Recipe getEntityById(long id) {
        Recipe recipe = new Recipe();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM RECIPE WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                setRecipe(recipe, resultSet);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
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

        byte[] encodeBase64 = Base64.getEncoder().encode(resultSet.getBytes("PHOTO"));
        String base64encoded = new String(encodeBase64, StandardCharsets.UTF_8);
        recipe.setBase64image(base64encoded);

        recipe.setCategories(new RecipeCategoryDao().getCategoriesByRecipeId(recipe.getId()));
        recipe.setIngredients(new IngredientDao().getIngredientsByRecipeId(recipe.getId()));
    }

    @Override
    public boolean update(Recipe entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE RECIPE SET TITLE=?, PUBL_DATE=?, COOKING_TIME=?, DESCRIPTION=?, PHOTO=?, AUTHOR_ID=? WHERE ID=?")) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setDate(2, (Date) (entity.getDateOfPublication()));
            preparedStatement.setInt(3, entity.getCookingTime());
            preparedStatement.setString(4, entity.getText());
            preparedStatement.setBytes(5, entity.getImage() != null ? entity.getImage() : null);
            preparedStatement.setInt(6, entity.getAuthorId());
            preparedStatement.setInt(7, entity.getId());
            preparedStatement.executeUpdate();
            new IngredientService().deleteRecipeIngredients(entity.getId());
            int recipe_id = entity.getId();
            PreparedStatement inner_statement1 = connection.prepareStatement("DELETE FROM MM_CATEGORY_RECIPE WHERE RECIPE_ID=?");
            inner_statement1.setInt(1, recipe_id);
            inner_statement1.executeUpdate();
            initCategoriesAndIngredients(entity, recipe_id);
            preparedStatement.close();
            inner_statement1.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void initCategoriesAndIngredients(Recipe entity, int recipe_id) {
        for (var i : entity.getCategories()) {
            try (PreparedStatement inner_statement1 = connection.prepareStatement("INSERT INTO MM_CATEGORY_RECIPE (RECIPE_ID, CATEGORY_ID) VALUES(?, ?)")) {
                inner_statement1.setInt(1, recipe_id);
                inner_statement1.setInt(2, i.getId());
                inner_statement1.executeUpdate();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for (var i : entity.getIngredients()) {
            i.setRecipe_id(recipe_id);
            new IngredientDao().create(i);
        }
    }

    @Override
    public boolean delete(Recipe entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM RECIPE WHERE ID=?")) {
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
    public boolean create(Recipe entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO RECIPE (TITLE, COOKING_TIME, DESCRIPTION, PHOTO, AUTHOR_ID) VALUES(?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setInt(2, entity.getCookingTime());
            preparedStatement.setString(3, entity.getText());
            preparedStatement.setBytes(4, entity.getImage() != null ? entity.getImage() : null);
            preparedStatement.setInt(5, entity.getAuthorId());
            preparedStatement.executeUpdate();
            int recipe_id = getMaxId();
            initCategoriesAndIngredients(entity, recipe_id);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getMaxId() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) AS max_id FROM RECIPE");
            if (resultSet.next()) {
                return resultSet.getInt("max_id");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Recipe> getAllInCategory(int id) {
        List<Recipe> recipes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM RECIPE WHERE ID IN " + "(SELECT RECIPE_ID ID FROM MM_CATEGORY_RECIPE WHERE CATEGORY_ID = ?)" + "ORDER BY PUBL_DATE DESC")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            initRecipe(recipes, resultSet);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    private void initRecipe(List<Recipe> recipes, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Recipe recipe = new Recipe();
            setRecipe(recipe, resultSet);
            recipes.add(recipe);
        }
    }

    public int getNumberOfRecipes() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) RES FROM RECIPE");
            if (resultSet.next()) {
                return resultSet.getInt("RES");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getNumberOfRecipesInCategory(int id) {
        String sql = "SELECT COUNT(*) RES FROM RECIPE WHERE ID IN" + "(SELECT RECIPE_ID ID FROM MM_CATEGORY_RECIPE WHERE CATEGORY_ID = ?)";
        return getAmount(id, sql);
    }

    public int getNumberOfRecipesWrittenBy(int id) {
        String sql = "SELECT COUNT(*) RES FROM RECIPE WHERE AUTHOR_ID = ?";
        return getAmount(id, sql);
    }

    private int getAmount(int id, String sql) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) return resultSet.getInt("RES");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
