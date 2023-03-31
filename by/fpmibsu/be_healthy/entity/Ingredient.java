package by.fpmibsu.be_healthy.entity;
import java.io.Serializable;
public class Ingredient extends Product implements Serializable{
    private int recipe_id;
    private int IngredientId;
    public int quantity;
    public Ingredient(){
    }
    public Ingredient(Product p) {
        super(p.getId(), p.getName(), p.getProteins(), p.getFats(), p.getCarbohydrates(), p.getCalories(),p.getUnit());
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getRecipe_id() {
        return recipe_id;
    }
    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public int getIngredientId() {
        return IngredientId;
    }

    public void setIngredientId(int ingredientId) {
        IngredientId = ingredientId;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", quantity: " + quantity+", recipe: "+recipe_id;
    }
}
