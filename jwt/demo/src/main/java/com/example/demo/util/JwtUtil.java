package com.example.demo.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private String secret = generatedSecretKey(); // Replace with a secure key
    private int jwtExpirationInMs = 3600000;  // Token validity: 1 hour

    private String generatedSecretKey(){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[32]; // 256 bits
        random.nextBytes(key);
        String secretKey = Base64.getEncoder().encodeToString(key);
        return secretKey;
    }

    // Generate JWT token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // Validate JWT token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Extract username from JWT token
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
