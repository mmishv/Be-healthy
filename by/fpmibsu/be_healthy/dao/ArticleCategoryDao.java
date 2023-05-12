package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.ArticleCategory;
import by.fpmibsu.be_healthy.postgres.DataSource;
import by.fpmibsu.be_healthy.postgres.JDBCPostgreSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleCategoryDao extends JDBCPostgreSQL implements Dao<ArticleCategory> {
    @Override
    public List<ArticleCategory> getAll() {
        List<ArticleCategory> categories = new ArrayList<>();
        try (Connection connection = DataSource.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT ID, NAME FROM ARTICLE_CATEGORY ORDER BY NAME");
            while (resultSet.next()) {
                ArticleCategory category = new ArticleCategory();
                category.setId(resultSet.getInt("ID"));
                category.setName(resultSet.getString("NAME"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public ArticleCategory getEntityById(long id) {
        ArticleCategory category = new ArticleCategory();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement =connection.prepareStatement("SELECT ID, NAME FROM ARTICLE_CATEGORY WHERE ID=?")){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category.setId(resultSet.getInt("ID"));
                category.setName(resultSet.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public boolean update(ArticleCategory entity) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement =connection.prepareStatement("UPDATE ARTICLE_CATEGORY SET NAME=? WHERE ID=?")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setLong(2, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(ArticleCategory entity) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ARTICLE_CATEGORY WHERE ID=?");){
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean create(ArticleCategory entity) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement =connection.prepareStatement("INSERT INTO ARTICLE_CATEGORY (ID, NAME) VALUES(?, ?)")){
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<ArticleCategory> getArticleCategoriesByArticleId(int id) {
        List<ArticleCategory> categories = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT CATEGORY_ID AS ID FROM MM_CATEGORY_ARTICLE WHERE ARTICLE_ID=?")){
            statement.setInt(1, id);
            ResultSet categoriesIds = statement.executeQuery();
            while (categoriesIds.next()) {
                categories.add(new ArticleCategoryDao().getEntityById(categoriesIds.getInt("ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}