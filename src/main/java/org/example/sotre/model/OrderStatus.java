package org.example.sotre.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statusID;

    private String status; // Open/Shipped/Canceled

    @ManyToMany(mappedBy = "status",fetch = FetchType.EAGER)
private List<Order> orderList;

    public Integer getStatusID() {
        return statusID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}