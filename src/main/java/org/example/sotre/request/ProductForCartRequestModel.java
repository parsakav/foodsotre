package org.example.sotre.request;

import jakarta.validation.constraints.NotNull;

public class ProductForCartRequestModel {

    @NotNull
    private int productId;
    @NotNull
    private int cartId;
    @NotNull
    private int quantity;
    public ProductForCartRequestModel(int productId, int cartId, int quantity) {
        this.productId = productId;
        this.cartId = cartId;
        this.quantity = quantity;
    }

    @NotNull
    public int getProductId() {
        return productId;
    }

    @NotNull
    public int getCartId() {
        return cartId;
    }

    @NotNull
    public int getQuantity() {
        return quantity;
    }
}
