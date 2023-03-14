package by.fpmibsu.be_healthy.entity;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Article implements Serializable {
    private int id;
    private int authorId;
    private String title;
    private String fulltext;
    private Date dateOfPublication;
    private ArrayList<ArticleCategory> categories;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFulltext() {
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }

    public Date getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(Date dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public ArrayList<ArticleCategory> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<ArticleCategory> categories) {
        this.categories = categories;
    }
    public void addCategory(ArticleCategory newCategory){
        categories.add(newCategory);
    }
    @Override
    public String toString() {
        return "Author: " + authorId + ", title: " + title  + ", date of publication: " + dateOfPublication;
    }
}
