package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.pg.JDBCPostgreSQL;
import by.fpmibsu.be_healthy.entity.ForumTopic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ForumTopicDao extends JDBCPostgreSQL implements Dao<ForumTopic> {
    private Connection connection = getConnection();

    @Override
    public List<ForumTopic> getAll() throws SQLException {
        List<ForumTopic> topics = new ArrayList<>();
        String sql = "SELECT * FROM FORUM_TOPIC";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ForumTopic topic = new ForumTopic();
                topic.setId(resultSet.getInt("ID"));
                topic.setAuthorId(resultSet.getInt("AUTHOR_ID"));
                topic.setTitle(resultSet.getString("TITLE"));
                topic.setCreated_on(resultSet.getDate("CREATED_ON"));
                topic.setMessages(new ForumMessageDao().getMessagesByTopicId(topic.getId()));
                topics.add(topic);
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
        return topics;
    }

    @Override
    public ForumTopic getEntityById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM FORUM_TOPIC WHERE ID=?";
        ForumTopic topic = new ForumTopic();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                topic.setId(resultSet.getInt("ID"));
                topic.setAuthorId(resultSet.getInt("AUTHOR_ID"));
                topic.setTitle(resultSet.getString("TITLE"));
                topic.setCreated_on(resultSet.getDate("CREATED_ON"));
                topic.setMessages(new ForumMessageDao().getMessagesByTopicId(topic.getId()));
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
        return topic;
    }

    @Override
    public boolean update(ForumTopic entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE FORUM_TOPIC SET TITLE=?, CREATED_ON=?, AUTHOR_ID=? WHERE ID=?";
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setDate(2, entity.getCreated_on());
            preparedStatement.setInt(3, entity.getAuthorId());
            preparedStatement.setInt(4, entity.getId());
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
    public boolean delete(ForumTopic entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM FORUM_TOPIC WHERE ID=?";
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
    public boolean create(ForumTopic entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO FORUM_TOPIC (ID, TITLE, CREATED_ON, AUTHOR_ID) VALUES(?, ?, ?, ?)";
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(2, entity.getTitle());
            preparedStatement.setDate(3, entity.getCreated_on());
            preparedStatement.setInt(4, entity.getAuthorId());
            preparedStatement.setInt(1, entity.getId());
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
    List<ForumTopic> getTopicsByAuthorId(int id) throws SQLException {
        PreparedStatement statement = null;
        List<ForumTopic> topics = new ArrayList<>();
        String sql = "SELECT ID FROM FORUM_TOPICS WHERE AUTHOR_ID=?";
        try{
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet topicsIds = statement.executeQuery();
            while (topicsIds.next()){
                topics.add(new ForumTopicDao().getEntityById(topicsIds.getInt("ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if (statement != null) {
                statement.close();
            }
        }
        return topics;
    }
}
