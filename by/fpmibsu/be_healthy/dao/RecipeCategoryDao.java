package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.postgres.DataSource;
import by.fpmibsu.be_healthy.entity.RecipeCategory;

import java.sql.*;
import java.util.*;

public class RecipeCategoryDao implements Dao<RecipeCategory> {

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
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public RecipeCategory getEntityById(long id) {
        RecipeCategory category = new RecipeCategory();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID, NAME FROM RECIPE_CATEGORY WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category.setId(resultSet.getInt("ID"));
                category.setName(resultSet.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public boolean update(RecipeCategory entity) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE RECIPE_CATEGORY SET NAME=? WHERE ID=?")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setLong(2, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM RECIPE_CATEGORY WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean create(RecipeCategory entity) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO RECIPE_CATEGORY (ID, NAME) VALUES(?, ?)")) {
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<RecipeCategory> getCategoriesByRecipeId(int id) {
        List<RecipeCategory> categories = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT CATEGORY_ID AS ID FROM MM_CATEGORY_RECIPE WHERE RECIPE_ID=?")) {
            statement.setInt(1, id);
            ResultSet categoriesIds = statement.executeQuery();
            while (categoriesIds.next()) {
                categories.add(new RecipeCategoryDao().getEntityById(categoriesIds.getInt("ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}