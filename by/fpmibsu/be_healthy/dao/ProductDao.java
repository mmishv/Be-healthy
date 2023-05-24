package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.Product;
import by.fpmibsu.be_healthy.postgres.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements Dao<Product> {
    private static final Logger logger = LogManager.getLogger(ProductDao.class);
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
            logger.error("Error getting all products");
            logger.trace(e);
            e.printStackTrace();
        }
        return products;
    }

    private void setProduct(ResultSet resultSet, Product product) throws SQLException {
        product.setId(resultSet.getInt("ID"));
        product.setName(resultSet.getString("NAME"));
        product.setCarbohydrates(resultSet.getDouble("CARBOHYDRATES"));
        product.setFats(resultSet.getDouble("FATS"));
        product.setProteins(resultSet.getDouble("PROTEINS"));
        product.setCalories(resultSet.getInt("CALORIES"));
        product.setUnit(resultSet.getString("UNIT"));
    }

    @Override
    public Product getEntityById(long id) {
        Product product = null;
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                setProduct(resultSet, product);
            }
        } catch (SQLException e) {
            logger.error("Error getting product by id");
            logger.trace(e);
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean update(Product entity) {
        if (entity == null)
            return false;
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PRODUCT SET NAME=?, CARBOHYDRATES=?, FATS=?, PROTEINS=?, CALORIES=?, UNIT=? WHERE ID=?")) {
            initCreateUpdate(entity, preparedStatement);
            preparedStatement.setInt(7, entity.getId());
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error updating product");
            logger.trace(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PRODUCT WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error deleting product");
            logger.trace(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean create(Product entity) {
        if (entity == null)
            return false;
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO PRODUCT (NAME, CARBOHYDRATES, FATS, PROTEINS, CALORIES, UNIT) VALUES( ?, ?, ?, ?, ?, ?)")) {
            initCreateUpdate(entity, preparedStatement);
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error creating product");
            logger.trace(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void initCreateUpdate(Product entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setDouble(2, entity.getCarbohydrates());
        preparedStatement.setDouble(3, entity.getFats());
        preparedStatement.setDouble(4, entity.getProteins());
        preparedStatement.setInt(5, entity.getCalories());
        preparedStatement.setString(6, entity.getUnit());
    }
}
