package com.spring.henallux.firstSpringProject.model;

public class PaymentModel {

    private Double amount;
    private String currency;

    // Getters et setters
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public PaymentModel() {
    }

    public PaymentModel(Double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public String toString() {
        return "Amount: " + amount + ", Currency: " + currency;
    }


}
