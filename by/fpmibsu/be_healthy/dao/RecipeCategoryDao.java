package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.RecipeCategory;
import by.fpmibsu.be_healthy.postgres.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeCategoryDao implements Dao<Integer, RecipeCategory> {

    private static final Logger logger = LogManager.getLogger(ProfileDao.class);

    @Override
    public List<RecipeCategory> getAll() {
        List<RecipeCategory> categories = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT ID, NAME FROM RECIPE_CATEGORY ORDER BY NAME");
            while (resultSet.next()) {
                RecipeCategory category = new RecipeCategory();
                category.setId(resultSet.getInt("ID"));
                category.setName(resultSet.getString("NAME"));
                categories.add(category);
            }
        } catch (SQLException e) {
            logger.error("Error getting all recipe categories");
            logger.trace(e);
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public RecipeCategory getEntityById(Integer id) {
        RecipeCategory category = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID, NAME FROM RECIPE_CATEGORY WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category = new RecipeCategory();
                category.setId(resultSet.getInt("ID"));
                category.setName(resultSet.getString("NAME"));
            }
        } catch (SQLException e) {
            logger.error("Error getting recipe category by id");
            logger.trace(e);
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public boolean update(RecipeCategory entity) {
        if (entity == null)
            return false;
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE RECIPE_CATEGORY SET NAME=? WHERE ID=?")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setLong(2, entity.getId());
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error updating recipe category");
            logger.trace(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM RECIPE_CATEGORY WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error deleting recipe category");
            logger.trace(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean create(RecipeCategory entity) {
        if (entity == null)
            return false;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO RECIPE_CATEGORY (NAME) VALUES(?)")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error creating recipe category");
            logger.trace(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<RecipeCategory> getRecipeCategoriesByRecipeId(int id) {
        List<RecipeCategory> categories = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT CATEGORY_ID AS ID FROM MM_CATEGORY_RECIPE WHERE RECIPE_ID=?")) {
            statement.setInt(1, id);
            ResultSet categoriesIds = statement.executeQuery();
            while (categoriesIds.next()) {
                categories.add(new RecipeCategoryDao().getEntityById(categoriesIds.getInt("ID")));
            }
        } catch (SQLException e) {
            logger.error("Error getting recipe categories by recipe id");
            logger.trace(e);
            e.printStackTrace();
        }
        return categories;
    }
}