package com.HealthApp.HealthApp.Authentication;
import com.HealthApp.HealthApp.Patient.PatientRepository;
import com.HealthApp.HealthApp.Provider.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.bind.annotation.RequestBody;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Component
@Configuration
public class JwtUtils {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    ProviderRepository providerRepository;
    private String SECRET_KEY = "TaK+HaV^uvCHEFsEVfypW#7g9^k*Z8$V";


    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractEmail(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken( String email , LoginDTO type) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("type",type.getUsertype());
        return createToken(claims, email);
    }

    public String extractType(String jwt){
        return extractAllClaims(jwt).get("type",String.class);
    }
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .header().empty().add("typ","JWT")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 )) // 5 minutes expiration time
                .signWith(getSigningKey())
                .compact();
    }

    public Boolean validateToken(String token, String username) {
        return !isTokenExpired(token);
    }


}
