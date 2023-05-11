package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.postgres.JDBCPostgreSQL;
import by.fpmibsu.be_healthy.entity.MealProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MealProductDao extends JDBCPostgreSQL implements Dao<MealProduct> {
    private final Connection connection = getConnection();

    @Override
    public List<MealProduct> getAll() {
        List<MealProduct> products = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MEAL_PRODUCT");
            while (resultSet.next()) {
                MealProduct product = new MealProduct(new ProductDao().getEntityById(resultSet.getInt("PRODUCT_ID")));
                product.setMealProductId(resultSet.getInt("ID"));
                product.setQuantity(resultSet.getInt("QUANTITY"));
                product.setMeal_id(resultSet.getInt("MEAL_ID"));
                products.add(product);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public MealProduct getEntityById(long id) {
        MealProduct product = new MealProduct();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM MEAL_PRODUCT WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new MealProduct(new ProductDao().getEntityById(resultSet.getInt("PRODUCT_ID")));
                product.setMealProductId(resultSet.getInt("ID"));
                product.setQuantity(resultSet.getInt("QUANTITY"));
                product.setMeal_id(resultSet.getInt("MEAL_ID"));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean update(MealProduct entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE MEAL_PRODUCT SET MEAL_ID=?, PRODUCT_ID=?, QUANTITY=? WHERE ID=?")) {
            preparedStatement.setInt(1, entity.getMeal_id());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.setInt(3, entity.getQuantity());
            preparedStatement.setInt(4, entity.getMealProductId());
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
    public boolean delete(MealProduct entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM MEAL_PRODUCT WHERE ID=?")) {
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

    public boolean deleteMealProducts(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM MEAL_PRODUCT WHERE MEAL_ID=?")) {
            preparedStatement.setInt(1, id);
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
    public boolean create(MealProduct entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO MEAL_PRODUCT (MEAL_ID, PRODUCT_ID, QUANTITY) VALUES(?, ?, ?)")) {
            preparedStatement.setInt(1, entity.getMeal_id());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.setInt(3, entity.getQuantity());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<MealProduct> getProductsByMealId(int id) {
        List<MealProduct> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT ID FROM MEAL_PRODUCT WHERE MEAL_ID=?")) {
            statement.setInt(1, id);
            ResultSet productsIds = statement.executeQuery();
            while (productsIds.next()) {
                products.add(new MealProductDao().getEntityById(productsIds.getInt("ID")));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
