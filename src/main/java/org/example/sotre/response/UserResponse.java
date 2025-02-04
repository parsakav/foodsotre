package org.example.sotre.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class UserResponse {
    private int id;
    private String email;
    private boolean verified;

    public UserResponse() {
    }

    public UserResponse(int id, String email,boolean verified) {
        this.id = id;
        this.email = email;
        this.verified=verified;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
