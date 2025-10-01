package com.ITMO.informationSecurity.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final String secretKey256 = "ELgDOv4xnToGWhQRZbwuRL2Lde+RcY/XuVDS2VJZG10=";
    private final long ttlMillis = 24 * 60 * 60 * 1000L; // 24h


    public String generateToken(String username) {
        return generateToken(Map.of(), username);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            String username
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ttlMillis))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(parse(token).getBody());
    }

    public boolean isTokenValid(String token, String expectedUsername) {
        Jws<Claims> jws = parse(token);
        String sub = jws.getBody().getSubject();
        Date exp = jws.getBody().getExpiration();
        boolean notExpired = exp == null || exp.after(new Date());
        return notExpired && expectedUsername != null && expectedUsername.equals(sub);
    }

    private Jws<Claims> parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey256);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
