package com.ai.talenthub.auth_service.security.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    /**
     * Secret Key
     */
    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Generate Access Token
     */
    public String generateAccessToken(String email) {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Generate Refresh Token
     */
    public String generateRefreshToken(String email) {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshExpiration))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Extract Email
     */
    public String extractUsername(String token) {

        return extractAllClaims(token).getSubject();
    }

    /**
     * Validate Token
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {

        String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                && !extractAllClaims(token).getExpiration().before(new Date());
    }
    /**
     * Extract Claims
     */
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}