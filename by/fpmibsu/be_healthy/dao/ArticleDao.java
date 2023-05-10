package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.Article;
import by.fpmibsu.be_healthy.postgres.JDBCPostgreSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.fpmibsu.be_healthy.dao.MealDao.getId;

public class ArticleDao extends JDBCPostgreSQL implements Dao<Article> {
    private Connection connection = getConnection();

    @Override
    public List<Article> getAll() throws SQLException {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM ARTICLE ORDER BY PUBL_DATE DESC";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            initArticle(articles, resultSet);
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
        return articles;
    }
    public List<Article> getPage(int page, int per_page) throws SQLException {
        List<Article> recipes = new ArrayList<>();
        String sql = "SELECT * FROM ARTICLE ORDER BY PUBL_DATE DESC LIMIT ? OFFSET ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, per_page);
            statement.setInt(2, per_page * (page-1));
            ResultSet resultSet = statement.executeQuery();
            initArticle(recipes, resultSet);
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
        return recipes;
    }
    public List<Article> getAuthorPage(int page, int per_page, int id) throws SQLException {
        List<Article> recipes = new ArrayList<>();
        String sql = "SELECT * FROM ARTICLE WHERE AUTHOR_ID = ? ORDER BY PUBL_DATE DESC LIMIT ? OFFSET ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, per_page);
            statement.setInt(3, per_page * (page-1));
            ResultSet resultSet = statement.executeQuery();
            initArticle(recipes, resultSet);
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
        return recipes;
    }
    private void initArticle(List<Article> articles, ResultSet resultSet) throws SQLException {
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
        article.setCategories(new ArticleCategoryDao().getArticleCategoriesByArticleId(article.getId()));
    }

    @Override
    public Article getEntityById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM ARTICLE WHERE ID=?";
        Article article = new Article();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                setArticle(resultSet, article);
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
        return article;
    }

    @Override
    public boolean update(Article entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE ARTICLE SET TITLE=?, FULL_TEXT=? WHERE ID=?";
        PreparedStatement inner_statement1 = null;
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getFulltext());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.executeUpdate();

            String inner_sql = "DELETE FROM MM_CATEGORY_ARTICLE WHERE ARTICLE_ID=?";
            inner_statement1 = connection.prepareStatement(inner_sql);
            inner_statement1.setInt(1, entity.getId());
            inner_statement1.executeUpdate();
            setCategories(entity, entity.getId());
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

    private void setCategories(Article entity, int article_id) throws SQLException {
        PreparedStatement inner_statement = null;
        for (var i : entity.getCategories()) {
            String inner_sql = "INSERT INTO MM_CATEGORY_ARTICLE (ARTICLE_ID, CATEGORY_ID) VALUES(?, ?)";
            inner_statement = connection.prepareStatement(inner_sql);
            inner_statement.setInt(1, article_id);
            inner_statement.setInt(2, i.getId());
            inner_statement.executeUpdate();
        }
    }

    @Override
    public boolean delete(Article entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM ARTICLE WHERE ID=?";
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

    public int getMaxId() throws SQLException {
        PreparedStatement preparedStatement = null, inner_statement = null;
        String sql = "SELECT max(id) as id FROM ARTICLE";
        Statement statement = null;
        return getId(sql, connection);
    }
    @Override
    public boolean create(Article entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO ARTICLE (TITLE, PUBL_DATE, FULL_TEXT, AUTHOR_ID) VALUES(?, ?, ?, ?)";
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setDate(2, (Date) (entity.getDateOfPublication()));
            preparedStatement.setString(3, entity.getFulltext());
            preparedStatement.setInt(4, entity.getAuthorId());
            preparedStatement.executeUpdate();
            setCategories(entity, getMaxId());
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

    public List<Article> getWrittenArticlesByAuthorId(int id) throws SQLException {
        PreparedStatement statement = null;
        String sql = "SELECT ID FROM ARTICLE WHERE AUTHOR_ID=?";
        List<Article> articles = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet articlesIds = statement.executeQuery();

            while (articlesIds.next()) {
                articles.add(new ArticleDao().getEntityById(articlesIds.getInt("ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return articles;
    }

    public int getNumberOfArticles() throws SQLException {
        String sql = "SELECT COUNT(*) RES FROM ARTICLE";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                return resultSet.getInt("RES");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return -1;
    }

    public int getNumberOfArticlesWrittenBy(int id) throws SQLException {
        String sql = "SELECT COUNT(*) RES FROM ARTICLE WHERE AUTHOR_ID = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("RES");
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
        return -1;
    }
}
