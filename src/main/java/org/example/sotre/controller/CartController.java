package org.example.sotre.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.example.sotre.controller.exceptions.NoStockException;
import org.example.sotre.model.Cart;
import org.example.sotre.response.CartResponse;
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

    @GetMapping("/latest")
    public ResponseEntity<CartResponse> getLatestCart() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(email == null) {
            throw new RuntimeException("Invalid User");
        }
        Cart cart = cartService.getLatestCart(email);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(CartResponse.from(cart));
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")

    @GetMapping("/carts")
    public ResponseEntity<List<CartResponse>> getAllUserCarts() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(email == null) {
            throw new RuntimeException("Invalid User");
        }
        List<Cart> cart = cartService.getCarts(email);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CartResponse.from(cart));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<CartResponse>> getAllCarts() {
        List<Cart> cart = cartService.getCarts();
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CartResponse.from(cart));
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")

    @PostMapping("/add/{productId}")
    public ResponseEntity<CartResponse> addProductToCart(
            @PathVariable @Min(1) int productId) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(email == null) {
            throw new RuntimeException("Invalid User");
        }
        Cart updatedCart = null;

     updatedCart = cartService.addProduct(email, productId);

        return ResponseEntity.ok(CartResponse.from(updatedCart));
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<String> removeProductFromCart(
            @PathVariable @Min(1) int productId) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(email == null) {
            throw new RuntimeException("Invalid User");
        }
        boolean removed = cartService.removeProduct(email, productId);
        if (removed) {
            return ResponseEntity.ok("Product removed successfully.");
        } else {
            return ResponseEntity.badRequest().body("Product not found in cart.");
        }
    }


    @PreAuthorize("hasRole('ADMIN')")

    @GetMapping("/carts/{cartId}")
    public ResponseEntity<CartResponse> getAllUserCarts(
            @PathVariable @Min(1) int cartId) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(email == null) {
            throw new RuntimeException("Invalid User");
        }
        Cart cart = cartService.getCart(cartId);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CartResponse.from(cart));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/carts/{userId}/carts")
    public ResponseEntity<List<CartResponse>> getAllUserCarts(
            @PathVariable @NotBlank String userId) {

        List<Cart> carts = cartService.getCarts(userId);
        if (carts == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CartResponse.from(carts));
    }
}
