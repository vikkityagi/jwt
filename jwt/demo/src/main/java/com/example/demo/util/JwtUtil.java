package com.example.demo.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private String secret = generatedSecretKey(); // Replace with a secure key
    private int jwtExpirationInMs = 60000;  // Token validity: 1 minute

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
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            System.out.println("Token claims: " + claims.getBody()); // Debug
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token expired");
            return false;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Token invalid");
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