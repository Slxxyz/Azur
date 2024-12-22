package com.spring.henallux.firstSpringProject.dataAccess.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orderCustomer")
public class OrderCustomerEntity {

    @Id
    @Column(name = "orderID")
    private Integer orderID;

    @Id
    @Column(name = "totalAmount")
    private double totalAmount;

    @Id
    @Column(name = "dateAndTime")
    private LocalDateTime dateAndTime;

    @Id
    @Column(name = "methodOfPayment")
    private String methodOfPayment;

    @Id
    @Column(name = "state")
    private String state;

    @Id
    @Column(name = "customerID")
    private Integer customerID;
}
