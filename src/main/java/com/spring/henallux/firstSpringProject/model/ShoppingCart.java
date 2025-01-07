package com.spring.henallux.firstSpringProject.model;

import java.util.HashMap;

public class ShoppingCart {

    private HashMap<Integer, OrderLine> productsOrdered;

    public ShoppingCart() {
        this.productsOrdered = new HashMap<>();

    }

    public ShoppingCart(HashMap<Integer, OrderLine> productsOrdered) {
        this.productsOrdered = productsOrdered;
    }

    public void addProduct(OrderLine orderLine) {
        int id = orderLine.getProduct().getProductID();
        if (productsOrdered.containsKey(id)) {
            productsOrdered.get(id).addQuantity(orderLine.getQuantity());
        } else {
            productsOrdered.put(id, orderLine);
        }
    }

    public HashMap<Integer, OrderLine> getProductsOrdered() {
        return productsOrdered;
    }

    public void setProductsOrdered(HashMap<Integer, OrderLine> productsOrderedByOrderId) {
        this.productsOrdered = productsOrderedByOrderId;
    }

    public String toString() {
        return "ShoppingCart{" +
                "productsOrdered=" + productsOrdered +
                '}';
    }
}
