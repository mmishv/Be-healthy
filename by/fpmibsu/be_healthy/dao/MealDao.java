package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.postgres.DataSource;
import by.fpmibsu.be_healthy.entity.Meal;
import by.fpmibsu.be_healthy.services.MealProductService;

import java.sql.*;
import java.util.*;
import java.sql.Date;

public class MealDao implements Dao<Meal> {

    @Override
    public List<Meal> getAll() {
        List<Meal> meals = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MEAL");
            initMeal(meals, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meals;
    }

    @Override
    public Meal getEntityById(long id) {
        Meal meal = new Meal();
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM MEAL WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                meal.setId(resultSet.getInt("ID"));
                meal.setUser_id(resultSet.getInt("USER_ID"));
                meal.setName(resultSet.getString("NAME"));
                meal.setTimeOfMeal(resultSet.getTime("TIME"));
                meal.setDateOfMeal(resultSet.getTime("DATE"));
                meal.setProducts(new MealProductDao().getProductsByMealId(meal.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meal;
    }

    @Override
    public boolean update(Meal entity) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE MEAL SET NAME=?, TIME=?  WHERE ID=?")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setTime(2, entity.getTimeOfMeal());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.executeUpdate();
            new MealProductDao().deleteMealProducts(entity.getId());
            for (var i : entity.getProducts()) {
                i.setMeal_id(entity.getId());
                new MealProductService().create(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM MEAL WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean create(Meal entity) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO MEAL (USER_ID, NAME, TIME, DATE) VALUES(?, ?, ?, ?)")) {
            preparedStatement.setInt(1, entity.getUser_id());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setTime(3, entity.getTimeOfMeal());
            preparedStatement.setDate(4, (Date) entity.getDateOfMeal());
            preparedStatement.executeUpdate();
            int id = getMaxId();
            for (var i : entity.getProducts()) {
                i.setMeal_id(id);
                new MealProductService().create(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getMaxId() {
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) AS id FROM MEAL");
            if (resultSet.next()) {
                return resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public List<Meal> getAllByDateAndUserId(Date date, int id) {
        List<Meal> meals = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM MEAL WHERE DATE = ? AND USER_ID = ?")) {
            statement.setDate(1, date);
            statement.setInt(2, id);
            ResultSet resultSet = statement.executeQuery();
            initMeal(meals, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meals;
    }

    private void initMeal(List<Meal> meals, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Meal meal = new Meal();
            meal.setId(resultSet.getInt("ID"));
            meal.setUser_id(resultSet.getInt("USER_ID"));
            meal.setName(resultSet.getString("NAME"));
            meal.setTimeOfMeal(resultSet.getTime("TIME"));
            meal.setDateOfMeal(resultSet.getDate("DATE"));
            meal.setProducts(new MealProductDao().getProductsByMealId(meal.getId()));
            meals.add(meal);
        }
    }
}
