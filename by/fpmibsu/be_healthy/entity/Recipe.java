package by.fpmibsu.be_healthy.entity;
import org.hibernate.annotations.Type;

import javax.persistence.Lob;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Recipe implements Serializable {
    private int id;
    private int authorId;
    private String title;
    private Date dateOfPublication;
    private int cookingTime;
    private String text;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    byte[] image;
    String base64image;
    private List<Ingredient> ingredients;
    private List<RecipeCategory> categories;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(Date dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<RecipeCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<RecipeCategory> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Author: " + authorId + ", title: " + title  + ", date of publication: "
                + dateOfPublication + ", cooking time: " + cookingTime;
    }

    public String getBase64image() {
        return base64image;
    }

    public void setBase64image(String base64image) {
        this.base64image = base64image;
    }

    public HashMap<String, BigDecimal> getKBJU(){
        HashMap<String, BigDecimal> kbju = new HashMap<>();
        double k = 0, b=0, j=0, u=0, kol=0;
        for (Ingredient i: getIngredients()){
            kol+=i.getQuantity();
            k+=1.0*i.getQuantity()*i.getCalories()/100;
            b+=i.getQuantity()*i.getProteins()/100;
            j+=i.getQuantity()*i.getFats()/100;
            u+=i.getQuantity()*i.getCarbohydrates()/100;
        }
        double coef = kol/100;
        kbju.put("k", new BigDecimal(k/coef).setScale(1, RoundingMode.HALF_UP));
        kbju.put("b", new BigDecimal(b/coef).setScale(1, RoundingMode.HALF_UP));
        kbju.put("j", new BigDecimal(j/coef).setScale(1, RoundingMode.HALF_UP));
        kbju.put("u", new BigDecimal(u/coef).setScale(1, RoundingMode.HALF_UP));
        return kbju;
    }
    public void addIngregient(Ingredient i){
        ingredients.add(i);
    }
}
