package br.com.costa.cadastrodeprodutosSecurity.security.service;

import br.com.costa.cadastrodeprodutosSecurity.enitity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static br.com.costa.cadastrodeprodutosSecurity.utils.Utils.logger;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtService {



    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public String generateToken(UserEntity user) {

        logger.info("Generate JWT Token");

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("id", user.getId().toString())
                .claim("name", user.getName())
                .claim("role", user.getRole().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigninKey())
                .compact();


    }

    private SecretKey getSigninKey() {

        logger.info("Generate a deccoders");

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractClaims(String token) {

        logger.info("Extract claims from token");

        return Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractEmail(String token) {

        logger.info("Extract email from token");

        return extractClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserEntity user) {

        logger.info("Checking if token is valid");

        String email = extractEmail(token);

        return email.equals(user.getEmail()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {

        logger.info("Checking if token is expired");

        return extractClaims(token)
                .getExpiration()
                .before(new Date());
    }


}
