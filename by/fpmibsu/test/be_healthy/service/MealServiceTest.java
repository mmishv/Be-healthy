package service;

import by.fpmibsu.be_healthy.entity.Meal;
import by.fpmibsu.be_healthy.entity.MealProduct;
import by.fpmibsu.be_healthy.entity.Product;
import by.fpmibsu.be_healthy.postgres.DataSource;
import by.fpmibsu.be_healthy.service.MealService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class MealServiceTest extends MealService {

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
    public List<Meal> getExpectedGetAll() {
        var products = getAllProductsList();
        var t = getAll();
        try {
            return Arrays.asList(
                    new Meal(2, "обед",
                            new Time(new SimpleDateFormat("HH:mm:ss").parse("12:40:00").getTime()),
                            Date.valueOf("2023-03-28"),
                            List.of(
                                    new MealProduct(4, products.get(0), 200, 2)), 1),
                    new Meal(1, "ужин",
                            new Time(new SimpleDateFormat("HH:mm:ss").parse("03:09:45").getTime()),
                            Date.valueOf("2023-04-28"),
                            Arrays.asList(
                                    new MealProduct(2, products.get(0), 200, 1),
                                    new MealProduct(1, products.get(1), 300, 1),
                                    new MealProduct(3, products.get(2), 100, 1)),
                            1));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Meal> getSomeEntities() {
        var products = getAllProductsList();
        try{
            return Arrays.asList(
                    new Meal(2, "обед", new Time(new SimpleDateFormat("HH:mm:ss").parse("12:40:00").getTime()),
                            Date.valueOf("2023-03-28"),
                            Arrays.asList(
                                    new MealProduct(4, products.get(0), 200, 2)),
                            1));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void mealGetAllSizePositiveTest() {
        assertEquals(getExpectedGetAll().size(), getAll().size());
    }

    @Test
    public void mealGetAllSizeNegativeTest() {
        assertNotEquals(getSomeEntities().size(), getAll().size());
    }

    @Test
    public void mealGetAllPositiveTest() {
        assertEquals("Приемы пищи должны быть отсортированы по времени добавления",
                getExpectedGetAll(), getAll());
    }

    @Test
    public void mealGetAllNegativeTest() {
        assertNotEquals(getSomeEntities(), getAll());
    }

    @Test
    public void mealGetEntityByIdPositiveTest() {
        assertEquals(getExpectedGetAll().get(0),
                getEntityById(2));
    }

    @Test
    public void mealGetEntityByNonExistentIdPositiveTest() {
        assertNull(getEntityById(10));
    }
    @Test
    public void mealUpdateNonExistentNegativeTest() {
        Meal nonExistent = getExpectedGetAll().get(0);
        nonExistent.setId(100);
        assertFalse(update(nonExistent));
    }
    @Test
    public void mealUpdateWithNoProductsNegativeTest() {
        Meal nonExistent = getExpectedGetAll().get(0);
        nonExistent.setProducts(new ArrayList<>());
        assertFalse(update(nonExistent));
    }
    @Test
    public void mealUpdateNullNegativeTest() {
        assertFalse(update(null));
    }
    @Test
    public void mealCreatePositiveTest() {
        Meal newMeal = getExpectedGetAll().get(0);
        newMeal.setId(3);
        assertTrue(create(newMeal));
        newMeal = getLastAdded();
        assertEquals(newMeal, getEntityById(newMeal.getId()));
        delete(newMeal.getId());
    }

    @Test
    public void mealCreateNullNegativeTest() {
        assertFalse(create(null));
    }
    @Test
    public void mealCreateWithZeroProductsNegativeTest() {
        assertFalse("Прием пищи не может не содержать продуктов",
                create(new Meal()));
    }

    @Test
    public void mealDeletePositiveTest() {
        Meal forDeleteMeal = getExpectedGetAll().get(0);
        forDeleteMeal.setId(3);
        create(forDeleteMeal);
        forDeleteMeal = getLastAdded();
        assertTrue(delete(forDeleteMeal.getId()));
        assertNull(getEntityById(forDeleteMeal.getId()));
    }

    @Test
    public void mealDeleteNonExistentNegativeTest() {
        assertFalse(delete(100));
    }

    @Test
    public void mealGetAllByDateAndUserIdSizePositiveTest(){
        assertEquals(1, getAllByDateAndUserId(Date.valueOf("2023-03-28"), 1).size());
    }

    @Test
    public void mealGetAllByDateAndUserIdPositiveTest()  {
        assertEquals(List.of(getExpectedGetAll().get(0)),
                getAllByDateAndUserId(Date.valueOf("2023-03-28"), 1));
    }
    private Meal getLastAdded() {
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
