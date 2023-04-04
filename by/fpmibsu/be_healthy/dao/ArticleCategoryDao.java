package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.pg.JDBCPostgreSQL;
import by.fpmibsu.be_healthy.entity.ArticleCategory;

import java.sql.*;
import java.util.*;

public class ArticleCategoryDao extends JDBCPostgreSQL implements Dao<ArticleCategory> {
    private Connection connection = getConnection();

    @Override
    public List<ArticleCategory> getAll() throws SQLException {
        List<ArticleCategory> categories = new ArrayList<>();
        String sql = "SELECT ID, NAME FROM ARTICLE_CATEGORY";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                ArticleCategory category = new ArticleCategory();
                category.setId(resultSet.getInt("ID"));
                category.setName(resultSet.getString("NAME"));
                categories.add(category);
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
        return categories;
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
            if (resultSet.next()) {
                category.setId(resultSet.getInt("ID"));
                category.setName(resultSet.getString("NAME"));
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
        return category;
    }

    @Override
    public boolean update(ArticleCategory entity) throws SQLException {
        boolean success = true;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE ARTICLE_CATEGORY SET NAME=? WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setLong(2, entity.getId());
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
    public boolean delete(ArticleCategory entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM ARTICLE_CATEGORY WHERE ID=?";
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
    public boolean create(ArticleCategory entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO ARTICLE_CATEGORY (ID, NAME) VALUES(?, ?)";
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
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

    List<ArticleCategory> getArticleCategoriesByArticleId(int id) throws SQLException {
        PreparedStatement statement = null;
        String inner_sql = "SELECT CATEGORY_ID AS ID FROM MM_CATEGORY_ARTICLE WHERE ARTICLE_ID=?";
        List<ArticleCategory> categories = new ArrayList<>();
        try {
            statement = connection.prepareStatement(inner_sql);
            statement.setInt(1, id);
            ResultSet categoriesIds = statement.executeQuery();
            while (categoriesIds.next()) {
                categories.add(new ArticleCategoryDao().getEntityById(categoriesIds.getInt("ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return categories;
    }
}
