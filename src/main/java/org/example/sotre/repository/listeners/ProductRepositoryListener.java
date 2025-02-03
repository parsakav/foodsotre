package org.example.sotre.repository.listeners;


import org.example.sotre.model.Product;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class ProductRepositoryListener {
    @HandleBeforeSave
    @PreAuthorize("hasRole('ADMIN')")

    public void handleBeforeSave(Product product) {

    }
    @HandleBeforeDelete
    @PreAuthorize("hasRole('ADMIN')")

    public void handleBeforeDelete(Product product) {

    } @HandleBeforeCreate
    @PreAuthorize("hasRole('ADMIN')")

    public void handleBeforeCreate(Product product) {

    }
}
