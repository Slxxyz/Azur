// src/main/java/com/spring/henallux/firstSpringProject/dataAccess/util/ProviderConverter.java
package com.spring.henallux.firstSpringProject.dataAccess.util;

import com.spring.henallux.firstSpringProject.dataAccess.entity.CustomerEntity;
import com.spring.henallux.firstSpringProject.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class ProviderConverter {

    public Customer customerEntityToCustomerModel(CustomerEntity customerEntity) {
        if (customerEntity == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setFirstName(customerEntity.getFirstName());
        customer.setLastName(customerEntity.getLastName());
        customer.setMailAddress(customerEntity.getMailAddress());
        customer.setUserPassword(customerEntity.getUserPassword());
        customer.setTelNumber(customerEntity.getTelNumber());
        return customer;
    }

    public CustomerEntity customerModelToCustomerEntity(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFirstName(customer.getFirstName());
        customerEntity.setLastName(customer.getLastName());
        customerEntity.setMailAddress(customer.getMailAddress());
        customerEntity.setUserPassword(customer.getUserPassword());
        customerEntity.setTelNumber(customer.getTelNumber());
        return customerEntity;
    }
}