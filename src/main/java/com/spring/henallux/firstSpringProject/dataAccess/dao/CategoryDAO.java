package com.spring.henallux.firstSpringProject.dataAccess.dao;


import com.spring.henallux.firstSpringProject.dataAccess.entity.CategoryEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryDAO implements CategoryDataAccess {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryDAO(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ArrayList<String> getCategories(String locale){
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        ArrayList<String> categories = new ArrayList<>();
        for(CategoryEntity categoryEntity : categoryEntities){
            categories.add(categoryEntity.getCategory(locale));
        }
        return categories;
    }



}
