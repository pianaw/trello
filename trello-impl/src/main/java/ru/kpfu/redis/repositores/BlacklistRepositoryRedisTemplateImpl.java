package ru.kpfu.redis.repositores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BlacklistRepositoryRedisTemplateImpl implements BlackListRepository {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void save(String token) {
        redisTemplate.opsForValue().set(token, "");
    }

    @Override
    public boolean exists(String token) {
        Boolean hasToken = redisTemplate.hasKey(token);
        return hasToken != null && hasToken;


    }
}
