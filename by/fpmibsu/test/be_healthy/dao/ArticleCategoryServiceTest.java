package dao;

import by.fpmibsu.be_healthy.entity.ArticleCategory;
import by.fpmibsu.be_healthy.postgres.DataSource;
import by.fpmibsu.be_healthy.services.ArticleCategoryService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class ArticleCategoryServiceTest extends ArticleCategoryService {
    ArticleCategory updateCategory = new ArticleCategory(3, "Футбол"),
            beforeUpdateCategory = new ArticleCategory(3, "Беременность"),
            newCategory = new ArticleCategory(6, "Йога"),
            forDeleteCategory = new ArticleCategory(7, "Вред курения");

    @Before
    public void init() {
        DataSource.makeTest();
    }

    public List<ArticleCategory> createPositiveInput() {
        return Arrays.asList(new ArticleCategory(3, "Беременность"),
                new ArticleCategory(1, "Вегетерианство"),
                new ArticleCategory(4, "Здоровое питание"),
                new ArticleCategory(2, "Семейный отдых"),
                new ArticleCategory(5, "Фитнес"));
    }

    public List<ArticleCategory> createNegativeInput() {
        return Arrays.asList(new ArticleCategory(1, "Веганство"),
                new ArticleCategory(2, "Семейный отдых"),
                new ArticleCategory(4, "Здоровое питание"),
                new ArticleCategory(4, "Фитнес"));
    }

    @Test
    public void articleCategoryGetAllSizePositiveTest() {
        assertEquals(createPositiveInput().size(), getAll().size());
    }

    @Test
    public void articleCategoryGetAllSizeNegativeTest() {
        assertNotEquals(createNegativeInput().size(), getAll().size());
    }

    @Test
    public void articleCategoryGetAllPositiveTest() {
        assertEquals("Категории должны быть лексикографически отсортированы",
                createPositiveInput(), getAll());
    }

    @Test
    public void articleCategoryGetAllNegativeTest() {
        assertNotEquals(createNegativeInput(), getAll());
    }

    @Test
    public void articleCategoryGetEntityByIdPositiveTest() {
        assertEquals(new ArticleCategory(3, "Беременность"), getEntityById(3));
    }

    @Test
    public void articleCategoryGetEntityByNonExistentIdPositiveTest() {
        assertNull(getEntityById(10));
    }

    @Test
    public void articleCategoryUpdatePositiveTest() {
        assertTrue(new ArticleCategoryService().update(updateCategory));
        assertEquals(updateCategory, getEntityById(updateCategory.getId()));
    }
    @Test
    public void articleCategoryUpdateNonExistentNegativeTest() {
         assertFalse(update(new ArticleCategory(100, "ЗОЖ")));
    }
    @Test
    public void articleCategoryUpdateNullNegativeTest() {
        assertFalse(update(null));
    }
    @Test
    public void articleCategoryCreatePositiveTest() {
        assertTrue(new ArticleCategoryService().create(newCategory));
        newCategory = getLastAdded();
        assertEquals(newCategory, getEntityById(newCategory.getId()));
    }

    @Test
    public void articleCategoryCreateCloneNegativeTest() {
        assertFalse("Названия категорий должны быть уникальными",
                create(new ArticleCategory(1, "Вегетерианство")));
    }

    @Test
    public void articleCategoryCreateNullNegativeTest() {
        assertFalse(create(null));
    }
    @Test
    public void articleCategoryCreateNullNameNegativeTest() {
        assertFalse("Названия категорий не могут быть null",
                create(new ArticleCategory()));
    }

    @Test
    public void articleCategoryDeletePositiveTest() {
        create(forDeleteCategory);
        forDeleteCategory = getLastAdded();
        assertTrue(delete(forDeleteCategory.getId()));
        assertNull(getEntityById(forDeleteCategory.getId()));
    }

    @Test
    public void articleCategoryDeleteNonExistentNegativeTest() {
        assertFalse(delete(100));
    }
    @Test
    public void articleCategoryGetCategoriesByArticleIdSizePositiveTest() {
        assertEquals(2, getArticleCategoriesByArticleId(2).size());
    }

    @Test
    public void articleCategoryGetCategoriesByArticleIdPositiveTest() {
        assertEquals(Arrays.asList(new ArticleCategory(2, "Семейный отдых"),
                new ArticleCategory(3, "Беременность")),
                getArticleCategoriesByArticleId(2));
    }

    private ArticleCategory getLastAdded() {
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
