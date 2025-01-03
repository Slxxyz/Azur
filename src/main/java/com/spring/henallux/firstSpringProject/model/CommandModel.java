package com.spring.henallux.firstSpringProject.model;

import java.util.HashMap;

public class CommandModel {
    private Customer customer;
    private ShoppingCart shoppingCart;
    private PaymentModel paymentModel;
    private double totalAmount;
    private double discountAmount;
    private double totalAmountDiscount;

    public CommandModel(){

    }

    public CommandModel(Customer customer, ShoppingCart shoppingCart, PaymentModel paymentModel, double totalAmount, double discountAmount, double totalAmountDiscount){
        this.customer = customer;
        this.shoppingCart = shoppingCart;
        this.paymentModel = paymentModel;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.totalAmountDiscount = totalAmountDiscount;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public ShoppingCart getShoppingCart(){
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart){
        this.shoppingCart = shoppingCart;
    }

    public PaymentModel getPaymentModel(){
        return paymentModel;
    }

    public void setPaymentModel(PaymentModel paymentModel){
        this.paymentModel = paymentModel;
    }

    public double getTotalAmount(){
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount){
        this.totalAmount = totalAmount;
    }

    public double getDiscountAmount(){
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount){
        this.discountAmount = discountAmount;
    }

    public double getTotalAmountDiscount(){
        return totalAmountDiscount;
    }

    public void setTotalAmountDiscount(double totalAmountDiscount){
        this.totalAmountDiscount = totalAmountDiscount;
    }

    public String toString(){
        return "Customer: " + customer + ", ShoppingCart: " + shoppingCart + ", PaymentModel: " + paymentModel + ", TotalAmount: " + totalAmount + ", DiscountAmount: " + discountAmount + ", TotalAmountDiscount: " + totalAmountDiscount;
    }
}
