package org.example.sotre.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.example.sotre.model.Cart;
import org.example.sotre.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@Validated
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")

    @GetMapping("/{email}/latest")
    public ResponseEntity<Cart> getLatestCart(@PathVariable @NotBlank String email) {
        String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user+" PARSA");
        Cart cart = cartService.getLatestCart(email);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cart);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")

    @GetMapping("/{email}/carts")
    public ResponseEntity<List<Cart>> getAllUserCarts(@PathVariable @NotBlank String email) {
        List<Cart> cart = cartService.getCarts(email);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> cart = cartService.getCarts();
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")

    @PostMapping("/{email}/add/{productId}")
    public ResponseEntity<Cart> addProductToCart(
            @PathVariable @NotBlank String email,
            @PathVariable @Min(1) int productId) {

        Cart updatedCart = cartService.addProduct(email, productId);
        return ResponseEntity.ok(updatedCart);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")

    @DeleteMapping("/{email}/remove/{productId}")
    public ResponseEntity<String> removeProductFromCart(
            @PathVariable @NotBlank String email,
            @PathVariable @Min(1) int productId) {

        boolean removed = cartService.removeProduct(email, productId);
        if (removed) {
            return ResponseEntity.ok("Product removed successfully.");
        } else {
            return ResponseEntity.badRequest().body("Product not found in cart.");
        }
    }
}
