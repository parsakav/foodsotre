package org.example.sotre.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Setter
@Getter
@ToString


public class UserLoginRequestModel {
    @NotNull(message = "Email may not be null")
    @NotEmpty(message = "Email may not be empty")


    private String username;
    @NotNull(message = "Password may not be null")
    @NotEmpty(message = "Password may not be empty")
    private String password;

    public @NotNull(message = "Password may not be null") @NotEmpty(message = "Password may not be empty") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "Password may not be null") @NotEmpty(message = "Password may not be empty") String password) {
        this.password = password;
    }

    public @NotNull(message = "Email may not be null") @NotEmpty(message = "Email may not be empty") String getUsername() {
        return username;
    }

    public void setUsername(@NotNull(message = "Email may not be null") @NotEmpty(message = "Email may not be empty") String username) {
        this.username = username;
    }
}
