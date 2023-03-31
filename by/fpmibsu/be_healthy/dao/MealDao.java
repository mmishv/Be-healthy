package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.pg.JDBCPostgreSQL;
import by.fpmibsu.be_healthy.entity.Meal;
import by.fpmibsu.be_healthy.entity.MealProduct;

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
            PreparedStatement inner_statement = null;
            while (resultSet.next()) {
                Meal meal = new Meal();
                meal.setId(resultSet.getInt("ID"));
                meal.setUser_id(resultSet.getInt("USER_ID"));
                meal.setName(resultSet.getString("NAME"));
                meal.setTimeOfMeal(resultSet.getTime("TIME"));
                meal.setDateOfMeal(resultSet.getDate("DATE"));

                String inner_sql = "SELECT ID FROM MEAL_PRODUCT WHERE MEAL_ID=?";
                try{
                inner_statement = connection.prepareStatement(inner_sql);
                inner_statement.setInt(1, meal.getId());
                ResultSet messagesIds = inner_statement.executeQuery();
                List<MealProduct> products = new ArrayList<>();
                while (messagesIds.next()){
                    products.add(new MealProductDao().getEntityById(messagesIds.getInt("ID")));
                }
                meal.setProducts(products);
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally{
                    if (inner_statement != null) {
                        inner_statement.close();
                    }
                }
                meals.add(meal);
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
        return meals;
    }

    @Override
    public Meal getEntityById(long id) throws SQLException {
        PreparedStatement preparedStatement = null, inner_statement = null;
        String sql = "SELECT * FROM MEAL WHERE ID=?";
        Meal meal = new Meal();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
            meal.setId(resultSet.getInt("ID"));
            meal.setUser_id(resultSet.getInt("USER_ID"));
            meal.setName(resultSet.getString("NAME"));
            meal.setTimeOfMeal(resultSet.getTime("TIME"));
            meal.setDateOfMeal(resultSet.getTime("DATE"));

            String inner_sql = "SELECT ID FROM MEAL_PRODUCT WHERE MEAL_ID=?";
            try{
                inner_statement = connection.prepareStatement(inner_sql);
                inner_statement.setInt(1, meal.getId());
                ResultSet messagesIds = inner_statement.executeQuery();
                List<MealProduct> products = new ArrayList<>();
                while (messagesIds.next()){
                    products.add(new MealProductDao().getEntityById(messagesIds.getInt("ID")));
                }
                meal.setProducts(products);
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                if (inner_statement != null) {
                    inner_statement.close();
                }
            }
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
        String sql = "UPDATE MEAL SET NAME=?, TIME=?, DATE=?, USER_ID=? WHERE ID=?";
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setTime(2, entity.getTimeOfMeal());
            preparedStatement.setDate(3, (Date)entity.getDateOfMeal());
            preparedStatement.setLong(4, entity.getUser_id());
            preparedStatement.setLong(5, entity.getId());
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
    public boolean delete(Meal entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM MEAL WHERE ID=?";
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
    public boolean create(Meal entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO MEAL (ID, USER_ID, NAME, TIME, DATE) VALUES(?, ?, ?, ?, ?)";
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setInt(2, entity.getUser_id());
            preparedStatement.setString(3, entity.getName());
            preparedStatement.setTime(4, entity.getTimeOfMeal());
            preparedStatement.setDate(5, (Date)entity.getDateOfMeal());

            preparedStatement.executeUpdate();
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
}
