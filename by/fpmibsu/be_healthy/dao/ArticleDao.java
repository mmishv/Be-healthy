package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.Article;
import by.fpmibsu.be_healthy.entity.Recipe;
import by.fpmibsu.be_healthy.pg.JDBCPostgreSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

            initRecipes(articles, resultSet);
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
            initRecipes(recipes, resultSet);
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
            initRecipes(recipes, resultSet);
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
    private void initRecipes(List<Article> recipes, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Article article = new Article();
            setRecipe(resultSet, article);
            recipes.add(article);
        }
    }

    private void setRecipe(ResultSet resultSet, Article article) throws SQLException {
        article.setId(resultSet.getInt("ID"));
        article.setAuthorId(resultSet.getInt("AUTHOR_ID"));
        article.setTitle(resultSet.getString("TITLE"));
        article.setFulltext(resultSet.getString("FULL_TEXT"));
        article.setDateOfPublication(resultSet.getDate("PUBL_DATE"));
        article.setCategories(new ArticleCategoryDao().getArticleCategoriesByArticleId(article.getAuthorId()));
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
                setRecipe(resultSet, article);
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
