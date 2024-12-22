package com.spring.henallux.firstSpringProject.dataAccess.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_line")
public class OrderLineEntity {

    @Id
    @Column(name = "order_line_id")
    private Integer orderLineID;


    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "sub_total")
    private Integer subTotal;

    @ManyToOne
    @Column(name = "order_id")
    private OrderCustomerEntity orderID;

    @ManyToOne
    @Column(name = "product_id")
    private  productID;

    public OrderLineEntity() {
    }

    public int getProductID() {
        return productID;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public int getQuantity() {
        return quantity;
    }

    public Integer getProductId() {
        return productID;
    }
}
