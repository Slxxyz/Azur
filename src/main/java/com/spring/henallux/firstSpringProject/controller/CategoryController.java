package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.CategoryDAO;
import com.spring.henallux.firstSpringProject.dataAccess.dao.CategoryDataAccess;
import com.spring.henallux.firstSpringProject.dataAccess.dao.ProductDAO;
import com.spring.henallux.firstSpringProject.dataAccess.dao.ProductDataAccess;
import com.spring.henallux.firstSpringProject.dataAccess.entity.CategoryEntity;
import com.spring.henallux.firstSpringProject.dataAccess.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryDataAccess categoryDAO;
    private final ProductDataAccess productDAO;

    @Autowired
    public CategoryController(CategoryDAO categoryDAO, ProductDAO productDAO) {
        this.categoryDAO = categoryDAO;
        this.productDAO = productDAO;
    }

    @GetMapping("/fragment")
    public String getCategoriesFragment(@RequestParam(name = "locale", defaultValue = "fr") String locale, Model model) {
        ArrayList<CategoryEntity> categories = categoryDAO.getCategories();

        model.addAttribute("showHeader", false);
        model.addAttribute("language", locale);
        model.addAttribute("categories", categories);
        return "integrated:categoriesFragment";
    }

    @GetMapping("/productsByCategory")
    public String getProductsByCategory(@RequestParam(name = "categoryID") int categoryID, @RequestParam(name = "locale", defaultValue = "fr") String locale, Model model) {
        List<ProductEntity> products = productDAO.getProductsByCategory(categoryID);
        CategoryEntity category = categoryDAO.getCategoryById(categoryID);

        String categoryName = category.getCategoryFR();
        if(locale.equals("en")) {
            categoryName = category.getCategoryEN();
        }

        model.addAttribute("language", locale);
        model.addAttribute("title", "Catalogue");
        model.addAttribute("products", products);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("showHeader", true);
        model.addAttribute("showFooter", true);
        return "integrated:productsByCategory";
    }
}