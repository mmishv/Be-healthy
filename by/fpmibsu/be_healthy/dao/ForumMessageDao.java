package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.pg.JDBCPostgreSQL;
import by.fpmibsu.be_healthy.entity.ForumMessage;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ForumMessageDao extends JDBCPostgreSQL implements Dao<ForumMessage> {
    private Connection connection = getConnection();

    @Override
    public List<ForumMessage> getAll() throws SQLException {
        List<ForumMessage> messages = new ArrayList<>();
        String sql = "SELECT * FROM FORUM_MESSAGE";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ForumMessage message = new ForumMessage();
                message.setId(resultSet.getInt("ID"));
                message.setAuthorId(resultSet.getInt("USER_ID"));
                message.setTopic_id(resultSet.getInt("TOPIC_ID"));
                message.setText(resultSet.getString("CONTENT_TEXT"));
                message.setDateOfPublication(resultSet.getDate("PUBL_DATE"));
                var blob = resultSet.getBlob("ATTACHMENT");
                if (blob != null)
                    message.setAttachment(blob.getBytes(1l, (int) blob.length()));
                messages.add(message);
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
        return messages;
    }

    @Override
    public ForumMessage getEntityById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM FORUM_MESSAGE WHERE ID=?";
        ForumMessage message = new ForumMessage();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (int) id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                message.setId(resultSet.getInt("ID"));
                message.setAuthorId(resultSet.getInt("USER_ID"));
                message.setTopic_id(resultSet.getInt("TOPIC_ID"));
                message.setText(resultSet.getString("CONTENT_TEXT"));
                message.setDateOfPublication(resultSet.getDate("PUBL_DATE"));
                var blob = resultSet.getBlob("ATTACHMENT");
                if (blob != null)
                    message.setAttachment(blob.getBytes(1l, (int) blob.length()));
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
        return message;
    }

    @Override
    public boolean update(ForumMessage entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE FORUM_MESSAGE SET PUBL_DATE=?, CONTENT_TEXT=?, ATTACHMENT=? WHERE ID=?";
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, (Date) entity.getDateOfPublication());
            preparedStatement.setString(2, entity.getText());
            preparedStatement.setBlob(3,
                    entity.getAttachment() != null ? new SerialBlob(entity.getAttachment()) : null);
            preparedStatement.setLong(4, entity.getId());
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
    public boolean delete(ForumMessage entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM FORUM_MESSAGE WHERE ID=?";
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
    public boolean create(ForumMessage entity) throws SQLException {
        boolean success = true;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO FORUM_MESSAGE (ID, USER_ID, TOPIC_ID, PUBL_DATE, CONTENT_TEXT, ATTACHMENT) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setInt(2, entity.getAuthorId());
            preparedStatement.setInt(3, entity.getTopic_id());
            preparedStatement.setDate(4, (Date) entity.getDateOfPublication());
            preparedStatement.setString(5, entity.getText());
            preparedStatement.setBlob(6,
                    entity.getAttachment() != null ? new SerialBlob(entity.getAttachment()) : null);

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

    List<ForumMessage> getMessagesByTopicId(int id) throws SQLException {
        PreparedStatement statement = null;
        List<ForumMessage> messages = new ArrayList<>();
        String inner_sql = "SELECT ID FROM FORUM_MESSAGE WHERE TOPIC_ID=?";
        try {
            statement = connection.prepareStatement(inner_sql);
            statement.setInt(1, id);
            ResultSet messagesIds = statement.executeQuery();
            while (messagesIds.next()) {
                messages.add(new ForumMessageDao().getEntityById(messagesIds.getInt("ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return messages;
    }
    List<ForumMessage> getMessagesByUserId(int id) throws SQLException {
        PreparedStatement statement = null;
        String sql = "SELECT ID FROM FORUM_MESSAGE WHERE AUTHOR_ID=?";
        List<ForumMessage> messages = new ArrayList<>();
        try{
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet messagesIds = statement.executeQuery();

            while (messagesIds.next()){
                messages.add(new ForumMessageDao().getEntityById(messagesIds.getInt("ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if (statement != null) {
                statement.close();
            }
        }
        return messages;
    }
}
