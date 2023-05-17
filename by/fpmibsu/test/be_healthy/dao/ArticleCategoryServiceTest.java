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


public class ArticleCategoryServiceTest {
    ArticleCategory updateCategory = new ArticleCategory(3, "Футбол"),
            beforeUpdateCategory = new ArticleCategory(3, "Беременность"),
            newCategory = new ArticleCategory(6, "Йога");
    @Before
    public void init() {
        DataSource.makeTest();
    }
    public List<ArticleCategory> createPositiveInput() {
        return  Arrays.asList(
                new ArticleCategory(3, "Беременность"),
                new ArticleCategory(1, "Вегетерианство"),
                new ArticleCategory(4, "Здоровое питание"),
                new ArticleCategory(2, "Семейный отдых"),
                new ArticleCategory(5, "Фитнес")
        );
    }
    public List<ArticleCategory> createNegativeInput() {
        return  Arrays.asList(
                new ArticleCategory(1, "Веганство"),
                new ArticleCategory(2, "Семейный отдых"),
                new ArticleCategory(4, "Здоровое питание"),
                new ArticleCategory(4, "Фитнес")
        );
    }
    @Test
    public void articleCategoryGetAllSizePositiveTest(){
        assertEquals(createPositiveInput().size(),  new ArticleCategoryService().getAll().size());
    }
    @Test
    public void articleCategoryGetAllSizeNegativeTest(){
        assertNotEquals(createNegativeInput().size(),  new ArticleCategoryService().getAll().size());
    }
    @Test
    public void articleCategoryGetAllPositiveTest(){
        assertEquals("Категории должны быть лексикографически отсортированы", createPositiveInput(), new ArticleCategoryService().getAll());
    }
    @Test
    public void articleCategoryGetAllNegativeTest(){
        assertNotEquals(createNegativeInput(),  new ArticleCategoryService().getAll());
    }
    @Test
    public void articleCategoryGetEntityByIdPositiveTest(){
        assertEquals(new ArticleCategory(3, "Беременность"), new ArticleCategoryService().getEntityById(3));
    }

    @Test
    public void articleCategoryGetEntityByNonExistentIdPositiveTest(){
        assertNull(new ArticleCategoryService().getEntityById(10));
    }
    @Test
    public void articleCategoryUpdateTest(){
        boolean result = new ArticleCategoryService().update(updateCategory);
        assertTrue(result);
        assertEquals(updateCategory, new ArticleCategoryService().getEntityById(updateCategory.getId()));
        boolean returnOld = new ArticleCategoryService().update(beforeUpdateCategory);
        assertTrue(returnOld);
    }
    @After
    public void returnData() {
        new ArticleCategoryService().update(beforeUpdateCategory);
    }
}
