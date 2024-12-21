package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @GetMapping("/fragment")
    public String getCategoriesFragment(@RequestParam(name = "locale", defaultValue = "fr") String locale, Model model) {
        List<String> categories = categoryDAO.getCategories(locale);
        model.addAttribute("showHeader", false);
        model.addAttribute("categories", categories);
        return "integrated:categoriesFragment";
    }
}