package model;

public class Category {
    private Integer categoryID;
    private String name;

    public Category() {
    }

    public Category(Integer categoryID, String name) {
        this.categoryID = categoryID;
        this.name = name;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
