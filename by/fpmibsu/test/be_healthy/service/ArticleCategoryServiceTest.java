package service;

import by.fpmibsu.be_healthy.entity.ArticleCategory;
import by.fpmibsu.be_healthy.postgres.DataSource;
import by.fpmibsu.be_healthy.service.ArticleCategoryService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class ArticleCategoryServiceTest extends ArticleCategoryService {

    @Before
    public void init() {
        DataSource.makeTest();
    }

    public List<ArticleCategory> getExpectedGetAll() {
        return Arrays.asList(
                new ArticleCategory(3, "Беременность"),
                new ArticleCategory(1, "Вегетерианство"),
                new ArticleCategory(4, "Здоровое питание"),
                new ArticleCategory(2, "Семейный отдых"),
                new ArticleCategory(5, "Фитнес"));
    }

    public List<ArticleCategory> getSomeEntities() {
        return Arrays.asList(
                new ArticleCategory(1, "Веганство"),
                new ArticleCategory(2, "Семейный отдых"),
                new ArticleCategory(4, "Здоровое питание"),
                new ArticleCategory(4, "Фитнес"));
    }
    @Test
    public void articleCategoryGetAllSizePositiveTest() {
        assertEquals(getExpectedGetAll().size(), getAll().size());
    }

    @Test
    public void articleCategoryGetAllSizeNegativeTest() {
        assertNotEquals(getSomeEntities().size(), getAll().size());
    }

    @Test
    public void articleCategoryGetAllPositiveTest() {
        assertEquals("Категории должны быть лексикографически отсортированы",
                getExpectedGetAll(), getAll());
    }

    @Test
    public void articleCategoryGetAllNegativeTest() {
        assertNotEquals(getSomeEntities(), getAll());
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
        ArticleCategory updateCategory = new ArticleCategory(3, "Футбол");
        ArticleCategory beforeUpdateCategory = new ArticleCategory(3, "Беременность");
        assertTrue(update(updateCategory));
        assertEquals(updateCategory, getEntityById(updateCategory.getId()));
        update(beforeUpdateCategory);

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
        ArticleCategory newCategory = new ArticleCategory(6, "Йога");
        assertTrue(create(newCategory));
        newCategory = getLastAdded();
        assertEquals(newCategory, getEntityById(newCategory.getId()));
        delete(newCategory.getId());
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
        ArticleCategory forDeleteCategory = new ArticleCategory(7, "Вред курения");
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
        assertEquals(3, getArticleCategoriesByArticleId(2).size());
    }

    @Test
    public void articleCategoryGetCategoriesByArticleIdPositiveTest() {
        assertEquals(Arrays.asList(
                new ArticleCategory(2, "Семейный отдых"),
                new ArticleCategory(3, "Беременность"),
                        new ArticleCategory(1, "Вегетерианство")),
                getArticleCategoriesByArticleId(2));
    }

    private ArticleCategory getLastAdded() {
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
