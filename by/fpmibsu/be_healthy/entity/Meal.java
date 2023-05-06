package by.fpmibsu.be_healthy.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.util.*;
public class Meal implements Serializable {
    private int id;
    private String name;
    private Time timeOfMeal;
    private Date dateOfMeal;
    private List<MealProduct> products;
    private int user_id;
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

    public Time getTimeOfMeal() {
        return timeOfMeal;
    }

    public void setTimeOfMeal(Time timeOfMeal) {
        this.timeOfMeal = timeOfMeal;
    }

    public List<MealProduct> getProducts() {
        return products;
    }

    public void setProducts(List<MealProduct> products) {
        this.products = products;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getDateOfMeal() {
        return dateOfMeal;
    }

    public void setDateOfMeal(Date dateOfMeal) {
        this.dateOfMeal = dateOfMeal;
    }

    @Override
    public String toString() {
        return "Meal name: "
                + name+ ", time: " + timeOfMeal;
    }
    public HashMap<String, BigDecimal> getKBJU(){
        HashMap<String, BigDecimal> kbju = new HashMap<>();
        double k = 0, b=0, j=0, u=0, weight = 0;
        for (MealProduct i: getProducts()){
            weight+=i.getQuantity();
            k+=1.0*i.getQuantity()*i.getCalories()/100;
            b+=i.getQuantity()*i.getProteins()/100;
            j+=i.getQuantity()*i.getFats()/100;
            u+=i.getQuantity()*i.getCarbohydrates()/100;
        }
        kbju.put("k", BigDecimal.valueOf(k).setScale(1, RoundingMode.HALF_UP));
        kbju.put("b", BigDecimal.valueOf(b).setScale(1, RoundingMode.HALF_UP));
        kbju.put("j", BigDecimal.valueOf(j).setScale(1, RoundingMode.HALF_UP));
        kbju.put("u", BigDecimal.valueOf(u).setScale(1, RoundingMode.HALF_UP));
        kbju.put("weight", BigDecimal.valueOf(((int) weight)));
        return kbju;
    }
}