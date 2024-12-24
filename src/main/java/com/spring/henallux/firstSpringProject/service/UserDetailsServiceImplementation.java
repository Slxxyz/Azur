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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Exemple avec un utilisateur fictif
        if (username.equals("admin")) {
            return User.builder()
                    .username("admin")
                    .password(new BCryptPasswordEncoder().encode("password"))
                    .roles("ADMIN") // Rôles ou autorités
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }
}