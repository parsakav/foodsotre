package org.example.sotre.service;

import org.example.sotre.model.*;
import org.example.sotre.repository.CartRepository;
import org.example.sotre.repository.OrderRepository;
import org.example.sotre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Override
    public List<Order> getUserOrders(String email) {
        return orderRepository.findUserOrders(email);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order placeOrder(Status status, int cartId) {
        Cart c=cartRepository.findById(cartId).orElseThrow();
        Order order=new Order();
order.setUser(c.getUser());

order.setCart(c);
OrderStatus status2=new OrderStatus();
status2.setStatus(status);
order.setStatus(status2);
Order o =orderRepository.save(order);
        return o;
    }
    @Override
    public Order saveOrder() {
        String email= (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Cart latestCart = cartRepository.getLatestCart(email);
        User userByEmail = userRepository.findUserByEmail(email);
        Order order=new Order();
        order.setUser(userByEmail);

        order.setCart(latestCart);
        OrderStatus status=new OrderStatus();
        status.setStatus(Status.OPEN);
        order.setStatus(status);
        Order save =orderRepository.save(order);
        return save;
    }

    @Override
    public Order changeOrderStatus(int orderId, OrderStatus orderStatus) {
        Order order=orderRepository.findById(orderId).orElseThrow();


        order.setStatus(orderStatus);
        Order o =orderRepository.save(order);
        return o;
    }

    @Override
    public Order cancelOrder(int orderId) {
        OrderStatus status=new OrderStatus();
        status.setStatus(Status.CANCELD);
        return changeOrderStatus(orderId,status);
    }
}
