package by.fpmibsu.be_healthy;

import by.fpmibsu.be_healthy.dao.*;
import by.fpmibsu.be_healthy.entity.*;

import java.sql.SQLException;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        ArticleCategoryDao article_category_dao = new ArticleCategoryDao();
        ArticleCategory temp = new ArticleCategory();
        temp.setId(3);
        temp.setName("Беременность");
        RecipeCategoryDao recipe_category_dao = new RecipeCategoryDao();
        RecipeCategory temp1 = new RecipeCategory();
        temp1.setId(1);
        temp1.setName("Десерты");
        ForumMessageDao forum_message_dao = new ForumMessageDao();
        ForumMessage temp_message = new ForumMessage();
        temp_message.setId(1);
        temp_message.setAuthorId(123);
        temp_message.setText("Здравствуйте!");
        ForumTopicDao forum_topic_dao = new ForumTopicDao();
        ForumTopic temp_topic = new ForumTopic();
        temp_topic.setId(1);
        temp_topic.setAuthorId(123);
        temp_topic.setTitle("Приветствие");
        temp_topic.setPreview("!");
        try {
            //article_category_dao.create(temp);
            //recipe_category_dao.create(temp1);
            List<ArticleCategory> article_categories =  new ArticleCategoryDao().getAll();
            List<RecipeCategory> recipe_categories =  new RecipeCategoryDao().getAll();
            List<Meal> meals =  new MealDao().getAll();
            List<ForumMessage> messages =  new ForumMessageDao().getAll();
            List<ForumTopic> topics =  new ForumTopicDao().getAll();
            for (ArticleCategory e : article_categories) {
                System.out.println(e);
            }
            System.out.println("----------------------------------");
            for (RecipeCategory e : recipe_categories) {
                System.out.println(e);
            }
            System.out.println("----------------------------------");
            for (Meal e : meals) {
                System.out.println(e);
            }
            System.out.println("----------------------------------");
            for (ForumMessage e : messages) {
                System.out.println(e);
            }
            System.out.println("----------------------------------");
            for (ForumTopic e : topics) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
