package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;
import com.example.demo.util.JwtUtil;

@RestController
// @RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        // Hardcoded user for demonstration
        if ("user".equals(user.getUsername()) && "password".equals(user.getPassword())) {
            return jwtUtil.generateToken(user.getUsername());
        }
        throw new RuntimeException("Invalid username or password");

    }

    @GetMapping("/home")
    public String test() {
        System.out.println(" print knkjbcjdcdscklkm");
        // Hardcoded user for demonstration
        return "Hello world";
    }

    @GetMapping("/test")
    public String test2(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            System.out.println(" test");
            // Hardcoded user for demonstration
            return jwtUtil.getUsernameFromToken(token);
        }else{
            return "Incorrect token passing";
        }
    }

}
