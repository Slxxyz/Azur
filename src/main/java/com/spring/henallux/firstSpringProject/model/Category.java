package com.spring.henallux.firstSpringProject.model;

public class Category {
    private Integer categoryID;
    private String categoryFR;
    private String categoryEN;

    public Category() {
    }

    public Category(Integer id, String name, String description) {
        this.categoryID = id;
        this.categoryFR = name;
        this.categoryEN = description;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryFR() {
        return categoryFR;
    }

    public void setCategoryFR(String label) {
        this.categoryFR = label;
    }

    public String getCategoryEN() {
        return categoryEN;
    }

    public void setCategoryEN(String categoryEN) {
        this.categoryEN = categoryEN;
    }
}
