package by.fpmibsu.be_healthy.entity;
import java.io.Serializable;
public class MealProduct extends Product implements Serializable {
    private int mealProductId;
    private int meal_id;
    public int quantity;

    public MealProduct(){
    }
    public MealProduct(Product p) {
        super(p.getId(), p.getName(), p.getProteins(), p.getFats(), p.getCarbohydrates(), p.getCalories(),p.getUnit());
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMealProductId() {
        return mealProductId;
    }

    public void setMealProductId(int mealProductId) {
        this.mealProductId = mealProductId;
    }

    public int getMeal_id() {
        return meal_id;
    }

    public void setMeal_id(int meal_id) {
        this.meal_id = meal_id;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", quantity: " +quantity + ", meal: "+meal_id;
    }
}
