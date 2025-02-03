package org.example.sotre.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userID")
    private User user;

    private double totalPrice;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartProduct> cartProducts;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "cart")
    private Order order;

    public void addProduct(Product product, int quantity) {
        for (CartProduct cp : cartProducts) {
            if (cp.getProduct().getProductID().equals(product.getProductID())) {
                cp.setQuantity(cp.getQuantity() + quantity);
                return;
            }
        }
        CartProduct newCartProduct = new CartProduct();
        newCartProduct.setCart(this);
        newCartProduct.setProduct(product);
        newCartProduct.setQuantity(quantity);
        cartProducts.add(newCartProduct);
    }

    // Getters and Setters


    public Integer getCartID() {
        return cartID;
    }

    public void setCartID(Integer cartID) {
        this.cartID = cartID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
