package com.example.webshop.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class LoginResponse {
    public String email;
    public String token;
    @JsonAlias("role_id")
    public String role;

    public LoginResponse(String email, String token, String role) {
        this.email = email;
        this.token = token;
        this.role = role;
    }
}
