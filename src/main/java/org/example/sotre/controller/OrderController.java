package org.example.sotre.controller;

import org.example.sotre.model.*;
import org.example.sotre.response.OrderResponse;
import org.example.sotre.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")

public class OrderController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<List<OrderResponse>> getUserOrders() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(OrderResponse.from(orderService.getUserOrders(email)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(OrderResponse.from(orderService.getAllOrders()));
    }
/*    @PreAuthorize("hasRole('USER')")

    @PostMapping("/place")
    public ResponseEntity<OrderResponse> placeOrder(@RequestParam Status status, @RequestParam int cartId) {
        return ResponseEntity.ok(OrderResponse.from(orderService.placeOrder(status, cartId)));
    }*/
    @PreAuthorize("hasRole('USER')")

    @PostMapping("/save")
    public ResponseEntity<OrderResponse> saveOrder() {
        return ResponseEntity.ok(OrderResponse.from(orderService.saveOrder()));
    }

    @PreAuthorize("hasRole('ADMIN')")

    @PutMapping("/change-status/{orderId}")
    public ResponseEntity<OrderResponse> changeOrderStatus(@PathVariable int orderId, @RequestBody OrderStatus orderStatus) {
        return ResponseEntity.ok(OrderResponse.from(orderService.changeOrderStatus(orderId, orderStatus)));
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")

    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable int orderId) {
        return ResponseEntity.ok(OrderResponse.from(orderService.cancelOrder(orderId)));
    }
}
