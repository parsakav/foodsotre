package org.example.sotre.repository.listeners;


import org.example.sotre.config.SpringApplicationContext;
import org.example.sotre.model.Product;
import org.example.sotre.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RepositoryEventHandler
@Component

public class UserRepositoryListener {

    @Autowired
    private PasswordEncoder encoder;
    @HandleBeforeDelete
    @PreAuthorize("hasRole('ADMIN')")

    public void handleBeforeDelete(User product) {

    }
    @HandleBeforeSave
    @PreAuthorize("hasAnyRole('USER','ADMIN')")

    public void handleBeforeUpdate(User product) {
        product.setPassword(encoder.encode(product.getPassword()));
        System.out.println("Setting Password: " + product.getPassword());
    }


}
