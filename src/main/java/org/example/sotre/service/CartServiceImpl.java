package org.example.sotre.service;

import jakarta.transaction.Transactional;
import org.example.sotre.model.Cart;
import org.example.sotre.model.CartProduct;
import org.example.sotre.model.Product;
import org.example.sotre.model.User;
import org.example.sotre.repository.CartRepository;
import org.example.sotre.repository.ProductRepository;
import org.example.sotre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

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
    @Transactional

    @Override
    public boolean removeProduct(String userId, int productId) {
        int i = cartRepository.deleteProduct(userId, productId);
        if(i!=0) {
            Product p = null;
            p = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

            p.setStock(p.getStock() + 1);
        }
return i!=0;
    }

    @Transactional
    @Override
    public Cart addProduct(String userId, int productId) {

        Cart c=getLatestCart(userId);
        User user = null;
        user=userRepository.findUserByEmail(userId);
        Product p = null;
    p=  productRepository.findById(productId).orElseThrow(()->new RuntimeException("Product not found"));
List<CartProduct> cartProducts=new ArrayList<>();
       if(p.getStock()<=0){
           throw new RuntimeException("There is no product in stock");
       }
       CartProduct cardProduct=new CartProduct();
        cardProduct.setProduct(p);
        cardProduct.setQuantity(1);
        if(c==null){

                    c=new Cart();

            c.setUser(user);

            c.setCartProducts(List.of(cardProduct));
            cardProduct.setCart(c);

        }else {
c.getCartProducts().forEach(e->{
    System.out.println(e.getProduct().getProductID());
});            boolean found=false;
            for(CartProduct cc:c.getCartProducts()){
                if(cc.getProduct().getProductID().equals(p.getProductID())){
                    found=true;
                    System.out.println("HII");
                    cc.setQuantity(cc.getQuantity()+1);
                    cc.setCart(c);
                }
            }
            if(!found) {
                c.getCartProducts().add(cardProduct);
                cardProduct.setCart(c);
            }
        }
        p.setStock(p.getStock()-1);
        c.setTotalPrice(c.getTotalPrice()+p.getPrice());
        productRepository.saveAndFlush(p);

        return cartRepository.save(c);
    }
}
