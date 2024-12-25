package com.spring.henallux.firstSpringProject.dataAccess.entity;


import javax.persistence.*;

@Entity
@Table(name = "Category")
public class CategoryEntity {

    @Id
    @Column(name="category_id")
    private int categoryID;

    @Column(name="category_fr")
    private String categoryFR;

    @Column(name="category_en")
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
