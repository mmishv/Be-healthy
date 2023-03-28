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
        List<ForumTopic> topics = new ArrayList<>();
        String sql = "SELECT * FROM FORUM_TOPIC";
        Statement statement = null;
        PreparedStatement inner_statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                ForumTopic topic = new ForumTopic();
                topic.setId(resultSet.getInt("ID"));
                topic.setAuthorId(resultSet.getInt("AUTHOR_ID"));
                topic.setTitle(resultSet.getString("TITLE"));
                topic.setCreated_on(resultSet.getDate("CREATED_ON"));

                String inner_sql = "SELECT ID FROM FORUM_MESSAGE WHERE TOPIC_ID=?";
                try {
                inner_statement = connection.prepareStatement(inner_sql);
                inner_statement.setInt(1, topic.getId());
                ResultSet messagesIds = inner_statement.executeQuery();
                List<ForumMessage> messages = new ArrayList<>();
                while (messagesIds.next()){
                    //messages.add(new ForumMessageDao().getEntityById(messagesIds.getInt("ID")));
                }
                topic.setMessages(messages);
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally{
                    if (inner_statement != null) {
                        inner_statement.close();
                    }
                }
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
        PreparedStatement preparedStatement = null, inner_statement = null;
        String sql = "SELECT * FROM FORUM_TOPIC WHERE ID=?";
        ForumTopic topic= new ForumTopic();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            topic.setId(resultSet.getInt("ID"));
            topic.setAuthorId(resultSet.getInt("AUTHOR_ID"));
            topic.setTitle(resultSet.getString("TITLE"));
            topic.setCreated_on(resultSet.getDate("CREATED_ON"));

            String inner_sql = "SELECT ID FROM FORUM_MESSAGE WHERE TOPIC_ID=?";
            try{
            inner_statement = connection.prepareStatement(inner_sql);
            inner_statement.setInt(1, topic.getId());
            ResultSet messagesIds = inner_statement.executeQuery();
            List<ForumMessage> messages = new ArrayList<>();
            while (messagesIds.next()){
                messages.add(new ForumMessageDao().getEntityById(messagesIds.getInt("ID")));
            }
            topic.setMessages(messages);
            } catch (SQLException e) {
            e.printStackTrace();
            }finally{
                if (inner_statement != null) {
                    inner_statement.close();
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
        return topic;
    }

    @Override
    public ForumTopic update(ForumTopic entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE FORUM_TOPIC SET TITLE=?, CREATED_ON=?, AUTHOR_ID=? WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setDate(2, entity.getCreated_on());
            preparedStatement.setInt(3,  entity.getAuthorId());
            preparedStatement.setInt(4,  entity.getId());
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
        String sql = "INSERT INTO FORUM_TOPIC (ID, TITLE, CREATED_ON, AUTHOR_ID) VALUES(?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(2, entity.getTitle());
            preparedStatement.setDate(3, entity.getCreated_on());
            preparedStatement.setInt(4,  entity.getAuthorId());
            preparedStatement.setInt(1,  entity.getId());
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
