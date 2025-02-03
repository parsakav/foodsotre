package org.example.sotre.repository;

import org.example.sotre.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(collectionResourceRel = "carts", path = "carts")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
