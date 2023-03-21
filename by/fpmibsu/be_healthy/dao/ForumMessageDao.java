package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.bl.JDBCPostgreSQL;
import by.fpmibsu.be_healthy.entity.ForumMessage;
import by.fpmibsu.be_healthy.entity.Meal;

import java.io.File;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ForumMessageDao extends JDBCPostgreSQL implements Dao<ForumMessage>  {
    private Connection connection = getConnection();
    @Override
    public List<ForumMessage> getAll() throws SQLException {
        List<ForumMessage> projectList = new ArrayList<>();
        String sql = "SELECT * FROM FORUM_MESSAGE";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                ForumMessage message = new ForumMessage();
                message.setId(resultSet.getInt("ID"));
                message.setAuthorId(resultSet.getInt("AUTHOR_ID"));
                message.setText(resultSet.getAsciiStream("TEXT").toString());
                message.setDateOfPublication(resultSet.getDate("DATE_OF_PUBLICATION"));
                message.setAttachment((File) resultSet.getBlob("ATTACHMENT"));
                projectList.add(message);
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
    public ForumMessage getEntityById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM FORUM_MESSAGE WHERE ID=?";
        ForumMessage message = new ForumMessage();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            message.setId(resultSet.getInt("ID"));
            message.setAuthorId(resultSet.getInt("AUTHOR_ID"));
            message.setText(resultSet.getString("TEXT"));
            message.setDateOfPublication(resultSet.getDate("DATE_OF_PUBLICATION"));
            message.setAttachment((File) resultSet.getBlob("ATTACHMENT"));
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
        return message;
    }

    @Override
    public ForumMessage update(ForumMessage entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE MEAL SET TEXT=?, PUBLICATION_DATE=?, ATTACHMENT=? WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getText());
            preparedStatement.setDate(2, (Date) entity.getDateOfPublication());
            preparedStatement.setBlob(3, (Blob)entity.getAttachment());
            preparedStatement.setLong(4, entity.getId());
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
    public void delete(ForumMessage entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM FORUM_MESSAGE WHERE ID=?";
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
    public void create(ForumMessage entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO FORUM_MESSAGE (ID, AUTHOR_ID, TEXT, DATE_OF_PUBLICATION, ATTACHMENT) VALUES(?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setInt(2, entity.getAuthorId());
            preparedStatement.setString(3, entity.getText());
            preparedStatement.setDate(4, (Date) entity.getDateOfPublication());
            preparedStatement.setBlob(5, (Blob) entity.getAttachment());
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
