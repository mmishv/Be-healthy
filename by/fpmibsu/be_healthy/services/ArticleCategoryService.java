package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.entity.ArticleCategory;
import by.fpmibsu.be_healthy.dao.ArticleCategoryDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ArticleCategoryService {
    public List<ArticleCategory> getAll() {
        return new ArticleCategoryDao().getAll();
    }


    public ArticleCategory getEntityById(long id) {
        return new ArticleCategoryDao().getEntityById(id);
    }


    public boolean update(ArticleCategory entity) {
        return new ArticleCategoryDao().update(entity);
    }

    public boolean delete(int id) {
        return new ArticleCategoryDao().delete(id);
    }

    public boolean create(ArticleCategory entity) {
        return new ArticleCategoryDao().create(entity);
    }

    public List<ArticleCategory> getArticleCategoriesByArticleId(int id) {
        return new ArticleCategoryDao().getArticleCategoriesByArticleId(id);
    }

    public String getAllJSON() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new ArticleCategoryService().getAll());
    }
}
