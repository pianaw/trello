package ru.kpfu.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.entity.User;
import ru.kpfu.redis.services.RefreshSessionService;
import ru.kpfu.redis.services.RedisUserService;
import ru.kpfu.repositories.UserRepository;
import ru.kpfu.trelloapi.dto.TokensDto;
import ru.kpfu.trelloapi.dto.UserEmailPasswordDto;
import ru.kpfu.trelloapi.services.LoginService;
import ru.kpfu.utils.JwtUtil;

import java.util.UUID;
import java.util.function.Supplier;

@Service
public class LoginServiceImpl implements LoginService<UserEmailPasswordDto> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisUserService redisUserService;

    @Autowired
    RefreshSessionService redisRefreshSessionService;


    @Override
    public TokensDto login(UserEmailPasswordDto userEmailPasswordDto) throws Throwable {
        User user = userRepository.findByEmail(userEmailPasswordDto.email).orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("User not found"));
        if (passwordEncoder.matches(userEmailPasswordDto.password, user.getHashPassword())) {
            String accessToken = jwtUtil.createAccessTokenFor(user);

            String uuid = UUID.randomUUID().toString();
            String refreshToken = jwtUtil.createRefreshToken(uuid);
            redisUserService.addTokenToUser(user, accessToken);

            redisRefreshSessionService.createRefreshSession(user, refreshToken, uuid, userEmailPasswordDto.ip);
            return TokensDto.builder()
                    .jwt(accessToken)
                    .rt(refreshToken)
                    .build();
        }
        else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
