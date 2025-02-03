package org.example.sotre.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statusID;

    @Column(unique = true, nullable = false,name = "status_name")
    @Enumerated(EnumType.STRING)

    private Status status;

    @OneToMany(mappedBy = "status",fetch = FetchType.EAGER)
private List<Order> orderList;

    public Integer getStatusID() {
        return statusID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}