package com.joshell.fantasyroad.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Handles all jwt operations
 */
@Service
public class JwtService {

    private static final String SECRET_KEY = "Secret here";

    /**
     * Extracts the user email from the token
     * @param token
     * @return The result of extractClaim
     */
    public String extractUserEmail(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Gets passed the token and a function containing the subject
     * @param token
     * @param claimsResolver
     * @return the subject(in this case the user email) extracted from the token
     * @param <T>
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Generates a token
     * @param userDetails
     * @return  a token
     */
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a token with extra Claims
     * @param extraClaims
     * @param userDetails
     * @return jw token
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Checks token validity
     * @param token
     * @param userDetails
     * @return true or false
     */
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userEmail = extractUserEmail(token);
        return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Checks if the token is expired
     * @param token
     * @return true or false
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Creates the signing key
     * @return Secret key for sha algorithms
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
