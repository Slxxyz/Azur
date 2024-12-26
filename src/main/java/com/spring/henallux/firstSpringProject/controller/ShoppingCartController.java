package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.CategoryDAO;
import com.spring.henallux.firstSpringProject.dataAccess.dao.OrderCustomerDataAccess;
import com.spring.henallux.firstSpringProject.dataAccess.dao.ProductDAO;
import com.spring.henallux.firstSpringProject.dataAccess.dao.ProductDataAccess;
import com.spring.henallux.firstSpringProject.model.OrderCustomer;
import com.spring.henallux.firstSpringProject.model.OrderLine;
import com.spring.henallux.firstSpringProject.model.Product;
import com.spring.henallux.firstSpringProject.model.ShoppingCart;
import com.spring.henallux.firstSpringProject.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/panier")
public class ShoppingCartController {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

    private OrderCustomerDataAccess orderCustomerDataAccess;

    private ShoppingCartService shoppingCartService;

    private ProductDataAccess productDataAccess;

    @ModelAttribute("panier")
    public ShoppingCart initializeCart() {
        return new ShoppingCart(); // Crée un panier vide pour l'utilisateur
    }

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, OrderCustomerDataAccess orderCustomerDataAccess, ProductDataAccess productDataAccess) {
        this.shoppingCartService = shoppingCartService;
        this.orderCustomerDataAccess = orderCustomerDataAccess;
        this.productDataAccess = productDataAccess;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String shoppingCart(Model model, @ModelAttribute(value = "panier") ShoppingCart shoppingCart) {
        Integer orderId = 3; // Utiliser l'order_id 1
        shoppingCart.setProductsOrdered(orderCustomerDataAccess.getProductsOrderedByOrderId(3));
        System.out.println(shoppingCart.getProductsOrdered().toString());
        model.addAttribute("title", "Panier");
        model.addAttribute("showHeader", true);
        model.addAttribute("showFooter", true);
        model.addAttribute("productsOrdered", shoppingCart.getProductsOrdered());
        return "integrated:panier";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateQuantity(@RequestBody Map<String, Object> payload, @ModelAttribute("panier") ShoppingCart shoppingCart) {
        Map<String, Object> response = new HashMap<>();
        try {
            Integer productId = Integer.parseInt((String) payload.get("productId"));
            Integer quantity = Integer.parseInt((String) payload.get("quantity"));
            shoppingCart.setProductsOrdered(orderCustomerDataAccess.getProductsOrderedByOrderId(1));
            shoppingCartService.updateProductQuantity(shoppingCart, productId, quantity);
            OrderLine orderLine = shoppingCart.getProductsOrdered().get(productId);
            response.put("success", true);
            response.put("newSubTotal", orderLine.getSubTotal());
            response.put("totalAmount", orderLine.getOrder().getTotalAmount());
        } catch (NumberFormatException e) {
            response.put("success", false);
            response.put("error", "Invalid input format");
        }
        return response;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> removeOrderLine(
            @RequestBody Map<String, Object> payload,
            @ModelAttribute("panier") ShoppingCart shoppingCart
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            Integer productId = Integer.parseInt((String) payload.get("productId"));
            shoppingCart.setProductsOrdered(orderCustomerDataAccess.getProductsOrderedByOrderId(2));

            // Appeler la méthode de service
            Map<String, Object> result = shoppingCartService.removeOrderLine(shoppingCart, productId);
            response.putAll(result);
        } catch (NumberFormatException e) {
            response.put("success", false);
            response.put("error", "Invalid product ID format");
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addToCart(
            @RequestBody Map<String, Object> payload,
            @ModelAttribute("panier") ShoppingCart shoppingCart
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            Integer productId = Integer.parseInt((String) payload.get("productId"));
            Integer quantity = Integer.parseInt((String) payload.get("quantity"));

            shoppingCartService.addOrUpdateProduct(shoppingCart, productId, quantity);

            response.put("success", true);
            response.put("shoppingCart", shoppingCart);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
        }
        return response;
    }



}
