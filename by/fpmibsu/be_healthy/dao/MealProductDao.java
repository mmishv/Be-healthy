package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.RecipeCategory;
import by.fpmibsu.be_healthy.pg.JDBCPostgreSQL;
import by.fpmibsu.be_healthy.entity.MealProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MealProductDao extends JDBCPostgreSQL implements Dao<MealProduct>{
    private Connection connection = getConnection();
    @Override
    public List<MealProduct> getAll() throws SQLException {
        List<MealProduct> products = new ArrayList<>();
        String sql = "SELECT * FROM MEAL_PRODUCT";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                MealProduct product = new MealProduct(new ProductDao().getEntityById(resultSet.getInt("PRODUCT_ID")));
                product.setMealProductId(resultSet.getInt("ID"));
                product.setQuantity(resultSet.getInt("QUANTITY"));
                product.setMeal_id(resultSet.getInt("MEAL_ID"));
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
    public MealProduct getEntityById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM MEAL_PRODUCT WHERE ID=?";
        MealProduct product = new MealProduct();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                product = new MealProduct(new ProductDao().getEntityById(resultSet.getInt("PRODUCT_ID")));
                product.setMealProductId(resultSet.getInt("ID"));
                product.setQuantity(resultSet.getInt("QUANTITY"));
                product.setMeal_id(resultSet.getInt("MEAL_ID"));
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
    public boolean update(MealProduct entity) throws SQLException {
        boolean success = true;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE MEAL_PRODUCT SET MEAL_ID=?, PRODUCT_ID=?, QUANTITY=? WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getMeal_id());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.setInt(3, entity.getQuantity());
            preparedStatement.setInt(4, entity.getMealProductId());
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
    public boolean delete(MealProduct entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM MEAL_PRODUCT WHERE ID=?";
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
    public boolean create(MealProduct entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO MEAL_PRODUCT (ID, MEAL_ID, PRODUCT_ID, QUANTITY) VALUES(?, ?, ?, ?)";
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getMealProductId());
            preparedStatement.setInt(2, entity.getMeal_id());
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
