package ru.kpfu.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthAccessTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtAuthenticationProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("X-ACCESS-TOKEN");
        request.setAttribute("ip", request.getRemoteAddr());

        if (token != null) {
            JwtAuthentication jwtAuthentication = new JwtAuthentication(token);
            authenticationManager.authenticate(jwtAuthentication);
            SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
        }

        filterChain.doFilter(request, response);
    }
}