package com.example.Quiz.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;

import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    @Value("${security.jwt.secret-key}")
    private String secretkey;

    @Value("${security.jwt.expiration-time}")
    private Long jwtExp;

    public String extracteUsername(String token) {
        return extractClaim(token, Claims::getSubject);

    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsresolver) {
        final Claims claims = extractAllClaims(token);
        return claimsresolver.apply(claims);

    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);

    }

    public String generateToken(Map<String, Object> extracaClaims, UserDetails userDetails) {
        return buildToken(extracaClaims, userDetails, jwtExp);
    }

    public long getExpirationTime() {
        return jwtExp;
    }

    private String buildToken(

            Map<String, Object> extracaClaims,
            UserDetails userDetails,
            long expiration

    ) {
        return Jwts
                .builder()
                .setClaims(extracaClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignedKey(), SignatureAlgorithm.ES256)
                .compact();

    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extracteUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

    }

    private boolean isTokenExpired(String token) {
        return extarctExpiration(token).before(new Date());
    }

    private Date extarctExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignedKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignedKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
