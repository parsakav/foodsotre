package org.example.sotre.service;

import org.example.sotre.model.Cart;

import java.util.List;

public interface CartService {


    Cart getCart(int cartId);
    List<Cart> getCarts(String userId);
    List<Cart> getCarts();
    Cart getLatestCart(String userId);
    boolean removeProduct(String userId,int productId);
    Cart addProduct(String userId,int productId);
}
