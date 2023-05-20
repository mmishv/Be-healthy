package service;

import by.fpmibsu.be_healthy.entity.MealProduct;
import by.fpmibsu.be_healthy.entity.Product;
import by.fpmibsu.be_healthy.postgres.DataSource;
import by.fpmibsu.be_healthy.service.MealProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class MealProductServiceTest extends MealProductService {

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
    public List<MealProduct> getExpectedGetAll() {
        var products = getAllProductsList();
        return Arrays.asList (
                new MealProduct(1, products.get(1), 300, 1),
                new MealProduct(2, products.get(0), 200, 1),
                new MealProduct(3, products.get(2), 100, 1),
                new MealProduct(4, products.get(0), 200, 2)
        );
    }

    public List<MealProduct> getSomeEntities() {
        var products = getAllProductsList();
        return  Arrays.asList (
                new MealProduct(1, products.get(2), 300, 1),
                new MealProduct(2, products.get(1), 200, 1)
        );
    }

    @Test
    public void mealProductGetAllSizePositiveTest() {
        assertEquals(getExpectedGetAll().size(), getAll().size());
    }

    @Test
    public void mealProductGetAllSizeNegativeTest() {
        assertNotEquals(getSomeEntities().size(), getAll().size());
    }

    @Test
    public void mealProductGetAllPositiveTest() {
        assertEquals(getExpectedGetAll(), getAll());
    }

    @Test
    public void mealProductGetAllNegativeTest() {
        assertNotEquals(getSomeEntities(), getAll());
    }

    @Test
    public void mealProductGetEntityByIdPositiveTest() {
        assertEquals(getExpectedGetAll().get(0), getEntityById(1));
    }

    @Test
    public void mealProductGetEntityByNonExistentIdPositiveTest() {
        assertNull(getEntityById(10));
    }

    @Test
    public void mealProductUpdatePositiveTest() {
        MealProduct updateProduct = getExpectedGetAll().get(0),
                beforeUpdateProduct = getExpectedGetAll().get(0);
        updateProduct.setQuantity(150);
        assertTrue(update(updateProduct));
        assertEquals(updateProduct, getEntityById(updateProduct.getMealProductId()));
        update(beforeUpdateProduct);
    }

    @Test
    public void mealProductUpdateNonExistentNegativeTest() {
        MealProduct updateProduct = getExpectedGetAll().get(0);
        updateProduct.setMealProductId(100);
        assertFalse(update(updateProduct));
    }

    @Test
    public void mealProductUpdateNullNegativeTest() {
        assertFalse(update(null));
    }

    @Test
    public void mealProductCreatePositiveTest() {
        MealProduct newProduct = getExpectedGetAll().get(0);
        newProduct.setMealProductId(100);
        assertTrue(create(newProduct));
        newProduct = getLastAdded();
        assertEquals(newProduct, getEntityById(newProduct.getMealProductId()));
        delete(newProduct.getMealProductId());
    }

    @Test
    public void mealProductCreateNullNegativeTest() {
        assertFalse(create(null));
    }

    @Test
    public void mealProductCreateWithNegativeQuantityTest() {
        MealProduct newProduct = getExpectedGetAll().get(0);
        newProduct.setQuantity(-100);
        assertFalse(create(newProduct));
    }

    @Test
    public void mealProductCreateWithZeroQuantityTest() {
        MealProduct newProduct = getExpectedGetAll().get(0);
        newProduct.setQuantity(0);
        assertFalse(create(newProduct));
    }

    @Test
    public void mealProductCreateInNoExistentMealNegativeTest() {
        MealProduct newProduct = getExpectedGetAll().get(0);
        newProduct.setMealId(100);
        assertFalse(create(newProduct));
    }

    @Test
    public void mealProductCreateWithNonExistentProductNegativeTest() {
        MealProduct newProduct = getExpectedGetAll().get(0);
        newProduct.setId(100);
        assertFalse(create(newProduct));
    }

    @Test
    public void mealProductDeletePositiveTest() {
        MealProduct forDeleteProduct = getExpectedGetAll().get(0);
        forDeleteProduct.setMealProductId(100);
        create(forDeleteProduct);
        forDeleteProduct = getLastAdded();
        assertTrue(delete(forDeleteProduct.getMealProductId()));
        assertNull(getEntityById(forDeleteProduct.getMealProductId()));
    }

    @Test
    public void mealProductDeleteNonExistentNegativeTest() {
        assertFalse(delete(100));
    }
    @Test
    public void  mealProductGetProductsByMealIdTest(){
        var products = getAllProductsList();
        assertEquals(getProductsByMealId(1),
                Arrays.asList(
                    new MealProduct(2, products.get(0), 200, 1),
                    new MealProduct(1, products.get(1), 300, 1),
                    new MealProduct(3, products.get(2), 100, 1)));
    }
    @Test
    public void  mealProductGetProductsByNonExistentMealIdTest(){
        assertEquals(getProductsByMealId(100),
                List.of());
    }
    private MealProduct getLastAdded() {
        for (var t : getAll())
            if (t.getMealProductId() > getExpectedGetAll().size())
                return t;
        return null;
    }
    @After
    public void tearDown() {
        DataSource.close();
    }
}