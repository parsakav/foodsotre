package org.example.sotre.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.example.sotre.config.SpringApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer userID;

    @NotBlank
    private String name;
    @Column(unique=true)
    @NotBlank

    private String email;
    @NotBlank

    private String password;
    @NotBlank

    private String phoneNumber;
    @NotBlank

    private String address;
    @NotBlank

    private String city;
    @NotBlank

    private String postCode;
    @NotBlank

    private String role;

    private boolean verified = false;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Cart> carts;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Order> orders;



    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @PrePersist
    @PreUpdate
    public void encodePassword() {
        System.out.println(password);
        if (password != null) {

            BCryptPasswordEncoder passwordEncoder = (BCryptPasswordEncoder) SpringApplicationContext.getBean("passwordEncoder");
            if (getPassword() != null && !getPassword().isBlank()) {
                if (!getPassword().startsWith("$2a$")) {
                    this.password = passwordEncoder.encode(this.password);
                    System.out.println("Setting Password: " + getPassword());
                } else {
                    System.out.println("Password is already hashed");
                }
            }
        }
        if (role != null) {

            if (!role.isBlank() && !role.contains("ROLE_")) {
                role = "ROLE_" + role;
            }

        }
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}