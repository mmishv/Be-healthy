package by.fpmibsu.be_healthy.service;

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
        var articles = new ArticleDao().getAll();
        for (var article: articles)
            article.setCategories(new ArticleCategoryService().getArticleCategoriesByArticleId(article.getId()));
        return articles;
    }


    public Article getEntityById(Integer id) {
        logger.debug("Get article by id");
        var article = new ArticleDao().getEntityById(id);
        if (article!=null)
            article.setCategories(new ArticleCategoryService().getArticleCategoriesByArticleId(article.getId()));
        return article;
    }

    public boolean update(Article entity) {
        logger.info("Update article");
        return new ArticleDao().update(entity);
    }

    public boolean delete(int id) {
        logger.warn("Debug article");
        return new ArticleDao().delete(id);
    }

    public boolean create(Article entity) {
        logger.info("Create article");
        return new ArticleDao().create(entity);
    }

    public String getAllJSON() throws JsonProcessingException {
        logger.debug("Get all articles in JSON format");
        return new ObjectMapper().writeValueAsString(new ArticleService().getAll());
    }

    public List<Article> getPage(int page, int per_page, boolean moderated) {
        logger.debug("Get page with articles in JSON format");
        var articles = new ArticleDao().getPage(page, per_page, moderated);
        for (var article: articles)
            article.setCategories(new ArticleCategoryService().getArticleCategoriesByArticleId(article.getId()));
        return articles;
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
        var articles = new ArticleDao().getAuthorPage(page, per_page, id);
        for (var article: articles)
            article.setCategories(new ArticleCategoryService().getArticleCategoriesByArticleId(article.getId()));
        return articles;
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

    public String getEntityByIdJSON(Integer id) throws JsonProcessingException {
        logger.debug("Get article by id in JSON format");
        return new ObjectMapper().writeValueAsString(getEntityById(id));
    }

}