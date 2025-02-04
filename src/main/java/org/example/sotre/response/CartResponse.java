package org.example.sotre.response;

import org.example.sotre.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartResponse {
    private int id;
    private double totalPrice;
    private UserResponse userResponse;
    private List<ProductResponse> productResponseList;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ProductResponse> getProductResponseList() {
        return productResponseList;
    }

    public void setProductResponseList(List<ProductResponse> productResponseList) {
        this.productResponseList = productResponseList;
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }
    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }
    public static CartResponse from(Cart cart) {
        CartResponse response = new CartResponse();
        response.setId(cart.getCartID());
        response.setTotalPrice(cart.getTotalPrice());
        response.setProductResponseList(ProductResponse.from(cart.getCartProducts()));

        response.setUserResponse(new UserResponse(cart.getUser().getUserID(),cart.getUser().getEmail()));
  return response;
    }    public static List<CartResponse> from(List<Cart> cart) {

        List<CartResponse> cartResponseList = new ArrayList<>();
        for(Cart cartItem : cart) {
            cartResponseList.add(from(cartItem));
        }
   return cartResponseList;
    }


}
