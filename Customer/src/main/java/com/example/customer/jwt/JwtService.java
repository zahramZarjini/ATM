package com.example.customer.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.impl.JWTParser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.validation.Payload;
import java.util.List;

@Service
public class JwtService {
    public String createToken(String username) {
        return JWT.create().withSubject(username).withClaim("role", List.of(new SimpleGrantedAuthority("USER"))).sign(Algorithm.HMAC256("secret"));
    }

    public Payload validateToken(String token) {
        final String jsonPayload = JWT.decode(token).getPayload();
        return (Payload) new JWTParser().parsePayload(jsonPayload);
    }
}
