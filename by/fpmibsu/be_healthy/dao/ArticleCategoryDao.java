package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.ArticleCategory;
import by.fpmibsu.be_healthy.postgres.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleCategoryDao implements Dao<ArticleCategory> {
    private static final Logger logger = LogManager.getLogger(ArticleCategoryDao.class);
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
            logger.error("Error getting all article categories");
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public ArticleCategory getEntityById(long id) {
        ArticleCategory category = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement =connection.prepareStatement("SELECT ID, NAME FROM ARTICLE_CATEGORY WHERE ID=?")){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category = new ArticleCategory();
                category.setId(resultSet.getInt("ID"));
                category.setName(resultSet.getString("NAME"));
            }
        } catch (SQLException e) {
            logger.error("Error getting article category by id");
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public boolean update(ArticleCategory entity) {
        if (entity == null)
            return false;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement =connection.prepareStatement("UPDATE ARTICLE_CATEGORY SET NAME=? WHERE ID=?")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setLong(2, entity.getId());
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error updating article category");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ARTICLE_CATEGORY WHERE ID=?");){
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error deleting article category");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean create(ArticleCategory entity) {
        if (entity == null)
            return false;
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement =connection.prepareStatement("INSERT INTO ARTICLE_CATEGORY (NAME) VALUES(?)")){
            preparedStatement.setString(1, entity.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error creating article category");
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
            logger.error("Error getting article categories by article id");
            e.printStackTrace();
        }
        return categories;
    }
}