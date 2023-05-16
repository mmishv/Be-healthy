package entity;
import by.fpmibsu.be_healthy.entity.Meal;
import by.fpmibsu.be_healthy.entity.MealProduct;
import by.fpmibsu.be_healthy.entity.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MealTest {
    Meal meal = new Meal();
    @Test
    public void mealWithoutProductsKBJUTest(){
        meal.setProducts(new ArrayList<>());
        HashMap<String, BigDecimal> map = new HashMap<>(
                Map.of(
                        "k", BigDecimal.valueOf(0.0),
                        "b", BigDecimal.valueOf(0.0),
                        "j", BigDecimal.valueOf(0.0),
                        "u", BigDecimal.valueOf(0.0),
                        "weight", BigDecimal.valueOf(0)
                )
        );
        assertEquals("All values must be 0", map,  meal.getKBJU());
    }

    @Test
    public void mealWithOneProductsWithZeroQuantityKBJUTest(){
        MealProduct product = new MealProduct(new Product(1, "", 10, 10, 10, 10, ""));
        product.setQuantity(0);
        ArrayList<MealProduct> products= new ArrayList<>();
        products.add(product);
        meal.setProducts(products);
        HashMap<String, BigDecimal> map = new HashMap<>(
                Map.of(
                        "k", BigDecimal.valueOf(0.0),
                        "b", BigDecimal.valueOf(0.0),
                        "j", BigDecimal.valueOf(0.0),
                        "u", BigDecimal.valueOf(0.0),
                        "weight", BigDecimal.valueOf(0)
                )
        );
        assertEquals("All values must be 0", map,  meal.getKBJU());
    }
    @Test
    public void mealWithOneProductsKBJUTest(){
        MealProduct product = new MealProduct(new Product(1, "", 10, 10, 10, 10, ""));
        product.setQuantity(157);
        ArrayList<MealProduct> products= new ArrayList<>();
        products.add(product);
        meal.setProducts(products);
        HashMap<String, BigDecimal> map = new HashMap<>(
                Map.of(
                        "k", BigDecimal.valueOf(15.7),
                        "b", BigDecimal.valueOf(15.7),
                        "j", BigDecimal.valueOf(15.7),
                        "u", BigDecimal.valueOf(15.7),
                        "weight", BigDecimal.valueOf(157)
                )
        );
        assertEquals("Values must match the product data", map,  meal.getKBJU());
    }
    @Test
    public void mealWithMultipleProductsKBJUTest(){
        MealProduct product1 = new MealProduct(new Product(1, "", 10, 10, 10, 10, ""));
        product1.setQuantity(157);
        MealProduct product2 = new MealProduct(new Product(1, "", 20, 20, 20, 20, ""));
        product2.setQuantity(257);
        MealProduct product3 = new MealProduct(new Product(1, "", 30, 30, 30, 30, ""));
        product3.setQuantity(357);
        ArrayList<MealProduct> products= new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        meal.setProducts(products);
        HashMap<String, BigDecimal> map = new HashMap<>(
                Map.of(
                        "k", BigDecimal.valueOf(174.2),
                        "b", BigDecimal.valueOf(174.2),
                        "j", BigDecimal.valueOf(174.2),
                        "u", BigDecimal.valueOf(174.2),
                        "weight", BigDecimal.valueOf(771)
                )
        );
        assertEquals("Values must add up proportionally to the quantity", map,  meal.getKBJU());
    }
}