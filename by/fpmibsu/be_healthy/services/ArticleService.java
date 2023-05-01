package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.entity.Article;
import by.fpmibsu.be_healthy.dao.ArticleDao;
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
}