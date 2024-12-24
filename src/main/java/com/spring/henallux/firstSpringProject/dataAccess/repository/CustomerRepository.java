package com.spring.henallux.firstSpringProject.dataAccess.repository;

import com.spring.henallux.firstSpringProject.dataAccess.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    CustomerEntity findByUsername(String username);
    CustomerEntity findByMailAddress(String mailAddress);
    boolean existsByMailAddress(String mailAddress);
    boolean existsByTelNumber(String telephone);

    boolean existsByUsername(String username);
}