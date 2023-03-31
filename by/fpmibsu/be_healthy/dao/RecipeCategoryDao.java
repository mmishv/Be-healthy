package by.fpmibsu.be_healthy.dao;
import by.fpmibsu.be_healthy.pg.JDBCPostgreSQL;
import by.fpmibsu.be_healthy.entity.RecipeCategory;

import java.sql.*;
import java.util.*;

public class RecipeCategoryDao extends JDBCPostgreSQL implements Dao<RecipeCategory> {
    private Connection connection = getConnection();
    @Override
    public List<RecipeCategory> getAll() throws SQLException {
        List<RecipeCategory> categories = new ArrayList<>();
        String sql = "SELECT ID, NAME FROM RECIPE_CATEGORY";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                RecipeCategory category = new RecipeCategory();
                category.setId(resultSet.getInt("ID"));
                category.setName(resultSet.getString("NAME"));
                categories.add(category);
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
        return categories;
    }
    @Override
    public RecipeCategory getEntityById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT ID, NAME FROM RECIPE_CATEGORY WHERE ID=?";
        RecipeCategory category = new RecipeCategory();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
            category.setId(resultSet.getInt("ID"));
            category.setName(resultSet.getString("NAME"));
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
        return category;
    }

    @Override
    public boolean update(RecipeCategory entity) throws SQLException {
        boolean success = true;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE RECIPE_CATEGORY SET NAME=? WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setLong(2, entity.getId());
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
    public boolean delete(RecipeCategory entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM RECIPE_CATEGORY WHERE ID=?";
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
    public boolean create(RecipeCategory entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO RECIPE_CATEGORY (ID, NAME) VALUES(?, ?)";
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
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
}