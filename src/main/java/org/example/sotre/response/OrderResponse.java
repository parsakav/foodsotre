package org.example.sotre.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.sotre.model.Order;
import org.example.sotre.model.Status;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private int orderId;
    private Status orderStatus;
    private CartResponse cartResponse;
    public static OrderResponse from(Order o){
        OrderResponse r=new OrderResponse();
        r.setOrderId(o.getOrderID());
        r.setOrderStatus(o.getStatus().getStatus());
        r.setCartResponse(CartResponse.from(o.getCart()));
        return r;
    }   public static List<OrderResponse> from(List<Order> o){
       List<OrderResponse> r=new ArrayList<>();
       for (Order o1:o){
           r.add(OrderResponse.from(o1));
       }
       return r;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Status getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Status orderStatus) {
        this.orderStatus = orderStatus;
    }

    public CartResponse getCartResponse() {
        return cartResponse;
    }

    public void setCartResponse(CartResponse cartResponse) {
        this.cartResponse = cartResponse;
    }
}

