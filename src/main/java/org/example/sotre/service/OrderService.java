package org.example.sotre.service;

import org.example.sotre.model.Order;
import org.example.sotre.model.OrderStatus;

import java.util.List;

public interface OrderService {

    List<Order> getOrders(String email);

    Order placeOrder(String email,int cartId);
    Order changeOrderStatus(int orderId, OrderStatus orderStatus);
}
