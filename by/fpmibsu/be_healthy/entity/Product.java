package by.fpmibsu.be_healthy.entity;

import java.util.Objects;

public class Product extends Entity {
    private int id;
    private String name;
    private double proteins;
    private double fats;
    private double carbohydrates;
    private int calories;
    private String unit;
    public Product(){
        name = null;
    }
    public Product(int i, String n, double p, double f, double c, int cal, String u){
        id = i;
        name = n;
        proteins=p;
        fats = f;
        carbohydrates=c;
        calories=cal;
        unit = u;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return getId() == product.getId() && Double.compare(product.getProteins(), getProteins()) == 0 && Double.compare(product.getFats(), getFats()) == 0 && Double.compare(product.getCarbohydrates(), getCarbohydrates()) == 0 && getCalories() == product.getCalories() && getName().equals(product.getName()) && Objects.equals(getUnit(), product.getUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getProteins(), getFats(), getCarbohydrates(), getCalories(), getUnit());
    }

    @Override
    public String toString() {
        return "Name: " + name + ", " + proteins+"/"+fats+"/"+carbohydrates+
               ", " + calories + " calories per 100 grams";
    }

}