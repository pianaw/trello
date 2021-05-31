package ru.kpfu.redis.services;

import ru.kpfu.entity.User;

public interface RedisUserService {

    void addTokenToUser(User user, String token);

    void addAllTokensToBlackList(User user);

}
