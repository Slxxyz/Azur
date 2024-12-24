package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.CustomerEntity;

import java.util.List;

public interface CustomerDataAccess {
    CustomerEntity saveCustomer(CustomerEntity customer); // Sauvegarde ou met à jour un client

    CustomerEntity findByMailAddress(String mailAddress);

    boolean mailAddressExists(String mailAddress); // Vérifie si un email existe déjà
    boolean telephoneExists(String telephone); // Vérifie si un numéro de téléphone existe déjà
}