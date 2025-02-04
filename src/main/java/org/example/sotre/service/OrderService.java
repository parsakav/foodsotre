package org.example.sotre.service;

import org.example.sotre.model.Order;
import org.example.sotre.model.OrderStatus;
import org.example.sotre.model.Status;

import java.util.List;

public interface OrderService {

    List<Order> getUserOrders(String email);
    List<Order> getAllOrders();
    public Order saveOrder();
    Order placeOrder(Status status, int cartId);
    Order changeOrderStatus(int orderId, OrderStatus orderStatus);
    Order cancelOrder(int orderId);


}
