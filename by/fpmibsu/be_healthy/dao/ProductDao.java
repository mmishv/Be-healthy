package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.MealProduct;
import by.fpmibsu.be_healthy.entity.Product;
import by.fpmibsu.be_healthy.pg.JDBCPostgreSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends JDBCPostgreSQL implements Dao<Product>{
    private Connection connection = getConnection();
    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCT ORDER BY NAME";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("ID"));
                product.setName(resultSet.getString("NAME"));
                product.setCarbohydrates(resultSet.getInt("CARBOHYDRATES"));
                product.setFats(resultSet.getInt("FATS"));
                product.setProteins(resultSet.getInt("PROTEINS"));
                product.setCalories(resultSet.getInt("CALORIES"));
                product.setUnit(resultSet.getString("UNIT"));
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
    public Product getEntityById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM PRODUCT WHERE ID=?";
        Product product = new Product();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                product.setId(resultSet.getInt("ID"));
                product.setName(resultSet.getString("NAME"));
                product.setCarbohydrates(resultSet.getInt("CARBOHYDRATES"));
                product.setFats(resultSet.getInt("FATS"));
                product.setProteins(resultSet.getInt("PROTEINS"));
                product.setCalories(resultSet.getInt("CALORIES"));
                product.setUnit(resultSet.getString("UNIT"));
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
    public boolean update(Product entity) throws SQLException {
        boolean success = true;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE PRODUCT SET NAME=?, CARBOHYDRATES=?, FATS=?, PROTEINS=?, CALORIES=?, UNIT=? WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDouble(2, entity.getCarbohydrates());
            preparedStatement.setDouble(3, entity.getFats());
            preparedStatement.setDouble(4, entity.getProteins());
            preparedStatement.setInt(5, entity.getCalories());
            preparedStatement.setString(6, entity.getUnit());
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
    public boolean delete(Product entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM PRODUCT WHERE ID=?";
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
    public boolean create(Product entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO PRODUCT (ID, NAME, CARBOHYDRATES, FATS, PROTEINS, CALORIES, UNIT) VALUES(?, ?, ?, ?, ?, ?, ?)";
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setDouble(3, entity.getCarbohydrates());
            preparedStatement.setDouble(4, entity.getFats());
            preparedStatement.setDouble(5, entity.getProteins());
            preparedStatement.setInt(6, entity.getCalories());
            preparedStatement.setString(7, entity.getUnit());
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
