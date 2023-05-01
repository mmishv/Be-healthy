package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.entity.ArticleCategory;
import by.fpmibsu.be_healthy.dao.ArticleCategoryDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.List;

public class ArticleCategoryService {
    public List<ArticleCategory> getAll() throws SQLException {
        return new ArticleCategoryDao().getAll();
    }


    public ArticleCategory getEntityById(long id) throws SQLException {
        return new ArticleCategoryDao().getEntityById(id);
    }


    public boolean update(ArticleCategory entity) throws SQLException {
        return new ArticleCategoryDao().update(entity);
    }

    public boolean delete(ArticleCategory entity) throws SQLException {
        return new ArticleCategoryDao().delete(entity);
    }

    public boolean create(ArticleCategory entity) throws SQLException {
        return new ArticleCategoryDao().create(entity);
    }

    public List<ArticleCategory> getArticleCategoriesByArticleId(int id) throws SQLException {
        return new ArticleCategoryDao().getArticleCategoriesByArticleId(id);
    }

    public String getAllJSON() throws JsonProcessingException, SQLException {
        return new ObjectMapper().writeValueAsString(new ArticleCategoryService().getAll());
    }
}
