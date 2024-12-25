package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.CustomerEntity;
import com.spring.henallux.firstSpringProject.model.Customer;

import java.util.List;

public interface CustomerDataAccess {
    void saveCustomer(CustomerEntity customer); // Sauvegarde ou met à jour un client

    CustomerEntity findByMailAddress(String mailAddress);

    boolean mailAddressExists(String mailAddress); // Vérifie si un email existe déjà
    boolean telephoneExists(String telephone); // Vérifie si un numéro de téléphone existe déjà
}