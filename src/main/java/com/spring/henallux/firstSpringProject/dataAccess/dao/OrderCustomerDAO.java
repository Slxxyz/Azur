package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.OrderLineEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class OrderCustomerDAO implements OrderCustomerDataAccess {
    private OrderLineRepository orderLineRepository;

    @Autowired
    public OrderCustomerDAO(OrderLineRepository orderLineRepository) {
        this.orderLineRepository = orderLineRepository;
    }

    @Override
    public List<OrderLineEntity> getOrderLinesByCustomerId(Integer customerId) {
        return List.of();
    }

    @Override
    public HashMap<Integer, Integer> getProductsOrderedByOrderId(Integer orderId) {
        HashMap<Integer, Integer> productsOrdered = new HashMap<>();
        for (OrderLineEntity orderLineEntity : orderLineRepository.findByOrderId(orderId)) {
            productsOrdered.put(orderLineEntity.getProductId(), orderLineEntity.getQuantity());
        }
        return productsOrdered;
    }
}
