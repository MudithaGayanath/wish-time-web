package lk.wishu.wish_time.service;

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

    private static final long EXPIRATION_TIME_MINUTES = 5;
    private static final long EXPIRATION_TIME_MS = EXPIRATION_TIME_MINUTES * 60 * 1000;
    private final SecretKey secretKey;

    public JWTService(@Value("${jwt.secret}") String secret) {
        System.out.println("JWTService constructor called");
        byte[] keyBytes = Decoders.BASE64.decode(secret);
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
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
                .signWith(secretKey)
                .compact();
    }

    public String getUsername(String token) throws Exception{
        System.out.println("getUsername called");

        // Defensive check: Ensure the token is not null or empty
        if (token == null || token.trim().isEmpty()) {
            System.err.println("JWTService: Token input is null or empty.");
            return null;
        }
            // The parsing logic is correct
            return Jwts
                    .parserBuilder()
                    .setSigningKey(secretKey).build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

    }

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
}
