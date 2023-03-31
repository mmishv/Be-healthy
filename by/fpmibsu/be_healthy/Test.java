package by.fpmibsu.be_healthy;

import by.fpmibsu.be_healthy.dao.*;
import by.fpmibsu.be_healthy.entity.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import static java.sql.Date.valueOf;

public class Test {
    public static void main(String[] args) {
        ArticleCategoryDao article_category_dao = new ArticleCategoryDao();
        ArticleCategory temp_acat = new ArticleCategory();
        temp_acat.setId(3);
        temp_acat.setName("Беременность");

        RecipeCategoryDao recipe_category_dao = new RecipeCategoryDao();
        RecipeCategory temp_rcat = new RecipeCategory();
        temp_rcat.setId(1);
        temp_rcat.setName("Десерты");

        ForumTopicDao forum_topic_dao = new ForumTopicDao();
        ForumTopic temp_topic = new ForumTopic();
        temp_topic.setId(1);
        temp_topic.setAuthorId(1);
        temp_topic.setCreated_on(valueOf(LocalDate.now()));
        temp_topic.setTitle("Приветствие");

        ForumMessageDao forum_message_dao = new ForumMessageDao();
        ForumMessage temp_message = new ForumMessage();
        temp_message.setId(1);
        temp_message.setAuthorId(1);
        temp_message.setDateOfPublication(valueOf(LocalDate.now()));
        temp_message.setTopic_id(1);
        temp_message.setText("Здравствуйте!");


        try {
            System.out.println("getEntityById(1) test:");
            //System.out.println(new ForumMessageDao().update(temp_message));
            System.out.println(new ArticleCategoryDao().update(temp_acat));
            System.out.println(new RecipeCategoryDao().update(temp_rcat));
            System.out.println(new ForumTopicDao().update(temp_topic));
            // System.out.println(new MealDao().update(temp_meal));
            //article_category_dao.create(temp);
            //recipe_category_dao.create(temp1);
            //forum_topic_dao.create(temp_topic);
            //forum_message_dao.create(temp_message);

            System.out.println("getEntityById(1) test:");
            System.out.println(new ForumMessageDao().getEntityById(1));
            System.out.println(new ArticleCategoryDao().getEntityById(1));
            System.out.println(new RecipeCategoryDao().getEntityById(1));
            System.out.println(new ForumTopicDao().getEntityById(1));
            System.out.println(new MealDao().getEntityById(1));
            System.out.println("----------------------------------");

            System.out.println("getAll() test:");
            List<ArticleCategory> article_categories = new ArticleCategoryDao().getAll();
            List<RecipeCategory> recipe_categories = new RecipeCategoryDao().getAll();
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
