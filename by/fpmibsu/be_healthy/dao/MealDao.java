package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.Meal;
import by.fpmibsu.be_healthy.postgres.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MealDao implements Dao<Integer, Meal> {
    private static final Logger logger = LogManager.getLogger(MealDao.class);

    @Override
    public List<Meal> getAll() {
        List<Meal> meals = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM MEAL ORDER BY DATE, TIME");
            initMeal(meals, resultSet);
        } catch (SQLException | ParseException e) {
            logger.error("Error getting all meals");
            logger.trace(e);
            e.printStackTrace();
        }
        return meals;
    }

    @Override
    public Meal getEntityById(Integer id) {
        Meal meal = null;
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM MEAL WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                meal = new Meal();
                setMeal(resultSet, meal);
            }
        } catch (SQLException | ParseException e) {
            logger.error("Error getting meal by id");
            logger.trace(e);
            e.printStackTrace();
        }
        return meal;
    }

    @Override
    public boolean update(Meal entity) {
        if (entity == null || entity.getProducts().isEmpty())
            return false;
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE MEAL SET NAME=?, TIME=?  WHERE ID=?")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setTime(2, entity.getTimeOfMeal());
            preparedStatement.setLong(3, entity.getId());
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error updating meal");
            logger.trace(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM MEAL WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error deleting meal");
            logger.trace(e);
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
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO MEAL (USER_ID, NAME, TIME, DATE) VALUES(?, ?, ?, ?)")) {
            preparedStatement.setInt(1, entity.getUser_id());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setTime(3, entity.getTimeOfMeal());
            preparedStatement.setDate(4, new java.sql.Date(entity.getDateOfMeal().getTime()));
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error creating meal");
            logger.trace(e);
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
            logger.error("Error getting max meal id");
            logger.trace(e);
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
            logger.trace(e);
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
    }
}
