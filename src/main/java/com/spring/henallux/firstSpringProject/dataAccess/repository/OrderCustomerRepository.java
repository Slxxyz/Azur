package com.spring.henallux.firstSpringProject.dataAccess.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.henallux.firstSpringProject.dataAccess.entity.OrderCustomerEntity;

@Repository
public interface OrderCustomerRepository extends JpaRepository<OrderCustomerEntity, Integer> {


}
