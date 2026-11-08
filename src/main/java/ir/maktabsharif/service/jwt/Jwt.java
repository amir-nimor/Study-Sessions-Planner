package ir.maktabsharif.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class Jwt {

    private  final String SECRET = "my-super-secret-key-for-jwt-1234567890";
    private  final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));



    public  String generate(String username) {
        return Jwts.builder()
                .subject(username)
                .signWith(KEY)
                .compact();
    }




    public  String parse(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }


}
