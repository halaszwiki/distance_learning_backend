package com.example.dl.payload;

import java.util.Set;

public class SignupRequest {

    private String username;

    private String email;

    private String role;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return this.role;
    }
}