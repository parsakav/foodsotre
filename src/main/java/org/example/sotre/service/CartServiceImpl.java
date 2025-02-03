package org.example.sotre.service;

import jakarta.transaction.Transactional;
import org.example.sotre.model.Cart;
import org.example.sotre.model.Product;
import org.example.sotre.model.User;
import org.example.sotre.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Override
    public Cart getCart(int cartId) {
        return cartRepository.getReferenceById(cartId);
    }

    @Override
    public List<Cart> getCarts(String userId) {
        return cartRepository.findCartByUserId(userId);
    }

    @Override
    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getLatestCart(String userId) {
        return cartRepository.getLatestCart(userId);
    }

    @Override
    public boolean removeProduct(String userId, int productId) {
        return cartRepository.deleteProduct(userId,productId)>0;
    }

    @Transactional
    @Override
    public Cart addProduct(String userId, int productId) {

        Cart c=getLatestCart(userId);
        User user = new User();
        user.setEmail(userId);
        Product p = new Product();
        p.setProductID(productId);
        if(c==null){
            c=new Cart();

            c.setUser(user);
            c.setProducts(List.of(p));

        }else {
            c.getProducts().add(p);

        }

        return cartRepository.save(c);
    }
}
