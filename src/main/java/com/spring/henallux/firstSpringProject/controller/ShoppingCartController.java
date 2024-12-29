package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.OrderCustomerDataAccess;
import com.spring.henallux.firstSpringProject.dataAccess.dao.ProductDataAccess;
import com.spring.henallux.firstSpringProject.dataAccess.util.AddToCartRequest;
import com.spring.henallux.firstSpringProject.model.Customer;
import com.spring.henallux.firstSpringProject.model.OrderLine;
import com.spring.henallux.firstSpringProject.model.ShoppingCart;
import com.spring.henallux.firstSpringProject.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public ShoppingCart initializeCart(HttpSession session) {
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("panier");
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart(); // Crée un panier vide si aucun panier en session
            session.setAttribute("panier", shoppingCart);
        }
        return shoppingCart;
    }


    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, OrderCustomerDataAccess orderCustomerDataAccess, ProductDataAccess productDataAccess) {
        this.shoppingCartService = shoppingCartService;
        this.orderCustomerDataAccess = orderCustomerDataAccess;
        this.productDataAccess = productDataAccess;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String shoppingCart(Model model,
                               @ModelAttribute(value = "panier") ShoppingCart shoppingCart) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
        // Vérifie si l'utilisateur est connecté
            String username = authentication.getName();
            HashMap<Integer, OrderLine> productsOrdered = shoppingCartService.loadShoppingCartForUser(username);
            shoppingCart.setProductsOrdered(productsOrdered); // Charge les produits dans le panier
        }else {
            // Vérifie si l'utilisateur n'est pas connecté
            System.out.println("User not authenticated");
            System.out.println(shoppingCart);

            HashMap<Integer, OrderLine> productsOrdered = shoppingCart.getProductsOrdered();
            if (productsOrdered != null && !productsOrdered.isEmpty()) {
                // Si le panier n'est pas vide, charge les produits dans le panier temporaire
                shoppingCart.setProductsOrdered(productsOrdered);
            }
        }
        // Ajoute les attributs au modèle
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
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Integer productId = Integer.parseInt((String) payload.get("productId"));
            Integer quantity = Integer.parseInt((String) payload.get("quantity"));
            if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
                shoppingCartService.updateProductQuantity(shoppingCart, productId, quantity);
                OrderLine orderLine = shoppingCart.getProductsOrdered().get(productId);
                response.put("success", true);
                response.put("newSubTotal", orderLine.getSubTotal());
                response.put("totalAmount", orderLine.getOrder().getTotalAmount());
            } else {
                shoppingCartService.updateProductQuantityTemporary(shoppingCart, productId, quantity);
                response.put("success", true);
                response.put("newSubTotal", shoppingCart.getProductsOrdered().get(productId).getSubTotal());
                response.put("totalAmount", shoppingCartService.calculateTotalAmount(shoppingCart));
            }
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
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            Integer productId = Integer.parseInt((String) payload.get("productId"));
            // Appeler la méthode de service
            Map<String, Object> result = shoppingCartService.removeOrderLine(shoppingCart, productId);
            response.putAll(result);
            } else {
                Integer productId = Integer.parseInt((String) payload.get("productId"));
                shoppingCart.getProductsOrdered().remove(productId);
                shoppingCartService.removeOrderLineTemporary(shoppingCart, productId);
                response.put("success", true);
                response.put("totalAmount", shoppingCartService.calculateTotalAmount(shoppingCart));
            }
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
            @RequestBody AddToCartRequest request,
            @ModelAttribute("panier") ShoppingCart shoppingCart
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
                // Ajouter pour un utilisateur connecté
                Customer customer = shoppingCartService.getCustomerByUsername(authentication.getName());
                System.out.println(customer);
                shoppingCartService.addProduct(shoppingCart, request.getProductID(), request.getQuantity(), customer);
            } else {
                // Ajouter au panier temporaire (stocké en session)
                shoppingCartService.addProduct(shoppingCart, request.getProductID(), request.getQuantity(), null);
                response.put("shoppingCart", shoppingCart);
            }
            response.put("success", true);
        } catch (Exception e) {
            logger.error("Erreur lors de l'ajout au panier", e);
            response.put("success", false);
            response.put("error", e.getMessage());
        }
        return response;
    }

    @RequestMapping(value="/sync", method = RequestMethod.POST)
    public String syncCart(@RequestBody HashMap<Integer, Integer> cart,
                                      @ModelAttribute("panier") ShoppingCart shoppingCart) {
        // "cart" est une map où les clés sont les IDs de produit et les valeurs sont les quantités
        System.out.println("Synchronisation du panier");
        try {
            System.out.println(cart);
            HashMap<Integer, OrderLine> productsOrdered = shoppingCart.getProductsOrdered();
            cart.forEach((productId, quantity) -> {
                OrderLine orderLine = shoppingCartService.setOrderLineTemporary(shoppingCart, productId, quantity);;
                productsOrdered.put(productId, orderLine);
            });


        } catch (Exception e) {
            System.out.println("Erreur lors de la synchronisation du panier");
            e.printStackTrace();
        }
        return "redirect:/panier";
    }


}
