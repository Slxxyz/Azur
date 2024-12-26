package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.OrderCustomerEntity;
import com.spring.henallux.firstSpringProject.dataAccess.entity.OrderLineEntity;
import com.spring.henallux.firstSpringProject.model.OrderLine;

import java.util.HashMap;
import java.util.List;

public interface OrderCustomerDataAccess {
    List<OrderLineEntity> getOrderLinesByCustomerId(Integer customerId);

    HashMap<Integer, OrderLine> getProductsOrderedByOrderId(Integer orderId);

    OrderCustomerEntity save(OrderCustomerEntity orderCustomerEntity);
}
