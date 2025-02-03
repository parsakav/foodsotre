package org.example.sotre.controller;

import jakarta.validation.constraints.NotBlank;
import org.example.sotre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    @Lazy
   private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    @PutMapping("/resetPassword")
    @PreAuthorize("hasRole('USER')")

    public ResponseEntity<Boolean> resetPassword(@NotBlank @RequestParam String email,

                                        @NotBlank @RequestParam String password) {


        userRepository.resetPassword(email, bCryptPasswordEncoder.encode(password));
        return ResponseEntity.ok(true);
    }
}
