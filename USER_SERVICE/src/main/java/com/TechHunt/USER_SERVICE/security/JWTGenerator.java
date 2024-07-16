package com.TechHunt.USER_SERVICE.security;

import com.TechHunt.USER_SERVICE.models.CustomUserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JWTGenerator {

    public String generateToken(Authentication authentication){
        CustomUserDetail customUserDetail= (CustomUserDetail) authentication.getPrincipal();
        String username = customUserDetail.getEmail();  // gets the email
        System.err.println("During json creation "+username);
        Date curr = new Date();
        Date expiry = Date.from(Instant.now().plus(365 , ChronoUnit.DAYS));

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(curr)
                .setExpiration(expiry)
                .signWith(getKey() , SignatureAlgorithm.HS256).compact();

        return token;
    }

    public SecretKey getKey(){
        byte[] key = Decoders.BASE64.decode(SecurityConstants.JWT_SECRET);
        return Keys.hmacShaKeyFor(key);
    }

    public boolean validToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(SecurityConstants.JWT_SECRET).build().parseClaimsJws(token);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public String getEmail(String token){
        Claims claims = Jwts.parserBuilder().setSigningKey(SecurityConstants.JWT_SECRET).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}
