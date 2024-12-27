package com.spring.henallux.firstSpringProject.model;

import java.util.Date;

public class OrderCustomer {

    private Integer orderID;
    private double totalAmount;
    private Date dateAndTime;
    private String methodOfPayment;
    private String state;
    private Customer customer;

    public OrderCustomer() {
    }

    public OrderCustomer(Integer orderID, double totalAmount, Date dateAndTime, String methodOfPayment, String state, Customer customer) {
        this.orderID = orderID;
        this.totalAmount = totalAmount;
        this.dateAndTime = dateAndTime;
        this.methodOfPayment = methodOfPayment;
        this.state = state;
        this.customer = customer;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public String getMethodOfPayment() {
        return methodOfPayment;
    }

    public String getState() {
        return state;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = Math.round(totalAmount*100.0)/100.0;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setMethodOfPayment(String methodOfPayment) {
        this.methodOfPayment = methodOfPayment;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String toString() {
        return "OrderCustomer{" +
                "orderID=" + orderID +
                ", totalAmount=" + totalAmount +
                ", dateAndTime=" + dateAndTime +
                ", methodOfPayment='" + methodOfPayment + '\'' +
                ", state='" + state + '\'' +
                ", customer=" + customer +
                '}';
    }
}
