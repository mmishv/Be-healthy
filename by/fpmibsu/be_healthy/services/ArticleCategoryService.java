package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.entity.ArticleCategory;
import by.fpmibsu.be_healthy.dao.ArticleCategoryDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ArticleCategoryService {
    private static final Logger logger = LogManager.getLogger(ArticleCategoryService.class);
    public List<ArticleCategory> getAll() {
        logger.debug("Get all article categories");
        return new ArticleCategoryDao().getAll();
    }


    public ArticleCategory getEntityById(long id) {
        logger.debug("Get article category by id");
        return new ArticleCategoryDao().getEntityById(id);
    }


    public boolean update(ArticleCategory entity) {
        logger.debug("Update article category");
        return new ArticleCategoryDao().update(entity);
    }

    public boolean delete(int id) {
        logger.debug("Delete article category");
        return new ArticleCategoryDao().delete(id);
    }

    public boolean create(ArticleCategory entity) {
        logger.debug("Create article category");
        return new ArticleCategoryDao().create(entity);
    }

    public List<ArticleCategory> getArticleCategoriesByArticleId(int id) {
        logger.debug("Get article category by article id");
        return new ArticleCategoryDao().getArticleCategoriesByArticleId(id);
    }

    public String getAllJSON() throws JsonProcessingException {
        logger.debug("Get all article categories in JSON format");
        return new ObjectMapper().writeValueAsString(new ArticleCategoryService().getAll());
    }
}
