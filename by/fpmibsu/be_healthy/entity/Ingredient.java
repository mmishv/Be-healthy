package by.fpmibsu.be_healthy.entity;

import java.util.Objects;

public class Ingredient extends Product {
    private int recipe_id;
    private int IngredientId;
    public int quantity;
    public Ingredient(){
    }
    public Ingredient(Product p) {
        super(p.getId(), p.getName(), p.getProteins(), p.getFats(), p.getCarbohydrates(), p.getCalories(),p.getUnit());
    }
    public Ingredient(int id, Product p, int quantity, int recipe_id) {
        super(p.getId(), p.getName(), p.getProteins(), p.getFats(), p.getCarbohydrates(), p.getCalories(),p.getUnit());
        this.recipe_id = recipe_id;
        this.quantity = quantity;
        this.IngredientId = id;
    }
    public Ingredient(Product p, int quantity, int recipe_id) {
        super(p.getId(), p.getName(), p.getProteins(), p.getFats(), p.getCarbohydrates(), p.getCalories(),p.getUnit());
        this.recipe_id = recipe_id;
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getRecipeId() {
        return recipe_id;
    }
    public void setRecipeId(int recipeId) {
        this.recipe_id = recipeId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient that)) return false;
        if (!super.equals(o)) return false;
        return getRecipeId() == that.getRecipeId() && getIngredientId() == that.getIngredientId() && getQuantity() == that.getQuantity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRecipeId(), getIngredientId(), getQuantity());
    }
}
