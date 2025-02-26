package com.example.userservice.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Base64;

@Component
public class JwtUtil {

    // 🔥 Base64-kodet SECRET_KEY (minst 32 tegn!)
    private static final String SECRET_KEY = "dabfbe39bf343571bb51d7753104bbab5c8d4900b289ee30648b53c2bd8635bdbaeea45f7e260d652a28a5a5c238aec908164b606cfd77af40853a56ee65b526";

    private static final long EXPIRATION_TIME = 86400000; // 1 dag (24 timer)

    private final Key key;

    // 🚀 Konverter Base64-streng til en riktig signeringsnøkkel
    public JwtUtil() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        this.key = Keys.hmacShaKeyFor(decodedKey);
    }

    // ✅ Generer JWT-token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256) // 🚀 Bruker HS256
                .compact();
    }

    // ✅ Hent brukernavn fra token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) // 🚀 Bruk samme nøkkel for validering
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // ✅ Valider token
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            boolean isExpired = claimsJws.getBody().getExpiration().before(new Date());
            System.out.println("Brukt SECRET_KEY: " + Base64.getEncoder().encodeToString(key.getEncoded()));

            return !isExpired;
        } catch (JwtException e) {
            System.out.println("Ugyldig JWT-token: " + e.getMessage());
            return false;
        }
    }

    // ✅ Få Base64-versjonen av `SECRET_KEY` for jwt.io testing
    public String getBase64SecretKey() {
        System.out.println("SECRET_KEY: " + Base64.getEncoder().encodeToString(key.getEncoded()));

        return Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

}
