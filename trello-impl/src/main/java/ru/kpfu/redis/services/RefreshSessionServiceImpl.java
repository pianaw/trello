package ru.kpfu.redis.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.entity.User;
import ru.kpfu.redis.entities.RedisRefreshSession;
import ru.kpfu.redis.repositores.RefreshSessionRepository;
import ru.kpfu.repositories.UserRepository;
import ru.kpfu.trelloapi.dto.TokensDto;
import ru.kpfu.utils.JwtUtil;

import java.util.UUID;

@Service
public class RefreshSessionServiceImpl implements RefreshSessionService {

    @Autowired
    private RefreshSessionRepository redisRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired RedisUserService redisUserService;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public String createRefreshSession(User user, String token, String uuid, String ip) {
        RedisRefreshSession redisRefreshSession = RedisRefreshSession.builder()
                .userId(user.redisId)
                .ip(ip)
                .token(token)
                .uuid(uuid)
                .build();
        redisRepository.save(redisRefreshSession);

        return uuid;
    }

    @Override
    public TokensDto refreshSession(String ip, String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        String uuid = decodedJWT.getClaim("uuid").asString();

        RedisRefreshSession session = redisRepository.findById(uuid).orElseThrow(IllegalArgumentException::new);
        if (session.ip.equals(ip) && session.token.equals(token)) {
            redisRepository.deleteById(session.uuid);
            User user = userRepository.findByRedisId(session.userId).get();

            String accessToken = jwtUtil.createAccessTokenFor(user);

            uuid = UUID.randomUUID().toString();
            String refreshToken = jwtUtil.createRefreshToken(uuid);
            redisUserService.addTokenToUser(user, accessToken);

            createRefreshSession(user, refreshToken, uuid, ip);
            return TokensDto.builder()
                    .jwt(accessToken)
                    .rt(refreshToken)
                    .build();
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
