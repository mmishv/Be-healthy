package by.fpmibsu.be_healthy.entity;

import java.util.Objects;

public class MealProduct extends Product {
    private int mealProductId;

    private int mealId;
    private int quantity;

    public MealProduct(){
    }
    public MealProduct(Product p) {
        super(p.getId(), p.getName(), p.getProteins(), p.getFats(), p.getCarbohydrates(), p.getCalories(),p.getUnit());
    }
    public MealProduct(int id, Product p, int q, int mealId) {
        super(p.getId(), p.getName(), p.getProteins(), p.getFats(), p.getCarbohydrates(), p.getCalories(),p.getUnit());
        this.mealProductId = id;
        quantity = q;
        this.mealId = mealId;
    }
    public MealProduct(Product p, int q, int mealId) {
        super(p.getId(), p.getName(), p.getProteins(), p.getFats(), p.getCarbohydrates(), p.getCalories(),p.getUnit());
        quantity = q;
        this.mealId = mealId;
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

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int meal_id) {
        this.mealId = meal_id;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", quantity: " +quantity + ", meal: "+ mealId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealProduct that)) return false;
        if (!super.equals(o)) return false;
        return getMealProductId() == that.getMealProductId() && getId() == that.getId() && getMealId() == that.getMealId() && getQuantity() == that.getQuantity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMealProductId(), getId(), getMealId(), getQuantity());
    }
}
