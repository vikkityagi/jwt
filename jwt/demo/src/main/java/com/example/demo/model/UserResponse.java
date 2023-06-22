package com.example.demo.model;

public class UserResponse {
    private final String jwt;

    public UserResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
    
}
