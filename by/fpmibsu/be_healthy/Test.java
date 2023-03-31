package by.fpmibsu.be_healthy;

import by.fpmibsu.be_healthy.dao.*;
import by.fpmibsu.be_healthy.entity.*;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static java.sql.Date.valueOf;

public class Test {
    public static void main(String[] args) {
        ArticleCategory temp_acat = new ArticleCategory();
        temp_acat.setId(3);
        temp_acat.setName("Беременность");

        RecipeCategory temp_rcat = new RecipeCategory();
        temp_rcat.setId(1);
        temp_rcat.setName("Десерты");

        ForumTopic temp_topic = new ForumTopic();
        temp_topic.setId(1);
        temp_topic.setAuthorId(1);
        temp_topic.setCreated_on(valueOf(LocalDate.now()));
        temp_topic.setTitle("Приветствие");

        ForumMessage temp_message = new ForumMessage();
        temp_message.setId(1);
        temp_message.setAuthorId(1);
        temp_message.setDateOfPublication(valueOf(LocalDate.now()));
        temp_message.setTopic_id(1);
        temp_message.setText("Здравствуйте!");

        Product temp_product = new Product();
        temp_product.setId(1);
        temp_product.setName("молоко 3.2%");
        temp_product.setCarbohydrates(4.7);
        temp_product.setFats(3.2);
        temp_product.setProteins(3);
        temp_product.setCalories(60);
        temp_product.setUnit("мл");

        Meal temp_meal = new Meal();
        temp_meal.setId(1);
        temp_meal.setName("ужин");
        temp_meal.setDateOfMeal(valueOf(LocalDate.now()));
        temp_meal.setTimeOfMeal(Time.valueOf(LocalTime.now()));
        temp_meal.setUser_id(1);

        MealProduct temp_m_product = new MealProduct(temp_product);
        temp_m_product.setMealProductId(1);
        temp_m_product.setQuantity(300);
        temp_m_product.setMeal_id(temp_meal.getId());

        var product_list = new ArrayList<MealProduct>();
        product_list.add(temp_m_product);
        temp_meal.setProducts(product_list);

        Article temp_article = new Article();
        temp_article.setId(1);
        temp_article.setAuthorId(1);
        temp_article.setDateOfPublication(valueOf(LocalDate.now()));
        temp_article.setTitle("Бег по утрам: как начать?");
        temp_article.setFulltext("Мы пока не располагаем такой информацией...");
        var a_category_list = new ArrayList<ArticleCategory>();
        a_category_list.add(temp_acat);
        temp_article.setCategories(a_category_list);

        Recipe temp_recipe = new Recipe();
        temp_recipe.setId(1);
        temp_recipe.setAuthorId(1);
        temp_recipe.setTitle("Молочное молоко");
        temp_recipe.setText("Нагрейте молоко до 30 градусов");
        temp_recipe.setDateOfPublication(valueOf(LocalDate.now()));
        temp_recipe.setCookingTime(5);
        var r_category_list = new ArrayList<RecipeCategory>();
        r_category_list.add(temp_rcat);
        temp_recipe.setCategories(r_category_list);

        Ingredient temp_ing = new Ingredient(temp_product);
        temp_ing.setIngredientId(1);
        temp_ing.setRecipe_id(1);
        temp_ing.setQuantity(200);

        var ing_list = new ArrayList<Ingredient>();
        ing_list.add(temp_ing);
        temp_recipe.setIngredients(ing_list);


        try {
            //new ArticleCategoryDao().create(temp_acat);
            //new RecipeCategoryDao().create(temp_rcat);
            //new ForumTopicDao().create(temp_topic);
            //new ForumMessageDao().create(temp_message);
            //new MealDao().create(temp_meal);
            //new ProductDao().create(temp_product);
            //new MealProductDao().create(temp_m_product);
            //new ArticleDao().create(temp_article);
            //new RecipeDao().create(temp_recipe);
            //new IngredientDao().create(temp_ing);


            System.out.println("getEntityById(1) test:");
            System.out.println(new ForumMessageDao().update(temp_message));
            System.out.println(new ArticleCategoryDao().update(temp_acat));
            System.out.println(new RecipeCategoryDao().update(temp_rcat));
            System.out.println(new ForumTopicDao().update(temp_topic));
            System.out.println(new ProductDao().update(temp_product));
            System.out.println(new MealProductDao().update(temp_m_product));
            System.out.println(new MealDao().update(temp_meal));
            System.out.println(new ArticleDao().update(temp_article));
            System.out.println(new RecipeDao().update(temp_recipe));
            System.out.println(new IngredientDao().update(temp_ing));

            System.out.println("getEntityById(1) test:");
            System.out.println(new ForumMessageDao().getEntityById(1));
            System.out.println(new ArticleCategoryDao().getEntityById(1));
            System.out.println(new RecipeCategoryDao().getEntityById(1));
            System.out.println(new ForumTopicDao().getEntityById(1));
            System.out.println(new ProductDao().getEntityById(1));
            System.out.println(new MealProductDao().getEntityById(1));
            System.out.println(new MealDao().getEntityById(1));
            System.out.println(new ArticleDao().getEntityById(1));
            System.out.println(new RecipeDao().getEntityById(1));
            System.out.println(new IngredientDao().getEntityById(1));
            System.out.println("----------------------------------");

            System.out.println("getAll() test:");
            List<ArticleCategory> article_categories = new ArticleCategoryDao().getAll();
            List<RecipeCategory> recipe_categories = new RecipeCategoryDao().getAll();
            List<ForumMessage> messages =  new ForumMessageDao().getAll();
            List<ForumTopic> topics =  new ForumTopicDao().getAll();
            List<Product> products =  new ProductDao().getAll();
            List<MealProduct> m_products =  new MealProductDao().getAll();
            List<Meal> meals =  new MealDao().getAll();
            List<Article> articles =  new ArticleDao().getAll();
            List<Recipe> recipes =  new RecipeDao().getAll();
            List<Ingredient> ingredients =  new IngredientDao().getAll();
            for (ArticleCategory e : article_categories) {
                System.out.println(e);
            }
            System.out.println("----------------------------------");
            for (RecipeCategory e : recipe_categories) {
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
            System.out.println("----------------------------------");
            for (Product e : products) {
                System.out.println(e);
            }
            System.out.println("----------------------------------");
            for (MealProduct e : m_products) {
                System.out.println(e);
            }
            System.out.println("----------------------------------");
            for (Meal e : meals) {
                System.out.println(e);
            }
            System.out.println("----------------------------------");
            for (Article e : articles) {
                System.out.println(e);
            }
            System.out.println("----------------------------------");
            for (Recipe e : recipes) {
                System.out.println(e);
            }
            System.out.println("----------------------------------");
            for (Ingredient e : ingredients) {
                System.out.println(e);
            }
            System.out.println("----------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
