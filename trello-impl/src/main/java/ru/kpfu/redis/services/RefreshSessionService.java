package ru.kpfu.redis.services;

import ru.kpfu.entity.User;
import ru.kpfu.trelloapi.dto.TokensDto;

public interface RefreshSessionService {

    String createRefreshSession(User user, String token, String uuid, String ip);

    TokensDto refreshSession(String ip, String token);
}
