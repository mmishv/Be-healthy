package by.fpmibsu.be_healthy.entity;
import java.io.Serializable;
import java.util.Objects;

public class RecipeCategory implements Serializable {
    private int id;
    private String name;
    public RecipeCategory(){
        name = null;
    }
    public RecipeCategory(int id, String name){
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecipeCategory that)) return false;
        return getId() == that.getId() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Recipe category "+name;
    }
}
