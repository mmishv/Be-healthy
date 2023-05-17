package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.dao.ArticleDao;
import by.fpmibsu.be_healthy.entity.Article;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ArticleService {
    private static final Logger logger = LogManager.getLogger(ArticleService.class);
    public List<Article> getAll() {
        logger.debug("Get all articles");
        return new ArticleDao().getAll();
    }


    public Article getEntityById(long id) {
        logger.debug("Get article by id");
        return new ArticleDao().getEntityById(id);
    }


    public boolean update(Article entity) {
        logger.debug("Update article");
        return new ArticleDao().update(entity);
    }

    public boolean delete(int id) {
        logger.debug("Debug article");
        return new ArticleDao().delete(id);
    }

    public boolean create(Article entity) {
        logger.debug("Create article");
        return new ArticleDao().create(entity);
    }

    public String getAllJSON() throws JsonProcessingException {
        logger.debug("Get all articles in JSON format");
        return new ObjectMapper().writeValueAsString(new ArticleService().getAll());
    }

    public List<Article> getPage(int page, int per_page, boolean moderated) {
        logger.debug("Get page with articles in JSON format");
        return new ArticleDao().getPage(page, per_page, moderated);
    }

    public String getPageJSON(int page, int per_page, boolean moderated) throws JsonProcessingException {
        logger.debug("Get page with articles in JSON format");
        return new ObjectMapper().writeValueAsString((getPage(page, per_page, moderated)));
    }

    public int getNumberOfArticles(boolean moderated) {
        logger.debug("Get number of articles");
        return new ArticleDao().getNumberOfArticles(moderated);
    }

    public List<Article> getAuthorPage(int page, int per_page, int id) {
        logger.debug("Get page with articles by a specific author");
        return new ArticleDao().getAuthorPage(page, per_page, id);
    }

    public String getAuthorPageJSON(int page, int per_page, int id) throws JsonProcessingException {
        logger.debug("Get page with articles by a specific author in JSON format");
        return new ObjectMapper().writeValueAsString((getAuthorPage(page, per_page, id)));
    }

    public int getNumberOfArticlesWrittenBy(int id) {
        logger.debug("Get number of articles by a specific author");
        return new ArticleDao().getNumberOfArticlesWrittenBy(id);
    }
    public boolean updateModerationStatus(int id, boolean moderated){
        logger.debug("Update article moderation status");
        return new ArticleDao().updateModerationStatus(id, moderated);
    }
}