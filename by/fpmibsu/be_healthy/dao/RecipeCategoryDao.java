package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.postgres.JDBCPostgreSQL;
import by.fpmibsu.be_healthy.entity.RecipeCategory;

import java.sql.*;
import java.util.*;

public class RecipeCategoryDao extends JDBCPostgreSQL implements Dao<RecipeCategory> {
    private final Connection connection = getConnection();

    @Override
    public List<RecipeCategory> getAll() {
        List<RecipeCategory> categories = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT ID, NAME FROM RECIPE_CATEGORY ORDER BY NAME");
            while (resultSet.next()) {
                RecipeCategory category = new RecipeCategory();
                category.setId(resultSet.getInt("ID"));
                category.setName(resultSet.getString("NAME"));
                categories.add(category);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public RecipeCategory getEntityById(long id) {
        RecipeCategory category = new RecipeCategory();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID, NAME FROM RECIPE_CATEGORY WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category.setId(resultSet.getInt("ID"));
                category.setName(resultSet.getString("NAME"));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public boolean update(RecipeCategory entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE RECIPE_CATEGORY SET NAME=? WHERE ID=?")) {
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
    public boolean delete(RecipeCategory entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM RECIPE_CATEGORY WHERE ID=?")) {
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
    public boolean create(RecipeCategory entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO RECIPE_CATEGORY (ID, NAME) VALUES(?, ?)")) {
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<RecipeCategory> getCategoriesByRecipeId(int id) {
        List<RecipeCategory> categories = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT CATEGORY_ID AS ID FROM MM_CATEGORY_RECIPE WHERE RECIPE_ID=?")) {
            statement.setInt(1, id);
            ResultSet categoriesIds = statement.executeQuery();
            while (categoriesIds.next()) {
                categories.add(new RecipeCategoryDao().getEntityById(categoriesIds.getInt("ID")));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}