package service;

import by.fpmibsu.be_healthy.entity.Product;
import by.fpmibsu.be_healthy.postgres.DataSource;
import by.fpmibsu.be_healthy.service.ProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class ProductServiceTest extends ProductService {
    Product updateProduct = new Product(1, "Молоко 3.6%", 3.1, 3.3, 4.8,61, "мл."),
            beforeUpdateProduct = new Product(1, "Молоко 3.2%", 3.0, 3.2, 4.7,60, "мл."),
            newProduct = new Product(6, "Кефир 3.2%", 3.0, 3.2, 4.7,60, "мл."),
            forDeleteProduct = new Product(7, "Ряженка 3.2%", 3.0, 3.2, 4.7,60, "мл.");

    @Before
    public void init() {
        DataSource.makeTest();
    }

    public List<Product> createPositiveInput() {
        return Arrays.asList (
                new Product(5, "Вода питьевая", 0.0, 0, 0,0, "мл."),
                new Product(2, "Кофе молотый", 13.9, 14.4, 4.1,202, "гр."),
                new Product(1, "Молоко 3.2%", 3.0, 3.2, 4.7,60, "мл."),
                new Product(4, "Огурец", 0.8, 0.1, 2.5,14, "гр."),
                new Product(3, "Помидор", 0.6, 0, 4.2,19, "гр.")
               );
    }

    public List<Product> createNegativeInput() {
        return  Arrays.asList (new Product(1, "Молоко 3.6%", 3.0, 3.2, 4.7,60, "мл."),
                new Product(2, "Кофе молотый", 13.9, 14.4, 4.1,202, "гр."),
                new Product(3, "Помидор", 0.6, 0, 4.2,19, "гр."),
                new Product(4, "Огурец", 0.8, 0.1, 2.5,14, "гр."));
    }

    @Test
    public void productGetAllSizePositiveTest() {
        assertEquals(createPositiveInput().size(), getAll().size());
    }

    @Test
    public void productGetAllSizeNegativeTest() {
        assertNotEquals(createNegativeInput().size(), getAll().size());
    }

    @Test
    public void productGetAllPositiveTest() {
        assertEquals(createPositiveInput(), getAll());
    }

    @Test
    public void productGetAllNegativeTest() {
        assertNotEquals(createNegativeInput(), getAll());
    }

    @Test
    public void productGetEntityByIdPositiveTest() {
        assertEquals(new Product(1, "Молоко 3.2%", 3.0, 3.2, 4.7,60, "мл."), getEntityById(1));
    }

    @Test
    public void productGetEntityByNonExistentIdPositiveTest() {
        assertNull(getEntityById(10));
    }

    @Test
    public void productUpdatePositiveTest() {
        assertTrue(update(updateProduct));
        assertEquals(updateProduct, getEntityById(updateProduct.getId()));
    }

    @Test
    public void productUpdateNonExistentNegativeTest() {
        assertFalse(update(new Product(100, "Cоль", 0, 0, 0,0, "гр.")));
    }

    @Test
    public void productUpdateNullNegativeTest() {
        assertFalse(update(null));
    }

    @Test
    public void productCreatePositiveTest() {
        assertTrue(create(newProduct));
        newProduct = getLastAdded();
        assertEquals(newProduct, getEntityById(newProduct.getId()));
    }

    @Test
    public void productCreateCloneNegativeTest() {
        assertFalse("Названия продуктов должны быть уникальными", create(new Product(7, "Молоко 3.2%", 3.0, 3.2, 4.7,60, "мл.")));
    }

    @Test
    public void productCreateNullNegativeTest() {
        assertFalse(create(null));
    }
    @Test
    public void productCreateWithNegativeCaloriesNegativeTest() {
        assertFalse(create(new Product(100, "Cоль", 0, 0, 0,-50, "гр.")));
    }
    @Test
    public void productCreateWithNegativeProteinsNegativeTest() {
        assertFalse(create(new Product(100, "Cоль", 0, -50, 0,0, "гр.")));
    }
    @Test
    public void productCreateWithNegativeFatsNegativeTest() {
        assertFalse(create(new Product(100, "Cоль", 0, -50, 0,0, "гр.")));
    }
    @Test
    public void productCreateWithNegativeCarbsNegativeTest() {
        assertFalse(create(new Product(100, "Cоль", 0, 0, -50,0, "гр.")));
    }
    @Test
    public void productCreateNullNameNegativeTest() {
        assertFalse("Названия продуктов не могут быть null", create(new Product()));
    }

    @Test
    public void productDeletePositiveTest() {
        create(forDeleteProduct);
        forDeleteProduct = getLastAdded();
        assertTrue(delete(forDeleteProduct.getId()));
        assertNull(getEntityById(forDeleteProduct.getId()));
    }

    @Test
    public void productDeleteNonExistentNegativeTest() {
        assertFalse(delete(100));
    }

    private Product getLastAdded() {
        for (var t : getAll())
            if (t.getId() > 5) return t;
        return null;
    }

    @After
    public void dataRecovery() {
        update(beforeUpdateProduct);
        delete(newProduct.getId());
        DataSource.close();
    }
}
