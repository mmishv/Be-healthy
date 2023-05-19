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
    RecipeCategory updateCategory = new RecipeCategory(3, "Салаты"),
            beforeUpdateCategory = new RecipeCategory(3, "Выпечка"),
            newCategory = new RecipeCategory(6, "Диетическое"),
            forDeleteCategory = new RecipeCategory(7, "Постное");

    @Before
    public void init() {
        DataSource.makeTest();
    }

    public List<RecipeCategory> createPositiveInput() {
        return Arrays.asList(new RecipeCategory(2, "Вторые блюда"),
                new RecipeCategory(3, "Выпечка"),
                new RecipeCategory(1, "Десерты"),
                new RecipeCategory(4, "Обед"),
                new RecipeCategory(5, "Ужин"));
    }

    public List<RecipeCategory> createNegativeInput() {
        return Arrays.asList(new RecipeCategory(2, "Веганство"),
                new RecipeCategory(1, "Десерты"),
                new RecipeCategory(4, "Обед"),
                new RecipeCategory(5, "Ужин"));
    }

    @Test
    public void recipeCategoryGetAllSizePositiveTest() {
        assertEquals(createPositiveInput().size(), getAll().size());
    }

    @Test
    public void recipeCategoryGetAllSizeNegativeTest() {
        assertNotEquals(createNegativeInput().size(), getAll().size());
    }

    @Test
    public void recipeCategoryGetAllPositiveTest() {
        assertEquals("Категории должны быть лексикографически отсортированы",
                createPositiveInput(), getAll());
    }

    @Test
    public void recipeCategoryGetAllNegativeTest() {
        assertNotEquals(createNegativeInput(), getAll());
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
        assertTrue(update(updateCategory));
        assertEquals(updateCategory, getEntityById(updateCategory.getId()));
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
        assertTrue(create(newCategory));
        newCategory = getLastAdded();
        assertEquals(newCategory, getEntityById(newCategory.getId()));
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
            if (t.getId() > 5) return t;
        return null;
    }

    @After
    public void dataRecovery() {
        update(beforeUpdateCategory);
        delete(newCategory.getId());
        DataSource.close();
    }
}
