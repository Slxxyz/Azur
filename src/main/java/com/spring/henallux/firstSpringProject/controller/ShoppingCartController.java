package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.OrderCustomerDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

@Controller
@RequestMapping(value = "/panier")
public class ShoppingCartController {

    private OrderCustomerDataAccess orderCustomerDataAccess;

    @Autowired
    public ShoppingCartController(OrderCustomerDataAccess orderCustomerDataAccess) {
        this.orderCustomerDataAccess = orderCustomerDataAccess;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String shoppingCart(Model model) {
        HashMap<Integer, Integer> productsOrdered = orderCustomerDataAccess.getProductsOrderedByOrderId(1);
        model.addAttribute("title", "Panier");
        model.addAttribute("showHeader", true);
        model.addAttribute("showFooter", true);
        model.addAttribute("productsOrdered", productsOrdered);
        return "integrated:panier";
    }
}
