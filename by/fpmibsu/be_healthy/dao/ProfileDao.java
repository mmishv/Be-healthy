package by.fpmibsu.be_healthy.dao;


import by.fpmibsu.be_healthy.entity.*;
import by.fpmibsu.be_healthy.pg.JDBCPostgreSQL;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfileDao extends JDBCPostgreSQL implements Dao<Profile> {
    private Connection connection = getConnection();

    @Override
    public List<Profile> getAll() throws SQLException {
        List<Profile> profiles = new ArrayList<>();
        String sql = "SELECT * FROM PROFILE";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Profile profile = new Profile();
                profile.setId(resultSet.getInt("ID"));
                profile.setName(resultSet.getString("NAME"));
                profile.setEmail(resultSet.getString("EMAIL"));
                profile.setLogin(resultSet.getString("LOGIN"));
                profile.setPassword(resultSet.getString("PASSWORD"));
                var blob = resultSet.getBlob("AVATAR");
                if (blob != null)
                    profile.setAvatar(blob.getBytes(1l, (int) blob.length()));

                profile.setAge(resultSet.getInt("AGE"));
                profile.setHeight(resultSet.getInt("HEIGHT"));
                profile.setWeight(resultSet.getDouble("WEIGHT"));
                profile.setActivity(resultSet.getDouble("ACTIVITY_COEF"));
                profile.setWritten_articles(new ArticleDao().getWrittenArticlesByAuthorId(profile.getId()));
                profile.setWritten_recipes(new RecipeDao().getWrittenRecipesByUserId(profile.getId()));
                profile.setStarted_topics(new ForumTopicDao().getTopicsByAuthorId(profile.getId()));
                profile.setMeals(new MealDao().getMealsByUserId(profile.getId()));
                profile.setMessages(new ForumMessageDao().getMessagesByUserId(profile.getId()));
                profiles.add(profile);
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
        return profiles;
    }

    @Override
    public Profile getEntityById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM PROFILE WHERE ID=?";
        Profile profile = new Profile();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                profile.setId(resultSet.getInt("ID"));
                profile.setName(resultSet.getString("NAME"));
                profile.setEmail(resultSet.getString("EMAIL"));
                profile.setLogin(resultSet.getString("LOGIN"));
                profile.setPassword(resultSet.getString("PASSWORD"));
                var blob = resultSet.getBlob("AVATAR");
                if (blob != null)
                    profile.setAvatar(blob.getBytes(1l, (int) blob.length()));

                profile.setAge(resultSet.getInt("AGE"));
                profile.setHeight(resultSet.getInt("HEIGHT"));
                profile.setWeight(resultSet.getDouble("WEIGHT"));
                profile.setActivity(resultSet.getDouble("ACTIVITY_COEF"));
                profile.setWritten_articles(new ArticleDao().getWrittenArticlesByAuthorId(profile.getId()));
                profile.setWritten_recipes(new RecipeDao().getWrittenRecipesByUserId(profile.getId()));
                profile.setStarted_topics(new ForumTopicDao().getTopicsByAuthorId(profile.getId()));
                profile.setMeals(new MealDao().getMealsByUserId(profile.getId()));
                profile.setMessages(new ForumMessageDao().getMessagesByUserId(profile.getId()));
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
        return profile;
    }

    @Override
    public boolean update(Profile entity) throws SQLException {
        boolean success = true;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE PROFILE SET NAME=?, AVATAR=?, WEIGHT=?, ACTIVITY_COEF=? WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setBlob(2,
                    entity.getAvatar() != null ? new SerialBlob(entity.getAvatar()) : null);
            preparedStatement.setDouble(3, entity.getWeight());
            preparedStatement.setDouble(4, entity.getActivity());
            preparedStatement.setInt(5, entity.getId());
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
    public boolean delete(Profile entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM PROFILE WHERE ID=?";
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
    public boolean create(Profile entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO PROFILE (NAME, EMAIL, LOGIN, PASSWORD, AGE, HEIGHT, ACTIVITY_COEF, AVATAR, WEIGHT)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            //preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getLogin());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.setInt(5, entity.getAge());
            preparedStatement.setInt(6, entity.getHeight());
            preparedStatement.setDouble(7, entity.getActivity());
            preparedStatement.setBlob(8,
                    entity.getAvatar() != null ? new SerialBlob(entity.getAvatar()) : null);
            preparedStatement.setDouble(9, entity.getWeight());
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

    public boolean isProfileExist(String login, String password) throws SQLException {

        String sql = "SELECT * FROM PROFILE WHERE LOGIN=? AND PASSWORD=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }
    public boolean isLoginAvailable(String login) throws SQLException {
        String sql = "SELECT * FROM PROFILE WHERE LOGIN=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return true;
    }
    public boolean register(String login, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO PROFILE (LOGIN, PASSWORD) VALUES(?, ?)";
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
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

    public int getIdByLogin(String login) throws SQLException {
        String sql = "SELECT * FROM PROFILE WHERE LOGIN=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return -1;
    }
}
