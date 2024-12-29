package com.spring.henallux.firstSpringProject.dataAccess.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_customer")
public class OrderCustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderID;

    @Column(name = "total_amount", columnDefinition = "decimal")
    private double totalAmount;

    @Column(name = "date_and_time")
    private Date dateAndTime;

    @Column(name = "method_of_payment")
    private String methodOfPayment;

    @Column(name = "state")
    private String state;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "username")
    private CustomerEntity customerID;

    public OrderCustomerEntity() {
    }

    public OrderCustomerEntity(Integer orderID, double totalAmount, Date dateAndTime, String methodOfPayment, String state, CustomerEntity customerID) {
        this.orderID = orderID;
        this.totalAmount = totalAmount;
        this.dateAndTime = dateAndTime;
        this.methodOfPayment = methodOfPayment;
        this.state = state;
        this.customerID = customerID;
    }

    @PrePersist
    public void prePersist() {
        if (this.dateAndTime == null) {
            this.dateAndTime = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        }
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

    public CustomerEntity getCustomerID() {
        return customerID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
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

    public void setCustomerID(CustomerEntity customerID) {
        this.customerID = customerID;
    }

    public String toString() {
        return "OrderCustomerEntity{" +
                "orderID=" + orderID +
                ", totalAmount=" + totalAmount +
                ", dateAndTime=" + dateAndTime +
                ", methodOfPayment='" + methodOfPayment + '\'' +
                ", state='" + state + '\'' +
                ", customerID=" + customerID +
                '}';
    }
}
