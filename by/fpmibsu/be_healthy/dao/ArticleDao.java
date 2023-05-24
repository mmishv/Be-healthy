package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.Article;
import by.fpmibsu.be_healthy.postgres.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao implements Dao<Article> {
    private static final Logger logger = LogManager.getLogger(ArticleDao.class);

    @Override
    public List<Article> getAll() {
        List<Article> articles = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             Statement statement =  connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM ARTICLE ORDER BY PUBL_DATE DESC");
            initArticles(articles, resultSet);
        } catch (SQLException e) {
            logger.error("Error getting all articles");
            logger.trace(e);
            e.printStackTrace();
        }
        return articles;
    }

    public List<Article> getPage(int page, int per_page, boolean moderated) {
        List<Article> recipes = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM ARTICLE WHERE moderated = ?  ORDER BY PUBL_DATE DESC LIMIT ? OFFSET ?")) {
            statement.setBoolean(1, moderated);
            statement.setInt(2, per_page);
            statement.setInt(3, per_page * (page - 1));
            ResultSet resultSet = statement.executeQuery();
            initArticles(recipes, resultSet);
        } catch (SQLException e) {
            logger.error("Error getting page of articles");
            logger.trace(e);
            e.printStackTrace();
        }
        return recipes;
    }

    public List<Article> getAuthorPage(int page, int per_page, int id) {
        List<Article> recipes = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM ARTICLE WHERE AUTHOR_ID = ? ORDER BY PUBL_DATE DESC LIMIT ? OFFSET ?")){
            statement.setInt(1, id);
            statement.setInt(2, per_page);
            statement.setInt(3, per_page * (page - 1));
            ResultSet resultSet = statement.executeQuery();
            initArticles(recipes, resultSet);
        } catch (SQLException e) {
            logger.error("Error getting user's articles");
            logger.trace(e);
            e.printStackTrace();
        }
        return recipes;
    }

    private void initArticles(List<Article> articles, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Article article = new Article();
            setArticle(resultSet, article);
            articles.add(article);
        }
    }

    private void setArticle(ResultSet resultSet, Article article) throws SQLException {
        article.setId(resultSet.getInt("ID"));
        article.setAuthorId(resultSet.getInt("AUTHOR_ID"));
        article.setTitle(resultSet.getString("TITLE"));
        article.setFulltext(resultSet.getString("FULL_TEXT"));
        article.setDateOfPublication(resultSet.getDate("PUBL_DATE"));
        article.setModerated(resultSet.getBoolean("MODERATED"));
        article.setCategories(new ArticleCategoryDao().getArticleCategoriesByArticleId(article.getId()));
    }

    @Override
    public Article getEntityById(long id) {
        Article article = null;
        try (Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM ARTICLE WHERE ID=?")){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                article = new Article();
                setArticle(resultSet, article);
            }
        } catch (SQLException e) {
            logger.error("Error getting article by id");
            logger.trace(e);
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public boolean update(Article entity) {
        if (entity == null)
            return false;
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE ARTICLE SET TITLE=?, FULL_TEXT=? WHERE ID=?")){
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getFulltext());
            preparedStatement.setInt(3, entity.getId());
            if (preparedStatement.executeUpdate()==0)
                return false;
            deleteFromMM(entity.getId());
            setCategories(entity, entity.getId());
        } catch (SQLException e) {
            logger.error("Error updating article");
            logger.trace(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateModerationStatus(int id, boolean moderated){
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE ARTICLE SET MODERATED=? WHERE ID=?")) {
            preparedStatement.setBoolean(1, moderated);
            preparedStatement.setInt(2, id);
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error updating article moderation status");
            logger.trace(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void deleteFromMM(int article_id){
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM MM_CATEGORY_ARTICLE WHERE ARTICLE_ID=?")){
            preparedStatement.setInt(1, article_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting article categories");
            logger.trace(e);
            throw new RuntimeException(e);
        }
    }
    private void setCategories(Article entity, int article_id) throws SQLException {
        for (var i : entity.getCategories()) {
            try (Connection connection = DataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            "INSERT INTO MM_CATEGORY_ARTICLE (ARTICLE_ID, CATEGORY_ID) VALUES(?, ?)")) {
                preparedStatement.setInt(1, article_id);
                preparedStatement.setInt(2, i.getId());
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                logger.error("Error adding article categories");
                logger.trace(e);
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM ARTICLE WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error deleting article");
            logger.trace(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getMaxId() {
        try (Connection connection = DataSource.getConnection();
                Statement statement =  connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT MAX(id) AS id FROM ARTICLE");
            if (resultSet.next()) {
                return resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            logger.error("Error getting max article id");
            logger.trace(e);
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean create(Article entity) {
        if (entity == null)
            return false;
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement =  connection.prepareStatement(
                        "INSERT INTO ARTICLE (TITLE, PUBL_DATE, FULL_TEXT, AUTHOR_ID) VALUES(?, ?, ?, ?)")) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setDate(2, (Date) (entity.getDateOfPublication()));
            preparedStatement.setString(3, entity.getFulltext());
            preparedStatement.setInt(4, entity.getAuthorId());
            if (preparedStatement.executeUpdate()==0)
                return false;
            setCategories(entity, getMaxId());
        } catch (SQLException e) {
            logger.error("Error creating article");
            logger.trace(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getNumberOfArticles(boolean moderated) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT COUNT(*) RES FROM ARTICLE WHERE MODERATED=?")) {
            statement.setBoolean(1, moderated);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) return resultSet.getInt("RES");
        } catch (SQLException e) {
            logger.error("Error getting number of articles");
            logger.trace(e);
            e.printStackTrace();
        }
        return -1;
    }

    public int getNumberOfArticlesWrittenBy(int id) {
        try (PreparedStatement statement =  DataSource.getConnection().prepareStatement(
                "SELECT COUNT(*) AS ARTICLE_NUM FROM ARTICLE WHERE AUTHOR_ID = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("ARTICLE_NUM");
            }
        } catch (SQLException e) {
            logger.error("Error getting number of user's articles");
            logger.trace(e);
            e.printStackTrace();
        }
        return -1;
    }
}
