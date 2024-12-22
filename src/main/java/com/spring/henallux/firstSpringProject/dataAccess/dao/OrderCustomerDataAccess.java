package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.OrderLineEntity;

import java.util.HashMap;
import java.util.List;

public interface OrderCustomerDataAccess {
    List<OrderLineEntity> getOrderLinesByCustomerId(Integer customerId);

    HashMap<Integer, Integer> getProductsOrderedByOrderId(Integer orderId);
}
