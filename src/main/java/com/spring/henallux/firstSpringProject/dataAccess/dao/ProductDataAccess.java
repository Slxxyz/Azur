package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public interface ProductDataAccess {
    List<ProductEntity> getProductsByCategory(int categoryID);

    ProductEntity getProductById(int id);
}
