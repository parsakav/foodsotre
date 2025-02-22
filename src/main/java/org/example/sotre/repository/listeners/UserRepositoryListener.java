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

    public void handleBeforeDelete(User user) {

    }
    @HandleBeforeSave
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void handleBeforeUpdate(User user) {

        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            if (!user.getPassword().startsWith("$2a$")) {
              //  user.setPassword(encoder.encode(user.getPassword()));
                System.out.println("Setting Password: " + user.getPassword());
            } else {
                System.out.println("Password is already encoded.");
            }
        }
    }




}
