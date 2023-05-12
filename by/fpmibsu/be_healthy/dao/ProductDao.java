package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.Product;
import by.fpmibsu.be_healthy.postgres.DataSource;
import by.fpmibsu.be_healthy.postgres.JDBCPostgreSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends JDBCPostgreSQL implements Dao<Product> {

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT ORDER BY NAME");
            while (resultSet.next()) {
                Product product = new Product();
                setProduct(resultSet, product);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    private void setProduct(ResultSet resultSet, Product product) throws SQLException {
        product.setId(resultSet.getInt("ID"));
        product.setName(resultSet.getString("NAME"));
        product.setCarbohydrates(resultSet.getInt("CARBOHYDRATES"));
        product.setFats(resultSet.getInt("FATS"));
        product.setProteins(resultSet.getInt("PROTEINS"));
        product.setCalories(resultSet.getInt("CALORIES"));
        product.setUnit(resultSet.getString("UNIT"));
    }

    @Override
    public Product getEntityById(long id) {
        Product product = new Product();
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                setProduct(resultSet, product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean update(Product entity) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PRODUCT SET NAME=?, CARBOHYDRATES=?, FATS=?, PROTEINS=?, CALORIES=?, UNIT=? WHERE ID=?")) {
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
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Product entity) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PRODUCT WHERE ID=?")) {
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean create(Product entity) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUCT (ID, NAME, CARBOHYDRATES, FATS, PROTEINS, CALORIES, UNIT) VALUES(?, ?, ?, ?, ?, ?, ?)")) {
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
            return false;
        }
        return true;
    }
}
