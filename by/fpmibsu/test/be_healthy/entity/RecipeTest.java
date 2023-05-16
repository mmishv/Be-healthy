package be_healthy.entity;
import by.fpmibsu.be_healthy.entity.Recipe;
import by.fpmibsu.be_healthy.entity.Ingredient;
import by.fpmibsu.be_healthy.entity.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RecipeTest {
    Recipe recipe = new Recipe();
    @Test
    public void recipeWithOneProductsWithZeroQuantityKBJUTest(){
        Ingredient ingredient = new Ingredient(new Product(1, "", 10, 10, 10, 10, ""));
        ingredient.setQuantity(0);
        ArrayList<Ingredient> ingredients= new ArrayList<>();
        ingredients.add(ingredient);
        recipe.setIngredients(ingredients);
        HashMap<String, BigDecimal> map = new HashMap<>(
                Map.of(
                        "k", BigDecimal.valueOf(0),
                        "b", BigDecimal.valueOf(0),
                        "j", BigDecimal.valueOf(0),
                        "u", BigDecimal.valueOf(0),
                        "weight", BigDecimal.valueOf(0.0)
                )
        );
        assertEquals("All values must be 0", map,  recipe.getKBJU());
    }
    @Test
    public void recipeWithOneProductsKBJUTest(){
        Ingredient ingredient = new Ingredient(new Product(1, "", 10, 10, 10, 10, ""));
        ingredient.setQuantity(157);
        ArrayList<Ingredient> ingredients= new ArrayList<>();
        ingredients.add(ingredient);
        recipe.setIngredients(ingredients);
        HashMap<String, BigDecimal> map = new HashMap<>(
                Map.of(
                        "k", BigDecimal.valueOf(10.0),
                        "b", BigDecimal.valueOf(10.0),
                        "j", BigDecimal.valueOf(10.0),
                        "u", BigDecimal.valueOf(10.0),
                        "weight", BigDecimal.valueOf(157.0)
                )
        );
        assertEquals("Values must be indicated per 100 grams", map,  recipe.getKBJU());
    }
    @Test
    public void recipeWithMultipleProductsKBJUTest(){
        Ingredient ingredient1 = new Ingredient(new Product(1, "", 10, 10, 10, 10, ""));
        ingredient1.setQuantity(157);
        Ingredient ingredient2 = new Ingredient(new Product(1, "", 20, 20, 20, 20, ""));
        ingredient2.setQuantity(257);
        Ingredient ingredient3 = new Ingredient(new Product(1, "", 30, 30, 30, 30, ""));
        ingredient3.setQuantity(357);
        ArrayList<Ingredient> products= new ArrayList<>();
        products.add(ingredient1);
        products.add(ingredient2);
        products.add(ingredient3);
        recipe.setIngredients(products);
        HashMap<String, BigDecimal> map = new HashMap<>(
                Map.of(
                        "k", BigDecimal.valueOf(22.6),
                        "b", BigDecimal.valueOf(22.6),
                        "j", BigDecimal.valueOf(22.6),
                        "u", BigDecimal.valueOf(22.6),
                        "weight", BigDecimal.valueOf(771.0)
                )
        );
        assertEquals("Values must be indicated per 100 grams", map,  recipe.getKBJU());
    }
}