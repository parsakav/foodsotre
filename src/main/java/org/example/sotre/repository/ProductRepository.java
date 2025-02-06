package org.example.sotre.repository;


import org.example.sotre.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")



public interface ProductRepository extends JpaRepository<Product, Integer> {


/*
    @PreAuthorize("hasRole('USER')")
*/

    @Query("from Product p where p.name like %:title%")
    List<Product> searchProductByTitle(@Param("title") String title);
    @PreAuthorize("hasRole('USER')")

    @Query("from Product p where p.description like %:description%")
    List<Product> searchProductByDescription(@Param("description") String description);

}

