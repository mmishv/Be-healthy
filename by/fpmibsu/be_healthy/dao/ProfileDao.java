package by.fpmibsu.be_healthy.dao;



import by.fpmibsu.be_healthy.entity.*;
import by.fpmibsu.be_healthy.pg.JDBCPostgreSQL;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfileDao extends JDBCPostgreSQL implements Dao<Profile>{
        private Connection connection = getConnection();
        @Override
        public List<Profile> getAll() throws SQLException {
            List<Profile> profiles = new ArrayList<>();
            String sql = "SELECT * FROM PROFILE";
            Statement statement = null;
            PreparedStatement inner_statement1 = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                Profile profile = new Profile();
                while (resultSet.next()) {
                    profile.setId(resultSet.getInt("ID"));
                    profile.setName(resultSet.getString("NAME"));
                    profile.setEmail(resultSet.getString("EMAIL"));
                    profile.setLogin(resultSet.getString("LOGIN"));
                    profile.setPassword(resultSet.getString("PASSWORD"));
                    var blob = resultSet.getBlob("PHOTO");
                    if (blob!=null)
                        profile.setAvatar(blob.getBytes(1l, (int)blob.length()));

                    profile.setAge(resultSet.getInt("AGE"));
                    profile.setHeight(resultSet.getInt("HEIGHT"));
                    profile.setWeight(resultSet.getDouble("WEIGHT"));
                    profile.setActivity(resultSet.getDouble("ACTIVITY_COEF"));

                    String inner_sql = "SELECT ID FROM ARTICLE WHERE AUTHOR_ID=?";
                    try{
                        inner_statement1 = connection.prepareStatement(inner_sql);
                        inner_statement1.setInt(1, profile.getId());
                        ResultSet articlesIds = inner_statement1.executeQuery();
                        List<Article> articles = new ArrayList<>();
                        while (articlesIds.next()){
                            articles.add(new ArticleDao().getEntityById(articlesIds.getInt("ID")));
                        }
                        profile.setWritten_articles(articles);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }finally{
                        if (inner_statement1 != null) {
                            inner_statement1.close();
                        }
                    }

                    inner_sql = "SELECT ID FROM RECIPE WHERE AUTHOR_ID=?";
                    try{
                        inner_statement1 = connection.prepareStatement(inner_sql);
                        inner_statement1.setInt(1, profile.getId());
                        ResultSet recipesIds = inner_statement1.executeQuery();
                        List<Recipe> recipes = new ArrayList<>();
                        while (recipesIds.next()){
                            recipes.add(new RecipeDao().getEntityById(recipesIds.getInt("ID")));
                        }
                        profile.setWritten_recipes(recipes);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }finally{
                        if (inner_statement1 != null) {
                            inner_statement1.close();
                        }
                    }

                    inner_sql = "SELECT ID FROM FORUM_TOPICS WHERE AUTHOR_ID=?";
                    try{
                        inner_statement1 = connection.prepareStatement(inner_sql);
                        inner_statement1.setInt(1, profile.getId());
                        ResultSet topicsIds = inner_statement1.executeQuery();
                        List<ForumTopic> topics = new ArrayList<>();
                        while (topicsIds.next()){
                            topics.add(new ForumTopicDao().getEntityById(topicsIds.getInt("ID")));
                        }
                        profile.setStarted_topics(topics);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }finally{
                        if (inner_statement1 != null) {
                            inner_statement1.close();
                        }
                    }

                    inner_sql = "SELECT ID FROM MEAL WHERE USER_ID=?";
                    try{
                        inner_statement1 = connection.prepareStatement(inner_sql);
                        inner_statement1.setInt(1, profile.getId());
                        ResultSet mealsIds = inner_statement1.executeQuery();
                        List<Meal> meals = new ArrayList<>();
                        while (mealsIds.next()){
                            meals.add(new MealDao().getEntityById(mealsIds.getInt("ID")));
                        }
                        profile.setMeals(meals);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }finally{
                        if (inner_statement1 != null) {
                            inner_statement1.close();
                        }
                    }

                    inner_sql = "SELECT ID FROM FORUM_MESSAGE WHERE AUTHOR_ID=?";
                    try{
                        inner_statement1 = connection.prepareStatement(inner_sql);
                        inner_statement1.setInt(1, profile.getId());
                        ResultSet messagesIds = inner_statement1.executeQuery();
                        List<ForumMessage> messages = new ArrayList<>();
                        while (messagesIds.next()){
                            messages.add(new ForumMessageDao().getEntityById(messagesIds.getInt("ID")));
                        }
                        profile.setMessages(messages);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }finally{
                        if (inner_statement1 != null) {
                            inner_statement1.close();
                        }
                    }
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
            PreparedStatement preparedStatement = null, inner_statement1=null;
            String sql = "SELECT * FROM PRODUCT WHERE ID=?";
            Profile profile = new Profile();
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    profile.setId(resultSet.getInt("ID"));
                    profile.setName(resultSet.getString("NAME"));
                    profile.setEmail(resultSet.getString("EMAIL"));
                    profile.setLogin(resultSet.getString("LOGIN"));
                    profile.setPassword(resultSet.getString("PASSWORD"));
                    var blob = resultSet.getBlob("PHOTO");
                    if (blob!=null)
                        profile.setAvatar(blob.getBytes(1l, (int)blob.length()));

                    profile.setAge(resultSet.getInt("AGE"));
                    profile.setHeight(resultSet.getInt("HEIGHT"));
                    profile.setWeight(resultSet.getDouble("WEIGHT"));
                    profile.setActivity(resultSet.getDouble("ACTIVITY_COEF"));

                    String inner_sql = "SELECT ID FROM ARTICLE WHERE AUTHOR_ID=?";
                    try{
                        inner_statement1 = connection.prepareStatement(inner_sql);
                        inner_statement1.setInt(1, profile.getId());
                        ResultSet articlesIds = inner_statement1.executeQuery();
                        List<Article> articles = new ArrayList<>();
                        while (articlesIds.next()){
                            articles.add(new ArticleDao().getEntityById(articlesIds.getInt("ID")));
                        }
                        profile.setWritten_articles(articles);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }finally{
                        if (inner_statement1 != null) {
                            inner_statement1.close();
                        }
                    }

                    inner_sql = "SELECT ID FROM RECIPE WHERE AUTHOR_ID=?";
                    try{
                        inner_statement1 = connection.prepareStatement(inner_sql);
                        inner_statement1.setInt(1, profile.getId());
                        ResultSet recipesIds = inner_statement1.executeQuery();
                        List<Recipe> recipes = new ArrayList<>();
                        while (recipesIds.next()){
                            recipes.add(new RecipeDao().getEntityById(recipesIds.getInt("ID")));
                        }
                        profile.setWritten_recipes(recipes);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }finally{
                        if (inner_statement1 != null) {
                            inner_statement1.close();
                        }
                    }

                    inner_sql = "SELECT ID FROM FORUM_TOPICS WHERE AUTHOR_ID=?";
                    try{
                        inner_statement1 = connection.prepareStatement(inner_sql);
                        inner_statement1.setInt(1, profile.getId());
                        ResultSet topicsIds = inner_statement1.executeQuery();
                        List<ForumTopic> topics = new ArrayList<>();
                        while (topicsIds.next()){
                            topics.add(new ForumTopicDao().getEntityById(topicsIds.getInt("ID")));
                        }
                        profile.setStarted_topics(topics);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }finally{
                        if (inner_statement1 != null) {
                            inner_statement1.close();
                        }
                    }

                    inner_sql = "SELECT ID FROM MEAL WHERE USER_ID=?";
                    try{
                        inner_statement1 = connection.prepareStatement(inner_sql);
                        inner_statement1.setInt(1, profile.getId());
                        ResultSet mealsIds = inner_statement1.executeQuery();
                        List<Meal> meals = new ArrayList<>();
                        while (mealsIds.next()){
                            meals.add(new MealDao().getEntityById(mealsIds.getInt("ID")));
                        }
                        profile.setMeals(meals);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }finally{
                        if (inner_statement1 != null) {
                            inner_statement1.close();
                        }
                    }

                    inner_sql = "SELECT ID FROM FORUM_MESSAGE WHERE AUTHOR_ID=?";
                    try{
                        inner_statement1 = connection.prepareStatement(inner_sql);
                        inner_statement1.setInt(1, profile.getId());
                        ResultSet messagesIds = inner_statement1.executeQuery();
                        List<ForumMessage> messages = new ArrayList<>();
                        while (messagesIds.next()){
                            messages.add(new ForumMessageDao().getEntityById(messagesIds.getInt("ID")));
                        }
                        profile.setMessages(messages);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }finally{
                        if (inner_statement1 != null) {
                            inner_statement1.close();
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
            return profile;
        }

        @Override
        public boolean update(Profile entity) throws SQLException {
            boolean success = true;
            PreparedStatement preparedStatement = null;
            String sql = "UPDATE PRODUCT SET NAME=?, AVATAR=?, WEIGHT=?, ACTIVITY_COEF=? WHERE ID=?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, entity.getName());
                preparedStatement.setBlob(2,
                        entity.getAvatar()!=null?new SerialBlob(entity.getAvatar()):null);
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
            String sql = "DELETE FROM PRODUCT WHERE ID=?";
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
            String sql = "INSERT INTO PRODUCT (ID, NAME,EMAIL, LOGIN, PASSWORD, AGE, HEIGHT, ACTIVITY_COEF, AVATAR, WEIGHT)" +
                    " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            boolean success = true;
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, entity.getId());
                preparedStatement.setString(2, entity.getName());
                preparedStatement.setString(3, entity.getEmail());
                preparedStatement.setString(4, entity.getLogin());
                preparedStatement.setString(5, entity.getPassword());
                preparedStatement.setInt(6, entity.getAge());
                preparedStatement.setInt(7, entity.getHeight());
                preparedStatement.setDouble(8, entity.getActivity());
                preparedStatement.setBlob(9,
                        entity.getAvatar()!=null?new SerialBlob(entity.getAvatar()):null);
                preparedStatement.setDouble(10, entity.getWeight());
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
}
