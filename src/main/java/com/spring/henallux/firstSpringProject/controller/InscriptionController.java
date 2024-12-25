package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.constants.Constants;
import com.spring.henallux.firstSpringProject.dataAccess.dao.CustomerDAO;
import com.spring.henallux.firstSpringProject.dataAccess.entity.CustomerEntity;
import com.spring.henallux.firstSpringProject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/inscription")
public class InscriptionController {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("title", "Inscription");
        model.addAttribute("showHeader", true);
        model.addAttribute("showFooter", false);
        model.addAttribute("customer", new CustomerEntity());
        return "integrated:inscription";
    }

    @RequestMapping(value = "/sendInscription", method = RequestMethod.POST)
    public String getFormData(
            Model model,
            @Valid @ModelAttribute(value = Constants.CURRENT_USER) CustomerEntity customer,
            final BindingResult errors) {

        boolean hasErrors = false;

        // Vérification des erreurs d'email, de téléphone et d'username
        if (customerDAO.mailAddressExists(customer.getMailAddress())) {
            model.addAttribute("errorEmail", "Email already exists");
            hasErrors = true;
        }
        if (customerDAO.telephoneExists(customer.getTelNumber())) {
            model.addAttribute("errorTelephone", "Telephone number already exists");
            hasErrors = true;
        }
        if (customerDAO.userNameExists(customer.getUsername())) {
            model.addAttribute("errorUsername", "Username already exists");
            hasErrors = true;
        }

        // Si des erreurs sont présentes, renvoyer au formulaire d'inscription
        if (errors.hasErrors() || hasErrors) {
            model.addAttribute("customer", customer);
            model.addAttribute("showHeader", true);
            model.addAttribute("showFooter", false);
            return "integrated:inscription";
        }

        // Encodage du mot de passe avant la sauvegarde
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(customer.getUserPassword());
        customer.setUserPassword(encodedPassword);

        // Sauvegarder l'adresse (LocationEntity) si elle existe
        if (customer.getLocation() != null && customer.getLocation().getLocationId() == null) {
            customerDAO.saveLocation(customer.getLocation());
        }

        // Sauvegarder le client (CustomerEntity)
        customerDAO.saveCustomer(customer);

        UserDetails userDetails = User.builder()
                .username(customer.getUsername())
                .password(customer.getUserPassword()) // Utilisez le mot de passe encodé ici
                .roles("USER")
                .build();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        System.out.println("Customer logged in: " + customer.getMailAddress());
        return "redirect:/azur";
    }
}