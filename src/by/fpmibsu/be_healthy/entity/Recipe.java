package by.fpmibsu.be_healthy.entity;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Recipe implements Serializable {
    private int id;
    private ArrayList<Integer> userLikedId;
    private int authorId;
    private String title;
    private Date dateOfPublication;
    private int cookingTime;
    private ArrayList<RecipeCategory> categories;
    private ArrayList<Product> ingredients;
    private String fullText;
    File image;
    public int getId() {
        return id;
    }

    public ArrayList<Integer> getUserLikedId() {
        return userLikedId;
    }

    public void setUserLikedId(ArrayList<Integer> userLikedId) {
        this.userLikedId = userLikedId;
    }
    public void addUserLikedId(int userId){
        userLikedId.add(userId);
    }
    public Integer likesNumber(){
        return userLikedId.size();
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
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

    public ArrayList<RecipeCategory> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<RecipeCategory> categories) {
        this.categories = categories;
    }
    public void addCategory(RecipeCategory newCategory){
        categories.add(newCategory);
    }
    public ArrayList<Product> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Product> ingredients) {
        this.ingredients = ingredients;
    }
    public void addIngredient(Product newIngredient){
        ingredients.add(newIngredient);
    }

    @Override
    public String toString() {
        return "Author: " + authorId + ", title: " + title  + ", date of publication: "
                + dateOfPublication + ", cooking time: " + cookingTime +
                ", quantity of ingredients " + ingredients.size();
    }
}
