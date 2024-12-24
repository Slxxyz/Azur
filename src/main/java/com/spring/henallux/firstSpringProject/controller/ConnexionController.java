package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.CustomerDAO;
import com.spring.henallux.firstSpringProject.dataAccess.entity.CustomerEntity;
import com.spring.henallux.firstSpringProject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/connexion")
public class ConnexionController {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method= RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("title", "Connexion");
        model.addAttribute("showHeader", true);
        model.addAttribute("showFooter", false);
        model.addAttribute("customer", new CustomerEntity());
        return "integrated:connexion";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("mailAddress") String mailAddress,
                        @RequestParam("userPassword") String userPassword,
                        Model model) {
        CustomerEntity customer = customerDAO.validateCustomer(mailAddress, userPassword);

        if (customer != null) {
            // Create UserDetails object
            UserDetails userDetails = User.builder()
                    .username(customer.getUsername())
                    .password(customer.getUserPassword()) // Use encoded password
                    .roles("USER") // Define roles for the user
                    .build();

            // Create Authentication object
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // Set the authentication in the Security Context
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            // Log successful login
            System.out.println("Customer logged in: " + customer.getMailAddress());

            return "redirect:/azur";
        } else {
            model.addAttribute("loginError", "Invalid email or password");
            model.addAttribute("title", "Connexion");
            model.addAttribute("showHeader", true);
            model.addAttribute("showFooter", false);
            model.addAttribute("customer", new CustomerEntity());
            return "integrated:connexion";
        }
    }
}