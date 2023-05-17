package by.fpmibsu.be_healthy.entity;
import java.io.Serializable;
import java.util.Objects;

public class ArticleCategory implements Serializable {
    private int id;
    private String name;

    public ArticleCategory(){
    }
    public ArticleCategory(int id, String name){
        this.name = name;
        this.id = id;
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

    @Override
    public String toString() {
        return "Article category "+name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleCategory that)) return false;
        return getId() == that.getId() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
