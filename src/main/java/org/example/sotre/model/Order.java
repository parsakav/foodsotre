package org.example.sotre.model;

import jakarta.persistence.*;
import org.example.sotre.response.OrderResponse;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    private Date orderDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private OrderStatus status;

    @OneToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "cartid",referencedColumnName = "cartid")

    private Cart cart;

    public Order(){
        status=new OrderStatus();
        status.setStatus(Status.OPEN);
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @PrePersist
    private void prePersist(){

        orderDate=new Date();
    }



}