package org.example.sotre.repository;

import org.example.sotre.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("from Order o join fetch o.user u where u.email=:email")
    List<Order> findUserOrders(String email);


}
