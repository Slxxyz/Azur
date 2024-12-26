package com.spring.henallux.firstSpringProject.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class OrderLine {

    private int orderLineID;
    @NotNull
    @PositiveOrZero
    private int quantity;
    private double subTotal;
    private Product product;
    private OrderCustomer order;

    public OrderLine() {
    }

    public OrderLine(int quantity, double subTotal, Product product, OrderCustomer order) {
        setQuantity(quantity);
        setSubTotal(subTotal);
        setProduct(product);
        setOrder(order);
    }

    public OrderLine(int quantity, double subTotal, Product product) {
        setQuantity(quantity);
        setSubTotal(subTotal);
        setProduct(product);
    }

    public int getOrderLineID() {
        return orderLineID;
    }

    public void setOrderLineID(int orderLineID) {
        this.orderLineID = orderLineID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderCustomer getOrder() {
        return order;
    }

    public void setOrder(OrderCustomer order) {
        this.order = order;
    }

    public String toString() {
        return "OrderLine{" +
                "orderLineID=" + orderLineID +
                ", quantity=" + quantity +
                ", subTotal=" + subTotal +
                ", product=" + (product != null ? product.getProductID() : "null") +
                ", order=" + (order != null ? order.getOrderID() : "null") +
                '}';
    }
}
