package com.spring.henallux.firstSpringProject.dataAccess.entity;



import javax.persistence.*;

@Entity
@Table(name = "order_line")
public class OrderLineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_line_id")
    private Integer orderLineID;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "sub_total", columnDefinition = "decimal")
    private double subTotal;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private OrderCustomerEntity orderID;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private ProductEntity product;

    public OrderLineEntity() {
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Integer getOrderLineID() {
        return orderLineID;
    }

    public OrderCustomerEntity getOrderID() {
        return orderID;
    }

    public void setOrderLineID(Integer orderLineID) {
        this.orderLineID = orderLineID;
    }

    public void setOrderID(OrderCustomerEntity orderID) {
        this.orderID = orderID;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public Integer getQuantity() {
        return quantity;
    }


    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public String toString() {
        return "OrderLineEntity{" +
                "orderLineID=" + orderLineID +
                ", quantity=" + quantity +
                ", subTotal=" + subTotal +
                ", orderID=" + orderID +
                ", product=" + product +
                '}';
    }

}
