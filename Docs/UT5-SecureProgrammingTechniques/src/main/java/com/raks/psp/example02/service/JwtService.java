package com.raks.psp.example02.service;

import com.raks.psp.example02.data.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private final byte[] _secretKeyByteArray;

    public JwtService(@Value("${jwt.secret-key}") String secretKey) {
        _secretKeyByteArray = secretKey.getBytes();
    }

    public String createJwt(User user) {
        Date now = new Date();
        // 10 minutes
        long expirationTime = 60 * 10 * 1000;
        return Jwts.builder()
                   .setSubject(user.get_username())
                   .setIssuedAt(now)
                   .setExpiration(new Date(now.getTime() + expirationTime))
                   .signWith(Keys.hmacShaKeyFor(_secretKeyByteArray))
                   .compact();
    }

    public User extractUserFromJwt(String jwt) {
        Claims claims = extractClaims(jwt);
        return new User(claims.getSubject(), "");
    }

    private Claims extractClaims(String jwt) {
        try {
            return Jwts.parserBuilder()
                       .setSigningKey(Keys.hmacShaKeyFor(_secretKeyByteArray))
                       .build()
                       .parseClaimsJws(jwt)
                       .getBody();
        } catch (Exception ex) {
            throw new JwtParseException(ex);
        }
    }

}

class JwtParseException extends RuntimeException {

    public JwtParseException(Exception ex) {
        super(ex);
    }

}