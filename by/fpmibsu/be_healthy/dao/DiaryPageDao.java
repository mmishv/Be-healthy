package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.bl.JDBCPostgreSQL;
import by.fpmibsu.be_healthy.entity.DiaryPage;

import java.io.File;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DiaryPageDao extends JDBCPostgreSQL implements Dao<DiaryPage>  {
    private Connection connection = getConnection();
    @Override
    public List<DiaryPage> getAll() throws SQLException {
        List<DiaryPage> projectList = new ArrayList<>();
        String sql = "SELECT * FROM DIARY_PAGE";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                DiaryPage page = new DiaryPage();
                page.setId(resultSet.getInt("ID"));
                page.setDateOfDiaryPage(resultSet.getDate("DATE"));
                page.setUserId(resultSet.getInt("USER_ID"));
                projectList.add(page);
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
    public DiaryPage getEntityById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM DIARY_PAGE WHERE ID=?";
        DiaryPage page = new DiaryPage();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            page.setId(resultSet.getInt("ID"));
            page.setDateOfDiaryPage(resultSet.getDate("DATE"));
            page.setUserId(resultSet.getInt("USER_ID"));
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
        return page;
    }

    @Override
    public DiaryPage update(DiaryPage entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE DIARY_PAGE SET DATE=? USER_ID=? WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, (Date) entity.getDateOfDiaryPage());
            preparedStatement.setInt(2, entity.getUserId());
            preparedStatement.setInt(3, entity.getId());
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
    public void delete(DiaryPage entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM DIARY_PAGE WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
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
    public void create(DiaryPage entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO DIARY_PAGE (ID, DATE, USER_ID) VALUES(?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setDate(2, entity.getDateOfDiaryPage());
            preparedStatement.setInt(3, entity.getUserId());
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
