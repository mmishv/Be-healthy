package service;

import by.fpmibsu.be_healthy.entity.Ingredient;
import by.fpmibsu.be_healthy.entity.Product;
import by.fpmibsu.be_healthy.entity.Recipe;
import by.fpmibsu.be_healthy.entity.RecipeCategory;
import by.fpmibsu.be_healthy.postgres.DataSource;
import by.fpmibsu.be_healthy.service.RecipeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class RecipeServiceTest extends RecipeService {

    @Before
    public void init() {
        DataSource.makeTest();
    }
    public List<Product> getAllProductsList() {
        return Arrays.asList (
                new Product(1, "Молоко 3.2%", 3.0, 3.2, 4.7,60, "мл."),
                new Product(2, "Кофе молотый", 13.9, 14.4, 4.1,202, "гр."),
                new Product(3, "Помидор", 0.6, 0, 4.2,19, "гр."),
                new Product(4, "Огурец", 0.8, 0.1, 2.5,14, "гр."),
                new Product(5, "Вода питьевая", 0.0, 0, 0,0, "мл.")
        );
    }
    public List<RecipeCategory> getAllCategoriesList() {
        return Arrays.asList(
                new RecipeCategory(1, "Десерты"),
                new RecipeCategory(2, "Вторые блюда"),
                new RecipeCategory(3, "Выпечка"),
                new RecipeCategory(4, "Обед"),
                new RecipeCategory(5, "Ужин"));
    }

    public List<Recipe> getExpectedGetAll() {
        var products = getAllProductsList();
        var categories = getAllCategoriesList();
        try {
            return Arrays.asList(
                    new Recipe(3, 1, "Салат овощной", Date.valueOf("2023-05-02"), 10,
                            "11111",
                            Files.readAllBytes(Paths.get(
                                    "C:\\Users\\Masha\\IdeaProjects\\Be-healthy\\by\\fpmibsu\\test\\be_healthy\\service\\recipe_images\\салат.jpg")),
                            false,
                            Arrays.asList(
                                    new Ingredient(2, products.get(2), 200, 3),
                                    new Ingredient(3, products.get(3), 200, 3)),
                            List.of()),
                    new Recipe(2, 1, "Бодрящий кофе", Date.valueOf("2023-04-28"), 10,
                            "1. Включить кофемашину...",
                            Files.readAllBytes(Paths.get(
                                    "C:\\Users\\Masha\\IdeaProjects\\Be-healthy\\by\\fpmibsu\\test\\be_healthy\\service\\recipe_images\\кофе.jpg")),
                            true,
                            Arrays.asList(
                                    new Ingredient(1, products.get(1), 10, 2),
                                    new Ingredient(4, products.get(4), 250, 2)),
                            List.of(categories.get(0))),
                    new Recipe(1, 1, "Молочное молоко", Date.valueOf("2023-04-26"), 5,
                            "Нагрейте молоко до 30 градусов",
                            Files.readAllBytes(Paths.get(
                                    "C:\\Users\\Masha\\IdeaProjects\\Be-healthy\\by\\fpmibsu\\test\\be_healthy\\service\\recipe_images\\молоко.jpg")),
                            true,
                            List.of(),
                            List.of(categories.get(0)))
                    );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Recipe> getSomeEntities() {
        var products = getAllProductsList();
        try{
            return List.of(
                    new Recipe(3, 1, "Салат овощной", Date.valueOf("2023-05-02"), 10,
                            "11111",
                            Files.readAllBytes(Paths.get(
                                    "C:\\Users\\Masha\\IdeaProjects\\Be-healthy\\by\\fpmibsu\\test\\be_healthy\\service\\recipe_images\\салат.jpg")),
                            false,
                            Arrays.asList(
                                    new Ingredient(2, products.get(2), 200, 3),
                                    new Ingredient(3, products.get(3), 200, 3)),
                            List.of())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void recipeGetAllSizePositiveTest() {
        assertEquals(getExpectedGetAll().size(), getAll().size());
    }

    @Test
    public void recipeGetAllSizeNegativeTest() {
        assertNotEquals(getSomeEntities().size(), getAll().size());
    }

    @Test
    public void recipeGetAllPositiveTest() {
        assertEquals("Рецепты должны быть отсортированы по времени добавления",
                getExpectedGetAll(), getAll());
    }

    @Test
    public void recipeGetAllNegativeTest() {
        assertNotEquals(getSomeEntities(), getAll());
    }

    @Test
    public void recipeGetEntityByIdPositiveTest() {
        assertEquals(getExpectedGetAll().get(0),
                getEntityById(3));
    }

    @Test
    public void recipeGetEntityByNonExistentIdPositiveTest() {
        assertNull(getEntityById(10));
    }
    @Test
    public void recipeUpdateNonExistentNegativeTest() {
        Recipe nonExistent = getExpectedGetAll().get(0);
        nonExistent.setId(100);
        assertFalse(update(nonExistent));
    }
    @Test
    public void recipeUpdateNullNegativeTest() {
        assertFalse(update(null));
    }
    @Test
    public void recipeCreatePositiveTest() {
        Recipe newRecipe = getExpectedGetAll().get(0);
        newRecipe.setId(5);
        assertTrue(create(newRecipe));
        newRecipe = getLastAdded();
        assertEquals(newRecipe, getEntityById(newRecipe.getId()));
        delete(newRecipe.getId());
    }

    @Test
    public void recipeCreateNullNegativeTest() {
        assertFalse(create(null));
    }

    @Test
    public void recipeDeletePositiveTest() {
        Recipe forDeleteRecipe = getExpectedGetAll().get(0);
        forDeleteRecipe.setId(3);
        create(forDeleteRecipe);
        forDeleteRecipe = getLastAdded();
        assertTrue(delete(forDeleteRecipe.getId()));
        assertNull(getEntityById(forDeleteRecipe.getId()));
    }

    @Test
    public void recipeDeleteNonExistentNegativeTest() {
        assertFalse(delete(100));
    }
    @Test
    public void recipeGetNonModeratedPageWithOnePerPage(){
        var recipes = getExpectedGetAll();
        assertEquals(getPage(1, 1, true), List.of(recipes.get(1)));
    }
    @Test
    public void recipeGetModeratedPageWithOnePerPage(){
        var recipes = getExpectedGetAll();
        assertEquals(getPage(1, 1, false), List.of(recipes.get(0)));
    }
    @Test
    public void recipeGetNonModeratedPageWithSeveralPerPage(){
        var recipes = getExpectedGetAll();
        assertEquals(getPage(1, 5, true), List.of(recipes.get(1), recipes.get(2)));
    }
    @Test
    public void recipeGetModeratedPageWithSeveralPerPage(){
        var recipes = getExpectedGetAll();
        assertEquals(getPage(1, 5, false), List.of(recipes.get(0)));
    }
    @Test
    public void recipeGetNonExistentModeratedPage(){
        assertEquals(getPage(5, 1, true), List.of());
    }
    @Test
    public void recipeGetNonExistentNonModeratedPage(){
        assertEquals(getPage(5, 1, false), List.of());
    }

    @Test
    public void recipeGetCategoryPageWithOnePerPage(){
        var recipes = getExpectedGetAll();
        assertEquals(getCategoryPage(1, 1, 1), List.of(recipes.get(1)));
    }
    @Test
    public void recipeGetEmptyCategoryPage(){
        assertEquals(getCategoryPage(1, 1, 3), List.of());
    }
    @Test
    public void recipeGetCategoryPageWithSeveralPerPage(){
        var recipes = getExpectedGetAll();
        assertEquals(getCategoryPage(1, 5, 1), List.of(recipes.get(1), recipes.get(2)));
    }

    @Test
    public void recipeGetNonExistentCategoryPage(){
        assertEquals(getCategoryPage(5, 1, 1), List.of());
    }
    @Test
    public void recipeGetNonExistentCategoryPageInNonExistentCategory(){
        assertEquals(getCategoryPage(5, 1, 100), List.of());
    }
    @Test
    public void recipeGetAuthorPageWithOnePerPage(){
        var recipes = getExpectedGetAll();
        assertEquals(getAuthorPage(1, 1, 1),
                List.of(recipes.get(0)));
    }
    @Test
    public void recipeGetEmptyAuthorPage(){
        assertEquals(getAuthorPage(1, 1, 2), List.of());
    }
    @Test
    public void recipeGetAuthorPageWithSeveralPerPage(){
        var recipes = getExpectedGetAll();
        assertEquals(getAuthorPage(1, 5, 1),
                List.of(recipes.get(0), recipes.get(1), recipes.get(2)));
    }

    @Test
    public void recipeGetNonExistentAuthorPageOfExistentAuthor(){
        assertEquals(getAuthorPage(5, 1, 1), List.of());
    }
    @Test
    public void recipeGetNonExistentAuthorPageOfNonExistentAuthor(){
        assertEquals(getAuthorPage(5, 1, 100), List.of());
    }
    @Test
    public void recipeGetNumberOfRecipesInCategory(){
        assertEquals(2, getNumberOfRecipesInCategory(1));
    }

    @Test
    public void recipeGetNumberOfModeratedRecipes(){
        assertEquals(2, getNumberOfRecipes(true));
    }

    @Test
    public void recipeGetNumberOfNonModeratedRecipes(){
        assertEquals(1, getNumberOfRecipes(false));
    }
    @Test
    public void recipeGetNumberOfRecipesWrittenBy(){
        assertEquals(3, getNumberOfRecipesWrittenBy(1));
    }
    @Test
    public void recipeUpdateModerationStatus(){
        var recipes = getExpectedGetAll();
        assertTrue(updateModerationStatus(recipes.get(0).getId(),true));
        updateModerationStatus(recipes.get(0).getId(),false);
    }
    @Test
    public void recipeUpdateModerationOfNonExistentRecipeStatus(){
        assertFalse(updateModerationStatus(100,true));
    }
    private Recipe getLastAdded() {
        for (var t : getAll())
            if (t.getId() > getExpectedGetAll().size())
                return t;
        return null;
    }

    @After
    public void tearDown() {
        DataSource.close();
    }
}
