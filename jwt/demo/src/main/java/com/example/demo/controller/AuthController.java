package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtUtil;
import com.example.demo.model.User;
import com.example.demo.model.UserResponse;
import com.example.demo.service.UserService;

@RestController
public class AuthController {
    
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    JwtUtil jwtUtil;
    @PostMapping("/auth")
    public ResponseEntity<?> login(@RequestBody User user) throws Exception{
        try{
            System.out.println("login called");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

        }catch(Exception e){
            e.printStackTrace();
        }
        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
        final String jwt= jwtUtil.generateToken(userDetails);
        return  ResponseEntity.ok(new UserResponse(jwt));

    }
    @GetMapping("/home")
    public ResponseEntity<?> home() throws Exception{
        return new ResponseEntity("Home Page",HttpStatus.OK);
    }
}
