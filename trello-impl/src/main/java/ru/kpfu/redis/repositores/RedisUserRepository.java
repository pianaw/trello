package ru.kpfu.redis.repositores;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.kpfu.redis.entities.RedisUser;

public interface RedisUserRepository extends KeyValueRepository<RedisUser, String> {
}
