package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.CustomerEntity;
import com.spring.henallux.firstSpringProject.model.Customer;

import java.util.List;

public interface CustomerDataAccess {
    void saveCustomer(CustomerEntity customer);

    CustomerEntity findByMailAddress(String mailAddress);

    boolean mailAddressExists(String mailAddress);
    boolean telephoneExists(String telephone);
}