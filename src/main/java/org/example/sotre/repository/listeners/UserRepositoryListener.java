package org.example.sotre.repository.listeners;


import org.example.sotre.config.SpringApplicationContext;
import org.example.sotre.model.Product;
import org.example.sotre.model.User;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@RepositoryEventHandler
@Component
public class UserRepositoryListener {

    @HandleBeforeDelete
    @PreAuthorize("hasRole('ADMIN')")

    public void handleBeforeDelete(User product) {

    }



}
