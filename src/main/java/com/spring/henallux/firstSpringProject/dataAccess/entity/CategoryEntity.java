package com.spring.henallux.firstSpringProject.dataAccess.entity;


import javax.persistence.*;

@Entity
@Table(name = "category")
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

    public String getCategory(String locale){
        if(locale.equals("en")){
            return categoryEN;
        }
        return categoryFR;
    }

}
