package ru.kpfu.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.kpfu.entity.User;
import ru.kpfu.security.details.UserDetailsImpl;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private Algorithm algorithm;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;

        DecodedJWT decodedJWT;
        try {
            decodedJWT = JWT.require(algorithm).build().verify(jwtAuthentication.getName());
            jwtAuthentication.setPrincipal(new UserDetailsImpl(User.builder()
                    .id(Long.parseLong(decodedJWT.getSubject()))
                    .name(decodedJWT.getClaim("name").asString())
                    .email(decodedJWT.getClaim("email").asString())
                    .build()));
        }
        catch (JWTVerificationException e) {
            throw new IllegalArgumentException(e);
        }

        jwtAuthentication.setAuthenticated(true);
        jwtAuthentication.setAuthority(decodedJWT.getClaim("role").toString());

        return jwtAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }
}