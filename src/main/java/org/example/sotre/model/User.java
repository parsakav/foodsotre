package org.example.sotre.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.example.sotre.config.SpringApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer userID;

    private String name;
    @Column(unique=true)
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String city;
    private String postCode;
    private String role; // Kunde/Admin

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

            this.password = passwordEncoder.encode(this.password);
        }     if (role != null) {

role="ROLE_"+role;

        }
    }
}