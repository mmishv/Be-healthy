package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.postgres.DataSource;
import by.fpmibsu.be_healthy.entity.MealProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MealProductDao implements Dao<MealProduct> {

    @Override
    public List<MealProduct> getAll() {
        List<MealProduct> products = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MEAL_PRODUCT");
            while (resultSet.next()) {
                MealProduct product = new MealProduct(new ProductDao().getEntityById(resultSet.getInt("PRODUCT_ID")));
                product.setMealProductId(resultSet.getInt("ID"));
                product.setQuantity(resultSet.getInt("QUANTITY"));
                product.setMeal_id(resultSet.getInt("MEAL_ID"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public MealProduct getEntityById(long id) {
        MealProduct product = new MealProduct();
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM MEAL_PRODUCT WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new MealProduct(new ProductDao().getEntityById(resultSet.getInt("PRODUCT_ID")));
                product.setMealProductId(resultSet.getInt("ID"));
                product.setQuantity(resultSet.getInt("QUANTITY"));
                product.setMeal_id(resultSet.getInt("MEAL_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean update(MealProduct entity) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE MEAL_PRODUCT SET MEAL_ID=?, PRODUCT_ID=?, QUANTITY=? WHERE ID=?")) {
            preparedStatement.setInt(1, entity.getMeal_id());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.setInt(3, entity.getQuantity());
            preparedStatement.setInt(4, entity.getMealProductId());
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
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM MEAL_PRODUCT WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteMealProducts(int id) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM MEAL_PRODUCT WHERE MEAL_ID=?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean create(MealProduct entity) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO MEAL_PRODUCT (MEAL_ID, PRODUCT_ID, QUANTITY) VALUES(?, ?, ?)")) {
            preparedStatement.setInt(1, entity.getMeal_id());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.setInt(3, entity.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<MealProduct> getProductsByMealId(int id) {
        List<MealProduct> products = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT ID FROM MEAL_PRODUCT WHERE MEAL_ID=?")) {
            statement.setInt(1, id);
            ResultSet productsIds = statement.executeQuery();
            while (productsIds.next()) {
                products.add(new MealProductDao().getEntityById(productsIds.getInt("ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
