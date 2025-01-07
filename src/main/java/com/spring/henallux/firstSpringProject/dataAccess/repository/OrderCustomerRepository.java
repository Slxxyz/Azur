package com.spring.henallux.firstSpringProject.dataAccess.repository;

import com.spring.henallux.firstSpringProject.dataAccess.entity.CustomerEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.henallux.firstSpringProject.dataAccess.entity.OrderCustomerEntity;

import java.util.List;

@Repository
public interface OrderCustomerRepository extends JpaRepository<OrderCustomerEntity, Integer> {

    List<OrderCustomerEntity> findByCustomerID(CustomerEntity customerID);


}
