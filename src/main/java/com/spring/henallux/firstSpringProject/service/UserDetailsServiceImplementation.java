package com.spring.henallux.firstSpringProject.service;

import com.spring.henallux.firstSpringProject.dataAccess.dao.CustomerDAO;
import com.spring.henallux.firstSpringProject.dataAccess.entity.CustomerEntity;
import com.spring.henallux.firstSpringProject.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    private final CustomerDAO customerDAO;

    @Autowired
    public UserDetailsServiceImplementation(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        CustomerEntity customer = null;

        if (usernameOrEmail.contains("@")) {
            // Recherche par email
            customer = customerDAO.findByMailAddress(usernameOrEmail);
            if (customer == null) {
                throw new UsernameNotFoundException("Email not found: " + usernameOrEmail);
            }
        } else {
            // Recherche par username
            customer = customerDAO.findByUsername(usernameOrEmail);
            if (customer == null) {
                throw new UsernameNotFoundException("Username not found: " + usernameOrEmail);
            }
        }

        // Convertir en UserDetails
        return User.builder()
                .username(customer.getUsername()) // Utiliser le username comme identifiant principal
                .password(customer.getUserPassword()) // Utiliser le mot de passe encodé
                .roles("USER")
                .build();
    }
}