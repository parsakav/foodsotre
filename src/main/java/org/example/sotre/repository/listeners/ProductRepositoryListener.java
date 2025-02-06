package org.example.sotre.repository.listeners;


import org.example.sotre.model.Product;
import org.example.sotre.repository.CartRepository;
import org.example.sotre.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RepositoryEventHandler
public class ProductRepositoryListener {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @HandleBeforeSave
    @PreAuthorize("hasRole('ADMIN')")

    public void handleBeforeSave(Product product) {

    }
    @HandleBeforeDelete
    @PreAuthorize("hasRole('ADMIN')")


    public void handleBeforeDelete(Product product) {
        System.out.println(product.getName());
        product.setCartProducts(null);
        product.setCategory(null);
       cartRepository.detachProductFromCart(product.getProductID());

    }


    @HandleBeforeCreate
    @PreAuthorize("hasRole('ADMIN')")

    public void handleBeforeCreate(Product product) {

    }
}
