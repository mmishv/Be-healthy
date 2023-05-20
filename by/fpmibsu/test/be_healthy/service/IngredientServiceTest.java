package service;

import by.fpmibsu.be_healthy.entity.Ingredient;
import by.fpmibsu.be_healthy.entity.Product;
import by.fpmibsu.be_healthy.postgres.DataSource;
import by.fpmibsu.be_healthy.service.IngredientService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class IngredientServiceTest extends IngredientService {

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
    public List<Ingredient> getExpectedGetAll() {
        var products = getAllProductsList();
        return Arrays.asList (
                new Ingredient(1, products.get(1), 10, 2),
                new Ingredient(2, products.get(2), 200, 3),
                new Ingredient(3, products.get(3), 200, 3),
                new Ingredient(4, products.get(4), 250, 2)
        );
    }

    public List<Ingredient> getSomeEntities() {
        var products = getAllProductsList();
        return  Arrays.asList (
                new Ingredient(1, products.get(1), 10, 2),
                new Ingredient(2, products.get(2), 200, 3),
                new Ingredient(3, products.get(3), 200, 3)
        );
    }

    @Test
    public void ingredientGetAllSizePositiveTest() {
        assertEquals(getExpectedGetAll().size(), getAll().size());
    }

    @Test
    public void ingredientGetAllSizeNegativeTest() {
        assertNotEquals(getSomeEntities().size(), getAll().size());
    }

    @Test
    public void ingredientGetAllPositiveTest() {
        assertEquals(getExpectedGetAll(), getAll());
    }

    @Test
    public void ingredientGetAllNegativeTest() {
        assertNotEquals(getSomeEntities(), getAll());
    }

    @Test
    public void ingredientGetEntityByIdPositiveTest() {
        assertEquals(getExpectedGetAll().get(0), getEntityById(1));
    }

    @Test
    public void ingredientGetEntityByNonExistentIdPositiveTest() {
        assertNull(getEntityById(10));
    }

    @Test
    public void ingredientUpdatePositiveTest() {
        Ingredient updateProduct = getExpectedGetAll().get(0),
                beforeUpdateProduct = getExpectedGetAll().get(0);
        updateProduct.setQuantity(150);
        assertTrue(update(updateProduct));
        assertEquals(updateProduct, getEntityById(updateProduct.getIngredientId()));
        update(beforeUpdateProduct);
    }

    @Test
    public void ingredientUpdateNonExistentNegativeTest() {
        Ingredient updateProduct = getExpectedGetAll().get(0);
        updateProduct.setIngredientId(100);
        assertFalse(update(updateProduct));
    }

    @Test
    public void ingredientUpdateNullNegativeTest() {
        assertFalse(update(null));
    }

    @Test
    public void ingredientCreatePositiveTest() {
        Ingredient newProduct = getExpectedGetAll().get(0);
        newProduct.setIngredientId(100);
        assertTrue(create(newProduct));
        newProduct = getLastAdded();
        assertEquals(newProduct, getEntityById(newProduct.getIngredientId()));
        delete(newProduct.getIngredientId());
    }

    @Test
    public void ingredientCreateNullNegativeTest() {
        assertFalse(create(null));
    }

    @Test
    public void ingredientCreateWithNegativeQuantityTest() {
        Ingredient newProduct = getExpectedGetAll().get(0);
        newProduct.setQuantity(-100);
        assertFalse(create(newProduct));
    }

    @Test
    public void ingredientCreateWithZeroQuantityTest() {
        Ingredient newProduct = getExpectedGetAll().get(0);
        newProduct.setQuantity(0);
        assertFalse(create(newProduct));
    }

    @Test
    public void ingredientCreateInNoExistentRecipeNegativeTest() {
        Ingredient newProduct = getExpectedGetAll().get(0);
        newProduct.setRecipeId(100);
        assertFalse(create(newProduct));
    }

    @Test
    public void ingredientCreateWithNonExistentProductNegativeTest() {
        Ingredient newProduct = getExpectedGetAll().get(0);
        newProduct.setId(100);
        assertFalse(create(newProduct));
    }

    @Test
    public void ingredientDeletePositiveTest() {
        Ingredient forDeleteProduct = getExpectedGetAll().get(0);
        forDeleteProduct.setIngredientId(100);
        create(forDeleteProduct);
        forDeleteProduct = getLastAdded();
        assertTrue(delete(forDeleteProduct.getIngredientId()));
        assertNull(getEntityById(forDeleteProduct.getIngredientId()));
    }

    @Test
    public void ingredientDeleteNonExistentNegativeTest() {
        assertFalse(delete(100));
    }
    @Test
    public void  ingredientGetIngredientsByRecipeIdTest(){
        var products = getAllProductsList();
        assertEquals(getIngredientsByRecipeId(2),
                Arrays.asList(
                        new Ingredient(1, products.get(1), 10, 2),
                        new Ingredient(4, products.get(4), 250, 2)));
    }
    @Test
    public void ingredientGetIngredientsByNonExistentRecipeIdTest(){
        assertEquals(getIngredientsByRecipeId(100),
                List.of());
    }
    private Ingredient getLastAdded() {
        for (var t : getAll())
            if (t.getIngredientId() > getExpectedGetAll().size())
                return t;
        return null;
    }
    @After
    public void tearDown() {
        DataSource.close();
    }
}