package by.fpmibsu.be_healthy;

import by.fpmibsu.be_healthy.dao.ArticleCategoryDao;
import by.fpmibsu.be_healthy.entity.ArticleCategory;

import java.sql.SQLException;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        ArticleCategoryDao article_category_dao = new ArticleCategoryDao();
        ArticleCategory temp = new ArticleCategory();
        temp.setId(2);
        temp.setName("Семейный отдых");
        try {
            article_category_dao.create(temp);
            List<ArticleCategory> article_categories =  new ArticleCategoryDao().getAll();
            for (ArticleCategory e : article_categories) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
