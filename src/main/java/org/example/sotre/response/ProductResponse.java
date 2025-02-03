package org.example.sotre.response;

import org.example.sotre.model.CartProduct;
import org.example.sotre.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductResponse {

    private int productId;
    private String productName;

    private double price;
    private int quantity;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static ProductResponse from(Product product) {
        ProductResponse response = new ProductResponse();
        response.setProductId(product.getProductID());
        response.setProductName(product.getName());
        response.setPrice(product.getPrice());

        return response;
    }
    public static ProductResponse from(CartProduct cProduct) {
      Product product=cProduct.getProduct();
        ProductResponse from = from(product);
        from.setQuantity(cProduct.getQuantity());
        return from;
    }
    public static List<ProductResponse> fromProducts(List<Product> product) {
       List<ProductResponse> responses = new ArrayList<ProductResponse>();
       for (Product p : product) {
           responses.add(from(p));
       }
        return responses;
    }
    public static List<ProductResponse> from(List<CartProduct> product) {
       List<ProductResponse> responses = new ArrayList<ProductResponse>();
       for (CartProduct p : product) {
           responses.add(from(p));
       }
        return responses;
    }

}
