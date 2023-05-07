package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.dao.RecipeDao;
import by.fpmibsu.be_healthy.entity.Article;
import by.fpmibsu.be_healthy.dao.ArticleDao;
import by.fpmibsu.be_healthy.entity.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.List;

public class ArticleService {
    public List<Article> getAll() throws SQLException {
        return new ArticleDao().getAll();
    }


    public Article getEntityById(long id) throws SQLException {
        return new ArticleDao().getEntityById(id);
    }


    public boolean update(Article entity) throws SQLException {
        return new ArticleDao().update(entity);
    }

    public boolean delete(Article entity) throws SQLException {
        return new ArticleDao().delete(entity);
    }

    public boolean create(Article entity) throws SQLException {
        return new ArticleDao().create(entity);
    }

    public List<Article> getWrittenArticlesByAuthorId(int id) throws SQLException {
        return new ArticleDao().getWrittenArticlesByAuthorId(id);
    }

    public String getAllJSON() throws JsonProcessingException, SQLException {
        return new ObjectMapper().writeValueAsString(new ArticleService().getAll());
    }

    public List<Article> getPage(int page, int per_page) throws SQLException {
        return new ArticleDao().getPage(page, per_page);
    }
    public String getPageJSON(int page, int per_page) throws SQLException, JsonProcessingException {
        return new ObjectMapper().writeValueAsString((getPage(page, per_page)));
    }

    public int getNumberOfArticles() throws SQLException {
        return new ArticleDao().getNumberOfArticles();
    }

    public List<Article> getAuthorPage(int page, int per_page, int id) throws SQLException {
        return new ArticleDao().getAuthorPage(page, per_page, id);
    }
    public String getAuthorPageJSON(int page, int per_page, int id) throws SQLException, JsonProcessingException {
        return new ObjectMapper().writeValueAsString((getAuthorPage(page, per_page, id)));
    }
    public int getNumberOfArticlesWrittenBy(int id) throws SQLException {
        return new ArticleDao().getNumberOfArticlesWrittenBy(id);
    }
}