package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.ProductDAO;
import com.spring.henallux.firstSpringProject.dataAccess.dao.ProductDataAccess;
import com.spring.henallux.firstSpringProject.dataAccess.entity.ProductEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/product")
public class ArticleController {

    private ProductDataAccess productDAO;

    public ArticleController(ProductDAO productDAO){
        this.productDAO = productDAO;
    }

    @GetMapping("/details")
    public String details(@RequestParam(name = "productID") int productID , @RequestParam(name = "locale", defaultValue = "fr") String locale, Model model) {

        System.out.println("Product ID: " + productID);
        ProductEntity product= productDAO.getProductById(productID);

        model.addAttribute("language", locale);
        model.addAttribute("title", "Detail");
        model.addAttribute("product", product);
        model.addAttribute("showHeader", true);
        model.addAttribute("showFooter", true);
        return "integrated:article";
    }

}