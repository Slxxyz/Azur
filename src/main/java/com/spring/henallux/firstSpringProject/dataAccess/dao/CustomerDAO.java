// src/main/java/com/spring/henallux/firstSpringProject/dataAccess/dao/CustomerDAO.java
package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.CustomerEntity;
import com.spring.henallux.firstSpringProject.dataAccess.entity.LocationEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.CustomerRepository;
import com.spring.henallux.firstSpringProject.dataAccess.repository.LocationRepository;
import com.spring.henallux.firstSpringProject.dataAccess.util.ProviderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerDAO implements CustomerDataAccess {
    private final CustomerRepository customerRepository;
    private final LocationRepository locationRepository;
    private final ProviderConverter providerConverter;

    @Autowired
    public CustomerDAO(CustomerRepository customerRepository, LocationRepository locationRepository, ProviderConverter providerConverter) {
        this.customerRepository = customerRepository;
        this.locationRepository = locationRepository;
        this.providerConverter = providerConverter;
    }


    @Override
    public CustomerEntity saveCustomer(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    public LocationEntity saveLocation(LocationEntity location) {
        // Sauvegarder l'adresse de livraison
        return locationRepository.save(location);
    }

    @Override
    public CustomerEntity findByMailAddress(String mailAddress) {
        return customerRepository.findByMailAddress(mailAddress);
    }


    @Override
    public boolean mailAddressExists(String mailAddress) {
        return customerRepository.existsByMailAddress(mailAddress);
    }

    @Override
    public boolean telephoneExists(String telephone) {
        return customerRepository.existsByTelNumber(telephone);
    }


    public CustomerEntity validateCustomer(String mailAddress, String password) {
        CustomerEntity customer = customerRepository.findByMailAddress(mailAddress);
        if (customer != null && customer.getUserPassword().equals(password)) {
            return customer;
        }
        return null;
    }

    public CustomerEntity findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    public boolean userNameExists(String username) {
        return customerRepository.existsByUsername(username);
    }
}