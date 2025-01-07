package com.spring.henallux.firstSpringProject.configuration;

import com.spring.henallux.firstSpringProject.constants.Constants;
import com.spring.henallux.firstSpringProject.model.ShoppingCart;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class ShoppingCartInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(Constants.PANIER);

        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart(); // Créer un panier vide si aucun n'existe
            session.setAttribute(Constants.PANIER, shoppingCart);
        }

        return true; // Permet de continuer la requête
    }
}
