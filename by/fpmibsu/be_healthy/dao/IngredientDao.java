package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.Ingredient;
import by.fpmibsu.be_healthy.entity.Product;
import by.fpmibsu.be_healthy.pg.JDBCPostgreSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientDao extends JDBCPostgreSQL implements Dao<Ingredient>{
    private Connection connection = getConnection();
    @Override
    public List<Ingredient> getAll() throws SQLException {
        List<Ingredient> products = new ArrayList<>();
        String sql = "SELECT * FROM INGREDIENT";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Ingredient product = new Ingredient(new ProductDao().getEntityById(resultSet.getInt("PRODUCT_ID")));
                product.setIngredientId(resultSet.getInt("ID"));
                product.setQuantity(resultSet.getInt("QUANTITY"));
                product.setRecipe_id(resultSet.getInt("RECIPE_ID"));
                products.add(product);
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
        return products;
    }
    @Override
    public Ingredient getEntityById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM INGREDIENT WHERE ID=?";
        Ingredient product = new Ingredient();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                product = new Ingredient(new ProductDao().getEntityById(resultSet.getInt("PRODUCT_ID")));
                product.setIngredientId(resultSet.getInt("ID"));
                product.setQuantity(resultSet.getInt("QUANTITY"));
                product.setRecipe_id(resultSet.getInt("RECIPE_ID"));
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
        return product;
    }

    @Override
    public boolean update(Ingredient entity) throws SQLException {
        boolean success = true;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE INGREDIENT SET RECIPE_ID=?, PRODUCT_ID=?, QUANTITY=? WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getRecipe_id());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.setInt(3, entity.getQuantity());
            preparedStatement.setInt(4, entity.getIngredientId());
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
    public boolean delete(Ingredient entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM INGREDIENT WHERE ID=?";
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
    public boolean create(Ingredient entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO INGREDIENT (ID, RECIPE_ID, PRODUCT_ID, QUANTITY) VALUES(?, ?, ?, ?)";
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getIngredientId());
            preparedStatement.setInt(2, entity.getRecipe_id());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.setInt(4, entity.getQuantity());
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
