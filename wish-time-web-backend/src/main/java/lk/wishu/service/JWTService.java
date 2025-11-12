package lk.wishu.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class JWTService {

    public  static final String BASE64_SECRET = "3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b";

    /**
     * To genarate secretKey for JWT token
     */
    private final SecretKey secretKey;

    public JWTService() {
        System.out.println("JWTService constructor called");
        byte[] keyBytes = Decoders.BASE64.decode(BASE64_SECRET);
        secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Get JWT token
     * @return JWT token
     */
    public String getJWTToken(String username){
        System.out.println("getJWTToken called");
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*5))
                .signWith(secretKey)
                .compact();
    }

    public String getUsername(String token){
        System.out.println("getUsername called");

        // Defensive check: Ensure the token is not null or empty
        if (token == null || token.trim().isEmpty()) {
            System.err.println("JWTService: Token input is null or empty.");
            return null;
        }

        try {
            // The parsing logic is correct
            return Jwts
                    .parserBuilder()
                    .setSigningKey(secretKey).build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e){
            // Catch JwtException (like MalformedJwtException, ExpiredJwtException, etc.)
            System.err.println("JWTService: Failed to parse token: " + e.getMessage());
            // e.printStackTrace(); // Optional: remove stack trace for cleaner logs
            return null;
        }
    }

//    public String getUsername(String token){
//        System.out.println("getUsername called");
//        try {
//            return Jwts
//                    .parserBuilder()
//                    .setSigningKey(secretKey).build()
//                    .parseClaimsJws(token)
//                    .getBody()
//                    .getSubject();
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//
//    }

    /**
     * Verify if token is valid
     */
    public boolean validateToken(String token){
        System.out.println("validateToken called");
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true; // token is valid
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            System.out.println("Token expired");
        } catch (io.jsonwebtoken.JwtException e) {
            System.out.println("Invalid token");
        }
        return false;
    }
//    public String getUsername(String token){
//        System.out.println("getUsername called");
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(secretKey).build()
//                .parseClaimsJwt(token)
//                .getBody()
//                .getSubject();
//
//    }

//    public boolean validateToken(String token){
//        System.out.println("validateToken called");
//        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
//
//    }
}
