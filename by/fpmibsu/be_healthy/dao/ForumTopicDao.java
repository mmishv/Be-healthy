package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.bl.JDBCPostgreSQL;
import by.fpmibsu.be_healthy.entity.ForumMessage;
import by.fpmibsu.be_healthy.entity.ForumTopic;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ForumTopicDao extends JDBCPostgreSQL implements Dao<ForumTopic>  {
    private Connection connection = getConnection();
    @Override
    public List<ForumTopic> getAll() throws SQLException {
        List<ForumTopic> projectList = new ArrayList<>();
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
                topic.setPreview(resultSet.getString("PREVIEW"));
                topic.setMessages((List<ForumMessage>) resultSet.getArray("MESSAGES"));
                projectList.add(topic);
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
        return projectList;
    }

    @Override
    public ForumTopic getEntityById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM FORUM_TOPIC WHERE ID=?";
        ForumTopic topic= new ForumTopic();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            topic.setId(resultSet.getInt("ID"));
            topic.setAuthorId(resultSet.getInt("AUTHOR_ID"));
            topic.setTitle(resultSet.getString("TITLE"));
            topic.setPreview(resultSet.getString("PREVIEW"));
            topic.setMessages((List<ForumMessage>) resultSet.getArray("MESSAGES"));
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
        return topic;
    }

    @Override
    public ForumTopic update(ForumTopic entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE FORUM_TOPIC SET TITLE=?, PREVIEW=?, MESSAGES=? WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getPreview());
            preparedStatement.setArray(3, (Array) entity.getMessages());
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
        return entity;
    }

    @Override
    public void delete(ForumTopic entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM FORUM_TOPIC WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, entity.getId());
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
    }

    @Override
    public void create(ForumTopic entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO FORUM_TOPIC (ID, AUTHOR_ID, TITLE, PREVIEW, MESSAGES) VALUES(?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setInt(2, entity.getAuthorId());
            preparedStatement.setString(3, entity.getTitle());
            preparedStatement.setString(4, entity.getPreview());
            preparedStatement.setArray(5, (Array) entity.getMessages());
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
    }
}
