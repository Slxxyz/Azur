package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.CategoryEntity;

import java.util.ArrayList;
import java.util.List;


public interface CategoryDataAccess {
    ArrayList<CategoryEntity> getCategories();

    CategoryEntity getCategoryById(int id);
}
