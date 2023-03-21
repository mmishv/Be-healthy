package by.fpmibsu.be_healthy.dao;
import by.fpmibsu.be_healthy.bl.JDBCPostgreSQL;
import by.fpmibsu.be_healthy.entity.ArticleCategory;

import java.sql.*;
import java.util.*;

public class ArticleCategoryDao extends JDBCPostgreSQL implements Dao<ArticleCategory> {
    private Connection connection = getConnection();
    @Override
    public List<ArticleCategory> getAll() throws SQLException {
        List<ArticleCategory> projectList = new ArrayList<>();
        String sql = "SELECT ID, NAME FROM ARTICLE_CATEGORY";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                ArticleCategory category = new ArticleCategory();
                category.setId(resultSet.getInt("ID"));
                category.setName(resultSet.getString("NAME"));
                projectList.add(category);
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
    public ArticleCategory getEntityById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT ID, NAME FROM ARTICLE_CATEGORY WHERE ID=?";
        ArticleCategory category = new ArticleCategory();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            category.setId(resultSet.getInt("ID"));
            category.setName(resultSet.getString("NAME"));
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
        return category;
    }

    @Override
    public ArticleCategory update(ArticleCategory entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE ARTICLE_CATEGORY SET NAME=? WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setLong(2, entity.getId());
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
    public void delete(ArticleCategory entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM ARTICLE_CATEGORY WHERE ID=?";
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
    public void create(ArticleCategory entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO ARTICLE_CATEGORY (ID, NAME) VALUES(?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
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
