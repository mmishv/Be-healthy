package src.by.fpmibsu.be_healthy.entity;
import java.io.Serializable;
public class MealProduct extends Product implements Serializable {
    private int meal_id;
    public double quantity;
    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    public int getMeal_id() {
        return meal_id;
    }
    public void setMeal_id(int meal_id) {
        this.meal_id = meal_id;
    }
    @Override
    public String toString() {
        return "Name: " + getName() + ", quantity" +quantity + ", meal: "+meal_id;
    }
}
