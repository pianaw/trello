package ru.kpfu.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;

@AllArgsConstructor
@Data
@Builder
public class JwtResponse {


    private String accessToken;

    private String refreshToken;

    @Value("${app.jwtExpirationMs}")
    private String accessExpiresIn;
}
