package com.Authentication.Cafe_Demo.Authentication.jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;


@Service
public class JWTService {

//     Secret Key
    private static final String SECRET = "admincafedemo12097498%^*@&@#)#908";

//    convert Key
    private Key getKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

//    generated Key
    public String generatedKey(String email){
        return Jwts
                .builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getKey())
                .compact();
    }

//    parser Token
    private Claims getClaim(String token){
        return Jwts
                .parser()
                .verifyWith((javax.crypto.SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

//    Extract Subject (Email)
    public String extract(String token){
        return getClaim(token).getSubject();
    }

//    expire
    public boolean expire(String token){
        return getClaim(token).getExpiration().before(new Date());
    }

//    validation
    private boolean validate(String email, String token){
        return extract(token).equals(email) && !expire(token);
    }
}
