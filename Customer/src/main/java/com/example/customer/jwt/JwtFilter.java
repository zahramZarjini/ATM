package com.example.customer.jwt;

import com.auth0.jwt.interfaces.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null) {
            filterChain.doFilter(request, response);
        }

        if (!authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
        }

        final String token = authorizationHeader.replace("Bearer ", "");

        final Payload payload = (Payload) jwtService.validateToken(token);

        final UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(payload.getSubject(),
                        null,
                        payload.getClaim("roles")
                                .asList(SimpleGrantedAuthority.class));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    }

}
