package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.postgres.DataSource;
import by.fpmibsu.be_healthy.entity.Meal;
import by.fpmibsu.be_healthy.service.MealProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class MealDao implements Dao<Meal> {
    private static final Logger logger = LogManager.getLogger(MealDao.class);

    @Override
    public List<Meal> getAll() {
        List<Meal> meals = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MEAL ORDER BY DATE, TIME");
            initMeal(meals, resultSet);
        } catch (SQLException | ParseException e) {
            logger.error("Error getting all meals");
            e.printStackTrace();
        }
        return meals;
    }

    @Override
    public Meal getEntityById(long id) {
        Meal meal = null;
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM MEAL WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                meal = new Meal();
                setMeal(resultSet, meal);
            }
        } catch (SQLException | ParseException e) {
            logger.error("Error getting meal by id");
            e.printStackTrace();
        }
        return meal;
    }

    @Override
    public boolean update(Meal entity) {
        if (entity == null || entity.getProducts().isEmpty())
            return false;
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE MEAL SET NAME=?, TIME=?  WHERE ID=?")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setTime(2, entity.getTimeOfMeal());
            preparedStatement.setLong(3, entity.getId());
            if (preparedStatement.executeUpdate()==0)
                return false;
            new MealProductDao().deleteMealProducts(entity.getId());
            for (var i : entity.getProducts()) {
                i.setMealId(entity.getId());
                new MealProductService().create(i);
            }
        } catch (SQLException e) {
            logger.error("Error updating meal");
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
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error deleting meal");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean create(Meal entity) {
        if (entity == null || entity.getProducts().isEmpty())
            return false;
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO MEAL (USER_ID, NAME, TIME, DATE) VALUES(?, ?, ?, ?)")) {
            preparedStatement.setInt(1, entity.getUser_id());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setTime(3, entity.getTimeOfMeal());
            preparedStatement.setDate(4, new java.sql.Date(entity.getDateOfMeal().getDate()));
            if (preparedStatement.executeUpdate()==0)
                return false;
            int id = getMaxId();
            for (var i : entity.getProducts()) {
                i.setMealId(id);
                new MealProductService().create(i);
            }
        } catch (SQLException e) {
            logger.error("Error creating meal");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private int getMaxId() {
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) AS id FROM MEAL");
            if (resultSet.next()) {
                return resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            logger.error("Error getting max meal id");
            e.printStackTrace();
        }
        return -1;
    }

    public List<Meal> getAllByDateAndUserId(Date date, int id) {
        List<Meal> meals = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM MEAL WHERE DATE = ? AND USER_ID = ? ORDER BY DATE, TIME")) {
            statement.setDate(1, (java.sql.Date) date);
            statement.setInt(2, id);
            ResultSet resultSet = statement.executeQuery();
            initMeal(meals, resultSet);
        } catch (SQLException | ParseException e) {
            logger.error("Error getting all user's meals by date");
            e.printStackTrace();
        }
        return meals;
    }

    private void initMeal(List<Meal> meals, ResultSet resultSet) throws SQLException, ParseException {
        while (resultSet.next()) {
            Meal meal = new Meal();
            setMeal(resultSet, meal);
            meals.add(meal);
        }
    }

    private void setMeal(ResultSet resultSet, Meal meal) throws SQLException, ParseException {
        meal.setId(resultSet.getInt("ID"));
        meal.setUser_id(resultSet.getInt("USER_ID"));
        meal.setName(resultSet.getString("NAME"));
        meal.setTimeOfMeal(new Time(new SimpleDateFormat("HH:mm:ss").
                parse(resultSet.getTime("TIME").toString()).getTime()));
        meal.setDateOfMeal(new SimpleDateFormat("y-M-d").parse(resultSet.getDate("DATE").toString()));
        meal.setProducts(new MealProductDao().getProductsByMealId(meal.getId()));
    }
}
