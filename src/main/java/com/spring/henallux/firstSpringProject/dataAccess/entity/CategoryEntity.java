package com.spring.henallux.firstSpringProject.dataAccess.entity;


import javax.persistence.*;

@Entity
@Table(name = "Category")
public class CategoryEntity {

    @Id
    @Column(name="categoryID")
    private int categoryID;

    @Column(name="categoryFR")
    private String categoryFR;

    @Column(name="categoryEN")
    private String categoryEN;

    public CategoryEntity() {
    }

    public int getCategoryID() {
        return categoryID;
    }


    public String getCategoryFR() {
        return categoryFR;
    }

    public String getCategoryEN() {
        return categoryEN;
    }

}
