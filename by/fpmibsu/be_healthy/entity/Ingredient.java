package by.fpmibsu.be_healthy.entity;
import java.io.Serializable;
public class Ingredient extends Product implements Serializable{
    private int recipe_id;
    public double quantity;
    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    public int getRecipe_id() {
        return recipe_id;
    }
    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", quantity" + quantity+", recipe: "+recipe_id;
    }
}
