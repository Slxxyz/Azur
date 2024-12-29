package com.spring.henallux.firstSpringProject.dataAccess.util;

import com.spring.henallux.firstSpringProject.dataAccess.entity.*;
import com.spring.henallux.firstSpringProject.model.*;
import org.springframework.stereotype.Component;
import org.dozer.DozerBeanMapper;

import java.time.ZoneId;

@Component
public class ProviderConverter {

    private DozerBeanMapper mapper = new DozerBeanMapper();

    public CustomerEntity customerModelToCustomerEntity(Customer customer) {
        return mapper.map(customer, CustomerEntity.class);
    }

    public Customer customerEntityToCustomerModel(CustomerEntity customerEntity) {
        return mapper.map(customerEntity, Customer.class);
    }

    public OrderCustomerEntity orderCustomerModelToOrderCustomerEntity(OrderCustomer orderCustomer) {
        OrderCustomerEntity orderCustomerEntity = mapper.map(orderCustomer, OrderCustomerEntity.class);
        if (orderCustomer.getOrderID() != 0) {
            orderCustomerEntity.setOrderID(orderCustomer.getOrderID());
        }
        orderCustomerEntity.setTotalAmount(orderCustomer.getTotalAmount());
        orderCustomerEntity.setDateAndTime(orderCustomer.getDateAndTime());
        orderCustomerEntity.setMethodOfPayment(orderCustomer.getMethodOfPayment());
        orderCustomerEntity.setState(orderCustomer.getState());
        orderCustomerEntity.setCustomerID(customerModelToCustomerEntity(orderCustomer.getCustomer()));
        return orderCustomerEntity;
    }

    public OrderCustomer orderCustomerEntityToOrderCustomerModel(OrderCustomerEntity orderCustomerEntity) {
        OrderCustomer orderCustomer = mapper.map(orderCustomerEntity, OrderCustomer.class);
        orderCustomer.setOrderID(orderCustomerEntity.getOrderID());
        orderCustomer.setTotalAmount(orderCustomerEntity.getTotalAmount());
        orderCustomer.setDateAndTime(orderCustomerEntity.getDateAndTime());
        orderCustomer.setMethodOfPayment(orderCustomerEntity.getMethodOfPayment());
        orderCustomer.setState(orderCustomerEntity.getState());
        orderCustomer.setCustomer(customerEntityToCustomerModel(orderCustomerEntity.getCustomerID()));
        return orderCustomer;
    }

    public Product productEntityToProductModel(ProductEntity productEntity) {
        Product product = mapper.map(productEntity, Product.class);

        product.setProductID(productEntity.getProductID());
        product.setLabelProduct(productEntity.getLabelProduct());
        product.setImagePath(productEntity.getImagePath());
        product.setUnitPriceExcludingTax(productEntity.getUnitPriceExcludingTax());
        product.setVATRate(productEntity.getVATRate());
        product.setQuantityInStock(productEntity.getQuantityInStock());
        product.setDescriptionFR(productEntity.getDescriptionFR());
        product.setDescriptionEN(productEntity.getDescriptionEN());

        return product;
    }

    public ProductEntity productModelToProductEntity(Product product) {
        ProductEntity productEntity = mapper.map(product, ProductEntity.class);
        if (product.getProductID() != 0) {
            productEntity.setProductID(product.getProductID());
        }
        productEntity.setLabelProduct(product.getLabelProduct());
        productEntity.setImagePath(product.getImagePath());
        productEntity.setUnitPriceExcludingTax(product.getUnitPriceExcludingTax());
        productEntity.setVATRate(product.getVATRate());
        productEntity.setQuantityInStock(product.getQuantityInStock());
        productEntity.setDescriptionFR(product.getDescriptionFR());
        productEntity.setDescriptionEN(product.getDescriptionEN());
        return productEntity;
    }

    public OrderLineEntity orderLineModelToOrderLineEntity(OrderLine orderLine) {
        OrderLineEntity orderLineEntity = mapper.map(orderLine, OrderLineEntity.class);
        if (orderLine.getOrderLineID() != 0) {
            orderLineEntity.setOrderLineID(orderLine.getOrderLineID());
        }
        orderLineEntity.setSubTotal(orderLine.getSubTotal());
        orderLineEntity.setQuantity(orderLine.getQuantity());
        orderLineEntity.setProduct(productModelToProductEntity(orderLine.getProduct()));
        orderLineEntity.setOrderID(orderCustomerModelToOrderCustomerEntity(orderLine.getOrder()));
        System.out.println(orderLineEntity);
        return orderLineEntity;
    }

    public OrderLine orderLineEntityToOrderLineModel(OrderLineEntity orderLineEntity) {
        OrderLine orderLine = mapper.map(orderLineEntity, OrderLine.class);
        orderLine.setOrderLineID(orderLineEntity.getOrderLineID());
        orderLine.setSubTotal(orderLineEntity.getSubTotal());
        orderLine.setQuantity(orderLineEntity.getQuantity());
        orderLine.setProduct(productEntityToProductModel(orderLineEntity.getProduct()));
        orderLine.setOrder(orderCustomerEntityToOrderCustomerModel(orderLineEntity.getOrderID()));
        return orderLine;
    }


}