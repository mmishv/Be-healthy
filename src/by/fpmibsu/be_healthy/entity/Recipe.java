package src.by.fpmibsu.be_healthy.entity;
import java.io.File;
import java.io.Serializable;
import java.util.Date;

public class Recipe implements Serializable {
    private int id;
    private int authorId;
    private String title;
    private Date dateOfPublication;
    private int cookingTime;
    private String text;
    File image;
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


    @Override
    public String toString() {
        return "Author: " + authorId + ", title: " + title  + ", date of publication: "
                + dateOfPublication + ", cooking time: " + cookingTime;
    }
}
