package com.project.hospitalManagement_ProductionThings.security;

import com.project.hospitalManagement_ProductionThings.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class AuthUtil {


    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    // converting our secret key to hmac Sha key and we will get object of our secret key
    private SecretKey getSecretKey() {
        return  Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }


    // Now we will use this to make a token
    public String generateAccessToken(User user) {

        // so here we will usr User typed in parameter as Payload for jwt token, getSecretKey as algorithm and seccret key we have

        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId", user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))     // 10 mins
                .signWith(getSecretKey())
                .compact();
    }


    // Extracting User name from Token
    public String getUsernameFromToken(String token) {
        Claims claims =  Jwts.parser()
                .verifyWith(getSecretKey())
                .build().parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }
}
