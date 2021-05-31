package ru.kpfu.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthRefreshTokenFilter extends OncePerRequestFilter {

    @Autowired
    private Algorithm algorithm;

    public AuthRefreshTokenFilter(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader("X-REFRESH-TOKEN");
        request.setAttribute("ip", request.getRemoteAddr());
        if (token == null) {
            response.setStatus(500);
            return;
        }

        DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);
        chain.doFilter(request, response);
    }
}
