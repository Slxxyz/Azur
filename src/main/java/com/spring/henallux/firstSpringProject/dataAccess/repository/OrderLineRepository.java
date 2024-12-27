package com.spring.henallux.firstSpringProject.dataAccess.repository;

import com.spring.henallux.firstSpringProject.dataAccess.entity.OrderCustomerEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.henallux.firstSpringProject.dataAccess.entity.OrderLineEntity;

import java.util.List;


@Repository
public interface OrderLineRepository extends JpaRepository<OrderLineEntity, Integer> {

    // Requête pour trouver les lignes de commande d'une commande donnée
    List<OrderLineEntity> findByOrderID(OrderCustomerEntity orderID);

}