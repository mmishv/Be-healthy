package service;

import by.fpmibsu.be_healthy.entity.RecipeCategory;
import by.fpmibsu.be_healthy.postgres.DataSource;
import by.fpmibsu.be_healthy.service.RecipeCategoryService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class RecipeCategoryServiceTest extends RecipeCategoryService {

    @Before
    public void init() {
        DataSource.makeTest();
    }

    public List<RecipeCategory> getExpectedGetAll() {
        return Arrays.asList(
                new RecipeCategory(2, "Вторые блюда"),
                new RecipeCategory(3, "Выпечка"),
                new RecipeCategory(1, "Десерты"),
                new RecipeCategory(4, "Обед"),
                new RecipeCategory(5, "Ужин"));
    }

    public List<RecipeCategory> getSomeEntities() {
        return Arrays.asList(
                new RecipeCategory(2, "Веганство"),
                new RecipeCategory(1, "Десерты"),
                new RecipeCategory(4, "Обед"),
                new RecipeCategory(5, "Ужин"));
    }

    @Test
    public void recipeCategoryGetAllSizePositiveTest() {
        assertEquals(getExpectedGetAll().size(), getAll().size());
    }

    @Test
    public void recipeCategoryGetAllSizeNegativeTest() {
        assertNotEquals(getSomeEntities().size(), getAll().size());
    }

    @Test
    public void recipeCategoryGetAllPositiveTest() {
        assertEquals("Категории должны быть лексикографически отсортированы",
                getExpectedGetAll(), getAll());
    }

    @Test
    public void recipeCategoryGetAllNegativeTest() {
        assertNotEquals(getSomeEntities(), getAll());
    }

    @Test
    public void recipeCategoryGetEntityByIdPositiveTest() {
        assertEquals(new RecipeCategory(3, "Выпечка"), getEntityById(3));
    }

    @Test
    public void recipeCategoryGetEntityByNonExistentIdPositiveTest() {
        assertNull(getEntityById(10));
    }

    @Test
    public void recipeCategoryUpdatePositiveTest() {
        RecipeCategory updateCategory = new RecipeCategory(3, "Салаты"),
                beforeUpdateCategory = new RecipeCategory(3, "Выпечка");
        assertTrue(update(updateCategory));
        assertEquals(updateCategory, getEntityById(updateCategory.getId()));
        update(beforeUpdateCategory);
    }
    @Test
    public void recipeCategoryUpdateNonExistentNegativeTest() {
        assertFalse(update(new RecipeCategory(100, "Безглютеновые блюда")));
    }
    @Test
    public void recipeCategoryUpdateNullNegativeTest() {
        assertFalse(update(null));
    }
    @Test
    public void recipeCategoryCreatePositiveTest() {
        RecipeCategory newCategory = new RecipeCategory(6, "Диетическое");
        assertTrue(create(newCategory));
        newCategory = getLastAdded();
        assertEquals(newCategory, getEntityById(newCategory.getId()));
        delete(newCategory.getId());
    }

    @Test
    public void recipeCategoryCreateCloneNegativeTest() {
        assertFalse("Названия категорий должны быть уникальными",
                create(new RecipeCategory(1, "Десерты")));
    }

    @Test
    public void recipeCategoryCreateNullNegativeTest() {
        assertFalse(create(null));
    }
    @Test
    public void recipeCategoryCreateNullNameNegativeTest() {
        assertFalse("Названия категорий не могут быть null",
                create(new RecipeCategory()));
    }

    @Test
    public void recipeCategoryDeletePositiveTest() {
        RecipeCategory forDeleteCategory = new RecipeCategory(7, "Постное");
        create(forDeleteCategory);
        forDeleteCategory = getLastAdded();
        assertTrue(delete(forDeleteCategory.getId()));
        assertNull(getEntityById(forDeleteCategory.getId()));
    }

    @Test
    public void recipeCategoryDeleteNonExistentNegativeTest() {
        assertFalse(delete(100));
    }
    @Test
    public void recipeCategoryGetCategoriesByRecipeIdSizePositiveTest() {
        assertEquals(1, getRecipeCategoriesByRecipeId(2).size());
    }

    @Test
    public void recipeCategoryGetCategoriesByRecipeIdPositiveTest() {
        assertEquals(List.of(new RecipeCategory(1, "Десерты")),
                getRecipeCategoriesByRecipeId(1));
    }

    private RecipeCategory getLastAdded() {
        for (var t : getAll())
            if (t.getId() > getExpectedGetAll().size()) return t;
        return null;
    }

    @After
    public void tearDown() {
        DataSource.close();
    }
}
