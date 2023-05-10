package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.postgres.JDBCPostgreSQL;
import by.fpmibsu.be_healthy.entity.Meal;
import by.fpmibsu.be_healthy.services.MealProductService;

import java.sql.*;
import java.util.*;
import java.sql.Date;

public class MealDao extends JDBCPostgreSQL implements Dao<Meal> {
    private Connection connection = getConnection();

    @Override
    public List<Meal> getAll() throws SQLException {
        List<Meal> meals = new ArrayList<>();
        String sql = "SELECT * FROM MEAL";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            initMeal(meals, resultSet);
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
        return meals;
    }

    @Override
    public Meal getEntityById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM MEAL WHERE ID=?";
        Meal meal = new Meal();
        try {
            preparedStatement = connection.prepareStatement(sql);
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
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return meal;
    }

    @Override
    public boolean update(Meal entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE MEAL SET NAME=?, TIME=?  WHERE ID=?";
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
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
    public boolean delete(Meal entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM MEAL WHERE ID=?";
        return deleteWithId(preparedStatement, sql, connection, entity.getId(), entity);
    }

    static boolean deleteWithId(PreparedStatement preparedStatement, String sql, Connection connection, int id, Meal entity) throws SQLException {
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
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
    public boolean create(Meal entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO MEAL (USER_ID, NAME, TIME, DATE) VALUES(?, ?, ?, ?)";
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getUser_id());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setTime(3, entity.getTimeOfMeal());
            preparedStatement.setDate(4, (Date) entity.getDateOfMeal());
            preparedStatement.executeUpdate();
            int id =getMaxId();
            for (var i : entity.getProducts()) {
                i.setMeal_id(id);
                new MealProductService().create(i);
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
        return success;
    }
    public int getMaxId() {
        String sql = "SELECT max(id) as id FROM MEAL";
        Statement statement;
        return getId(sql, connection);
    }

    static int getId(String sql, Connection connection) {
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
    public List<Meal> getAllByDateAndUserId(Date date, int id) throws SQLException {
        List<Meal> meals = new ArrayList<>();
        String sql = "SELECT * FROM MEAL WHERE DATE = ? AND USER_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, date);
            statement.setInt(2, id);
            ResultSet resultSet = statement.executeQuery();
            initMeal(meals, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
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
