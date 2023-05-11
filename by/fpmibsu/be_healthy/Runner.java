package by.fpmibsu.be_healthy;

import by.fpmibsu.be_healthy.dao.*;
import by.fpmibsu.be_healthy.entity.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static java.sql.Date.valueOf;

public class Runner {
    public static void main(String[] args) {
        ArticleCategory temp_acat = new ArticleCategory();
        temp_acat.setId(3);
        temp_acat.setName("Беременность");

        RecipeCategory temp_rcat = new RecipeCategory();
        temp_rcat.setId(1);
        temp_rcat.setName("Десерты");

        Product temp_product = new Product();
        temp_product.setId(2);
        temp_product.setName("Кофе молотый");
        temp_product.setCarbohydrates(4.1);
        temp_product.setFats(14.4);
        temp_product.setProteins(13.9);
        temp_product.setCalories(202);
        temp_product.setUnit("г.");

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
        temp_recipe.setId(2);
        temp_recipe.setAuthorId(1);
        temp_recipe.setTitle("Бодрящий кофе");
        temp_recipe.setText("""
                1. Включить кофемашину и подождать, пока загорится индикатор готовности.
                                
                2. Если кофемашина оснащена кофемолкой, положить в неё столовую ложку с горкой кофейных зёрен.
                                
                3. Если кофеварка – рожковая, отмерить 7–11 г молотого кофе (мерной ёмкостью или чайной ложкой, в 1 чайной ложке – 3 г). Темпером утрамбовать кофе в рожке, прилагая усилие не менее 20 кг.
                                
                4. Установить рожок в кофеварку.
                                
                5. Подогреть большую толстостенную чашку (к примеру, бокал для латте) для кофе и другую – горячей воды.
                                
                6. Установить чашку для эспрессо в лоток для сбора капель, время приготовления кофе – 25–30 секунд.
                                
                7. Во вторую чашку набрать горячей воды (ориентировочная температура – от +90 до +92 °C).
                                
                8. Смешать ингредиенты. Добавить сахар по вкусу.
                """);
        temp_recipe.setDateOfPublication(valueOf(LocalDate.now()));
        temp_recipe.setCookingTime(10);
        try {
            temp_recipe.setImage(Files.readAllBytes(Paths.get("C:\\Users\\Masha\\Pictures\\calorie_calc\\кофе.jpg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var r_category_list = new ArrayList<RecipeCategory>();
        r_category_list.add(temp_rcat);
        temp_recipe.setCategories(r_category_list);

        Ingredient temp_ing = new Ingredient(temp_product);
        temp_ing.setIngredientId(2);
        temp_ing.setRecipe_id(2);
        temp_ing.setQuantity(10);

        var ing_list = new ArrayList<Ingredient>();
        ing_list.add(temp_ing);
        temp_recipe.setIngredients(ing_list);

        Profile temp_profile = new Profile();
        temp_profile.setId(2);
        temp_profile.setName("Adam");
        temp_profile.setEmail("adamsmith@gmail.com");
        temp_profile.setLogin("adamsmith");
        temp_profile.setPassword("1111");
        temp_profile.setWeight(80);
        temp_profile.setHeight(180);
        temp_profile.setAge(30);
        temp_profile.setActivity(1.1);

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
        //new ProfileDao().create(temp_profile);

        // new RecipeDao().update(temp_recipe);

        System.out.println("getEntityById(1) test:");
        System.out.println(new ArticleCategoryDao().update(temp_acat));
        System.out.println(new RecipeCategoryDao().update(temp_rcat));
        System.out.println(new ProductDao().update(temp_product));
        System.out.println(new MealProductDao().update(temp_m_product));
        System.out.println(new MealDao().update(temp_meal));
        System.out.println(new ArticleDao().update(temp_article));
        System.out.println(new RecipeDao().update(temp_recipe));
        System.out.println(new IngredientDao().update(temp_ing));
        System.out.println(new ProfileDao().update(temp_profile));

        System.out.println("getEntityById(1) test:");
        System.out.println(new ArticleCategoryDao().getEntityById(1));
        System.out.println(new RecipeCategoryDao().getEntityById(1));
        System.out.println(new ProductDao().getEntityById(1));
        System.out.println(new MealProductDao().getEntityById(1));
        System.out.println(new MealDao().getEntityById(1));
        System.out.println(new ArticleDao().getEntityById(1));
        System.out.println(new RecipeDao().getEntityById(1));
        System.out.println(new IngredientDao().getEntityById(1));
        System.out.println(new ProfileDao().getEntityById(1));
        System.out.println("----------------------------------");

        System.out.println("getAll() test:");
        List<ArticleCategory> article_categories = new ArticleCategoryDao().getAll();
        List<RecipeCategory> recipe_categories = new RecipeCategoryDao().getAll();
        List<Product> products =  new ProductDao().getAll();
        List<MealProduct> m_products =  new MealProductDao().getAll();
        List<Meal> meals =  new MealDao().getAll();
        List<Article> articles =  new ArticleDao().getAll();
        List<Recipe> recipes =  new RecipeDao().getAll();
        List<Ingredient> ingredients =  new IngredientDao().getAll();
        List<Profile> profiles =  new ProfileDao().getAll();

        for (ArticleCategory e : article_categories) {
            System.out.println(e);
        }
        System.out.println("----------------------------------");
        for (RecipeCategory e : recipe_categories) {
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
        for (Profile e : profiles) {
            System.out.println(e);
        }
        System.out.println("----------------------------------");
    }
}
