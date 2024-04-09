package com.example.webshop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "users")
public class CustomUser {

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public CustomUser(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public CustomUser() {
    }

    public String getRoles() {
        return role;
    }
}
