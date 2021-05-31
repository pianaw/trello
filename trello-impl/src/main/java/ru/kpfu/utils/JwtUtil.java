package ru.kpfu.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.kpfu.entity.User;

import java.time.LocalDateTime;

@Component
public class JwtUtil {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Autowired
    private Algorithm algorithm;

    public JwtUtil(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String createAccessTokenFor(User user) {
        String token = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("role", user.getRole().toString())
                .withClaim("name", user.getName())
                .withClaim("email", user.getEmail())
                .withClaim("createdAt", LocalDateTime.now().toString())
                .sign(algorithm);
        return token;
    }

    public String createRefreshToken(String uuid) {
        String token = JWT.create()
                .withClaim("uuid", uuid)
                .withClaim("createdAt", LocalDateTime.now().toString())
                .sign(algorithm);
        return token;
    }
}
