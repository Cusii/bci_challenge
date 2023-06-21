package com.challenge.bci.utils;

import com.challenge.bci.exception.RequestExceptions;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

public class JwtUtils {

    private static final String SECRET_KEY = "safsfafafaw6461faw14416as54fa6adw";
    // Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public static String generateToken(String name) {
        SecretKey key = Keys.hmacShaKeyFor(Base64.getEncoder().encode(SECRET_KEY.getBytes()));

        return Jwts.builder()
                .setSubject(name)
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static String extractNameFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Base64.getEncoder().encode(SECRET_KEY.getBytes()));


            token.startsWith("Bearer ");
            String jwtToken = token.substring(7); // Eliminar el "Bearer "

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();

            return claims.getSubject();
    }
}
