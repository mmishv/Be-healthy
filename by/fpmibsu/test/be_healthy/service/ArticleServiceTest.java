package service;

import by.fpmibsu.be_healthy.entity.*;
import by.fpmibsu.be_healthy.postgres.DataSource;
import by.fpmibsu.be_healthy.service.ArticleService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class ArticleServiceTest extends ArticleService {

    @Before
    public void init() {
        DataSource.makeTest();
    }
    public List<ArticleCategory> getAllCategoriesList() {
        return Arrays.asList(
                new ArticleCategory(1, "Вегетерианство"),
                new ArticleCategory(2, "Семейный отдых"),
                new ArticleCategory(3, "Беременность"),
                new ArticleCategory(4, "Здоровое питание"),
                new ArticleCategory(5, "Фитнес"));
    }

    public List<Article> getExpectedGetAll() {
        var categories = getAllCategoriesList();
        return Arrays.asList(
                new Article(2, 1, "Путешествие во время беременности",
                        "Большинство врачей сходится во мнении...",
                        Date.valueOf("2023-05-07"),
                        List.of(  categories.get(1), categories.get(2), categories.get(0)),
                        true),
                new Article(1, 1, "Бег по утрам: как начать?",
                        "Бег по утрам — традиционный атрибут здорового образа жизни и фитнеса.",
                        Date.valueOf("2023-04-28"),
                        List.of(categories.get(3)), true)
        );
    }

    public List<Article> getSomeEntities() {
        var categories = getAllCategoriesList();
        return List.of(
                new Article(2, 1, "Путешествие во время беременности",
                        "Большинство врачей сходится во мнении...",
                        Date.valueOf("2023-05-07"),
                        List.of(categories.get(0), categories.get(1), categories.get(2)),
                        true)
        );

    }

    @Test
    public void recipeGetAllSizePositiveTest() {
        assertEquals(getExpectedGetAll().size(), getAll().size());
    }

    @Test
    public void articleGetAllSizeNegativeTest() {
        assertNotEquals(getSomeEntities().size(), getAll().size());
    }

    @Test
    public void articleGetAllPositiveTest() {
        assertEquals("Рецепты должны быть отсортированы по времени добавления",
                getExpectedGetAll(), getAll());
    }

    @Test
    public void articleGetAllNegativeTest() {
        assertNotEquals(getSomeEntities(), getAll());
    }

    @Test
    public void articleGetEntityByIdPositiveTest() {
        assertEquals(getExpectedGetAll().get(0),
                getEntityById(2));
    }

    @Test
    public void articleGetEntityByNonExistentIdPositiveTest() {
        assertNull(getEntityById(10));
    }
    @Test
    public void articleUpdateNonExistentNegativeTest() {
        Article nonExistent = getExpectedGetAll().get(0);
        nonExistent.setId(100);
        assertFalse(update(nonExistent));
    }
    @Test
    public void articleUpdateNullNegativeTest() {
        assertFalse(update(null));
    }
    @Test
    public void articleCreatePositiveTest() {
        Article newArticle = getExpectedGetAll().get(0);
        newArticle.setId(5);
        assertTrue(create(newArticle));
        newArticle = getLastAdded();
        assertEquals(newArticle, getEntityById(newArticle.getId()));
        delete(newArticle.getId());
    }

    @Test
    public void articleCreateNullNegativeTest() {
        assertFalse(create(null));
    }

    @Test
    public void articleDeletePositiveTest() {
        Article forDeleteArticle = getExpectedGetAll().get(0);
        forDeleteArticle.setId(3);
        create(forDeleteArticle);
        forDeleteArticle = getLastAdded();
        assertTrue(delete(forDeleteArticle.getId()));
        assertNull(getEntityById(forDeleteArticle.getId()));
    }

    @Test
    public void articleDeleteNonExistentNegativeTest() {
        assertFalse(delete(100));
    }
    @Test
    public void articleGetNonModeratedPageWithOnePerPage(){
        assertEquals(getPage(1, 1, false), List.of());
    }
    @Test
    public void articleGetModeratedPageWithOnePerPage(){
        var articles = getExpectedGetAll();
        assertEquals(getPage(1, 1, true), List.of(articles.get(0)));
    }
    @Test
    public void articleGetNonModeratedPageWithSeveralPerPage(){
        assertEquals(getPage(1, 5, false), List.of());
    }
    @Test
    public void articleGetModeratedPageWithSeveralPerPage(){
        var articles = getExpectedGetAll();
        assertEquals(getPage(1, 5, true), List.of(articles.get(0), articles.get(1)));
    }
    @Test
    public void articleGetNonExistentModeratedPage(){
        assertEquals(getPage(5, 1, true), List.of());
    }
    @Test
    public void articleGetNonExistentNonModeratedPage(){
        assertEquals(getPage(5, 1, false), List.of());
    }
    @Test
    public void articleGetAuthorPageWithOnePerPage(){
        var articles = getExpectedGetAll();
        assertEquals(getAuthorPage(1, 1, 1),
                List.of(articles.get(0)));
    }
    @Test
    public void articleGetEmptyAuthorPage(){
        assertEquals(getAuthorPage(1, 1, 2), List.of());
    }
    @Test
    public void articleGetAuthorPageWithSeveralPerPage(){
        var recipes = getExpectedGetAll();
        assertEquals(getAuthorPage(1, 5, 1),
                List.of(recipes.get(0), recipes.get(1)));
    }

    @Test
    public void articleGetNonExistentAuthorPageOfExistentAuthor(){
        assertEquals(getAuthorPage(5, 1, 1), List.of());
    }
    @Test
    public void articleGetNonExistentAuthorPageOfNonExistentAuthor(){
        assertEquals(getAuthorPage(5, 1, 100), List.of());
    }
    @Test
    public void articleGetNumberOfRecipesWrittenBy(){
        assertEquals(2, getNumberOfArticlesWrittenBy(1));
    }
    @Test
    public void articleUpdateModerationStatus(){
        var articles = getExpectedGetAll();
        assertTrue(updateModerationStatus(articles.get(0).getId(),true));
    }
    @Test
    public void articleUpdateModerationOfNonExistentRecipeStatus(){
        assertFalse(updateModerationStatus(100,true));
    }
    private Article getLastAdded() {
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
