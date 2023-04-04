package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.Article;
import by.fpmibsu.be_healthy.pg.JDBCPostgreSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao extends JDBCPostgreSQL implements Dao<Article> {
    private Connection connection = getConnection();

    @Override
    public List<Article> getAll() throws SQLException {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM ARTICLE";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Article article = new Article();
                article.setId(resultSet.getInt("ID"));
                article.setAuthorId(resultSet.getInt("AUTHOR_ID"));
                article.setTitle(resultSet.getString("TITLE"));
                article.setFulltext(resultSet.getString("FULL_TEXT"));
                article.setDateOfPublication(resultSet.getDate("PUBL_DATE"));
                article.setCategories(new ArticleCategoryDao().getArticleCategoriesByArticleId(article.getAuthorId()));
                articles.add(article);
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
        return articles;
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
                article.setId(resultSet.getInt("ID"));
                article.setAuthorId(resultSet.getInt("AUTHOR_ID"));
                article.setTitle(resultSet.getString("TITLE"));
                article.setFulltext(resultSet.getString("FULL_TEXT"));
                article.setDateOfPublication(resultSet.getDate("PUBL_DATE"));
                article.setCategories(new ArticleCategoryDao().getArticleCategoriesByArticleId(article.getAuthorId()));
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
        String sql = "UPDATE ARTICLE SET TITLE=?, PUBL_DATE=?,FULL_TEXT=?, AUTHOR_ID=? WHERE ID=?";
        boolean success = true;
        try {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setDate(2, (Date) (entity.getDateOfPublication()));
            preparedStatement.setString(3, entity.getFulltext());
            preparedStatement.setInt(4, entity.getAuthorId());
            preparedStatement.setInt(5, entity.getId());
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

    @Override
    public boolean create(Article entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO ARTICLE (ID, TITLE, PUBL_DATE, FULL_TEXT, AUTHOR_ID) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement inner_statement = null;
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getTitle());
            preparedStatement.setDate(3, (Date) (entity.getDateOfPublication()));
            preparedStatement.setString(4, entity.getFulltext());
            preparedStatement.setInt(5, entity.getAuthorId());
            preparedStatement.executeUpdate();
            for (var i : entity.getCategories()) {
                String inner_sql = "INSERT INTO MM_CATEGORY_ARTICLE (ARTICLE_ID, CATEGORY_ID) VALUES(?, ?)";
                try {
                    inner_statement = connection.prepareStatement(inner_sql);
                    inner_statement.setInt(1, entity.getId());
                    inner_statement.setInt(2, i.getId());
                    inner_statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (inner_statement != null) {
                        inner_statement.close();
                    }
                }
            }
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

    List<Article> getWrittenArticlesByAuthorId(int id) throws SQLException {
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
}
