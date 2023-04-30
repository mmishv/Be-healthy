package by.fpmibsu.be_healthy.entity;
import org.hibernate.annotations.Type;

import javax.persistence.Lob;
import java.io.File;
import java.io.Serializable;
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

    public HashMap<String, Integer> getKBJU(){
        HashMap<String, Integer> kbju = new HashMap<>();
        int k = 0, b=0, j=0, u=0;
        for (Ingredient i: getIngredients()){
            k+=i.getQuantity()*i.getCalories();
            b+=i.getQuantity()*i.getProteins();
            j+=i.getQuantity()*i.getFats();
            u+=i.getQuantity()*i.getCarbohydrates();
        }
        kbju.put("k", k);
        kbju.put("b", b);
        kbju.put("j", j);
        kbju.put("u", u);
        return kbju;
    }
}
