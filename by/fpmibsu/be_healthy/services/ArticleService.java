package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.dao.ArticleDao;
import by.fpmibsu.be_healthy.entity.Article;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ArticleService {
    public List<Article> getAll() {
        return new ArticleDao().getAll();
    }


    public Article getEntityById(long id) {
        return new ArticleDao().getEntityById(id);
    }


    public boolean update(Article entity) {
        return new ArticleDao().update(entity);
    }

    public boolean delete(int id) {
        return new ArticleDao().delete(id);
    }

    public boolean create(Article entity) {
        return new ArticleDao().create(entity);
    }

    public String getAllJSON() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new ArticleService().getAll());
    }

    public List<Article> getPage(int page, int per_page) {
        return new ArticleDao().getPage(page, per_page);
    }

    public String getPageJSON(int page, int per_page) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString((getPage(page, per_page)));
    }

    public int getNumberOfArticles() {
        return new ArticleDao().getNumberOfArticles();
    }

    public List<Article> getAuthorPage(int page, int per_page, int id) {
        return new ArticleDao().getAuthorPage(page, per_page, id);
    }

    public String getAuthorPageJSON(int page, int per_page, int id) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString((getAuthorPage(page, per_page, id)));
    }

    public int getNumberOfArticlesWrittenBy(int id) {
        return new ArticleDao().getNumberOfArticlesWrittenBy(id);
    }
}