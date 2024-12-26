package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.OrderCustomerEntity;
import com.spring.henallux.firstSpringProject.dataAccess.entity.OrderLineEntity;
import com.spring.henallux.firstSpringProject.dataAccess.entity.ProductEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.OrderCustomerRepository;
import com.spring.henallux.firstSpringProject.dataAccess.repository.OrderLineRepository;
import com.spring.henallux.firstSpringProject.model.OrderLine;
import com.spring.henallux.firstSpringProject.dataAccess.util.ProviderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class OrderCustomerDAO implements OrderCustomerDataAccess {

    private final ProviderConverter providerConverter;
    private OrderLineRepository orderLineRepository;
    private OrderCustomerRepository orderCustomerRepository;

    @Autowired
    public OrderCustomerDAO(OrderLineRepository orderLineRepository, OrderCustomerRepository orderCustomerRepository, ProviderConverter providerConverter) {
        this.orderLineRepository = orderLineRepository;
        this.orderCustomerRepository = orderCustomerRepository;
        this.providerConverter = providerConverter;
    }


    @Override
    public List<OrderLineEntity> getOrderLinesByCustomerId(Integer customerId) {
        return List.of();
    }


    @Override
    public HashMap<Integer, OrderLine> getProductsOrderedByOrderId(Integer orderId) {
        OrderCustomerEntity orderCustomerEntity = orderCustomerRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID"));
        List<OrderLineEntity> orderLines = orderLineRepository.findByOrder(orderCustomerEntity);
        HashMap<Integer, OrderLine> productsOrdered = new HashMap<>();
        for (OrderLineEntity orderLineEntity : orderLines) {
            OrderLine orderLine = new OrderLine();
            orderLine.setOrderLineID(orderLineEntity.getOrderLineID());
            orderLine.setSubTotal(orderLineEntity.getSubTotal());
            orderLine.setQuantity(orderLineEntity.getQuantity());
            orderLine.setProduct(providerConverter.productEntityToProductModel(orderLineEntity.getProduct()));
            orderLine.setOrder(providerConverter.orderCustomerEntityToOrderCustomerModel(orderLineEntity.getOrder()));
            System.out.println(orderLine);
            ProductEntity product = orderLineEntity.getProduct(); // Assuming getProduct() method exists in OrderLineEntity
            if (product != null) {
                orderLineEntity.setProduct(product);
                productsOrdered.put(product.getProductID(), orderLine);
            }
        }
        return productsOrdered;
    }

    @Override
    public OrderCustomerEntity save(OrderCustomerEntity orderCustomerEntity) {
        return orderCustomerRepository.save(orderCustomerEntity);
    }



}
