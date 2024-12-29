package com.spring.henallux.firstSpringProject.dataAccess.util;

public class AddToCartRequest {

    private Integer productID;

    private Integer quantity;

    public AddToCartRequest() {
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return "Product ID: " + productID + ", Quantity: " + quantity;
    }
}
