package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.Article;
import by.fpmibsu.be_healthy.postgres.JDBCPostgreSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao extends JDBCPostgreSQL implements Dao<Article> {
    private final Connection connection = getConnection();

    @Override
    public List<Article> getAll() {
        List<Article> articles = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ARTICLE ORDER BY PUBL_DATE DESC");
            initArticles(articles, resultSet);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    public List<Article> getPage(int page, int per_page) {
        List<Article> recipes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM ARTICLE ORDER BY PUBL_DATE DESC LIMIT ? OFFSET ?")) {
            statement.setInt(1, per_page);
            statement.setInt(2, per_page * (page - 1));
            ResultSet resultSet = statement.executeQuery();
            initArticles(recipes, resultSet);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    public List<Article> getAuthorPage(int page, int per_page, int id) {
        List<Article> recipes = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ARTICLE WHERE AUTHOR_ID = ? ORDER BY PUBL_DATE DESC LIMIT ? OFFSET ?");
            statement.setInt(1, id);
            statement.setInt(2, per_page);
            statement.setInt(3, per_page * (page - 1));
            ResultSet resultSet = statement.executeQuery();
            initArticles(recipes, resultSet);
            statement.close();
            connection.close();
        } catch (SQLException e) {
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
        article.setCategories(new ArticleCategoryDao().getArticleCategoriesByArticleId(article.getId()));
    }

    @Override
    public Article getEntityById(long id) {
        Article article = new Article();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ARTICLE WHERE ID=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) setArticle(resultSet, article);
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public boolean update(Article entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ARTICLE SET TITLE=?, FULL_TEXT=? WHERE ID=?");
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getFulltext());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("DELETE FROM MM_CATEGORY_ARTICLE WHERE ARTICLE_ID=?");
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.executeUpdate();
            setCategories(entity, entity.getId());
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void setCategories(Article entity, int article_id) throws SQLException {
        for (var i : entity.getCategories()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO MM_CATEGORY_ARTICLE (ARTICLE_ID, CATEGORY_ID) VALUES(?, ?)");
            preparedStatement.setInt(1, article_id);
            preparedStatement.setInt(2, i.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public boolean delete(Article entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ARTICLE WHERE ID=?")) {
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getMaxId() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) AS id FROM ARTICLE");
            if (resultSet.next()) {
                return resultSet.getInt("ID");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean create(Article entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ARTICLE (TITLE, PUBL_DATE, FULL_TEXT, AUTHOR_ID) VALUES(?, ?, ?, ?)")) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setDate(2, (Date) (entity.getDateOfPublication()));
            preparedStatement.setString(3, entity.getFulltext());
            preparedStatement.setInt(4, entity.getAuthorId());
            preparedStatement.executeUpdate();
            setCategories(entity, getMaxId());
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getNumberOfArticles() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS ARTICLE_NUM FROM ARTICLE");
            if (resultSet.next()) {
                return resultSet.getInt("ARTICLE_NUM");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getNumberOfArticlesWrittenBy(int id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) AS ARTICLE_NUM FROM ARTICLE WHERE AUTHOR_ID = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("ARTICLE_NUM");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
