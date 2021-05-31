package ru.kpfu.redis.repositores;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.kpfu.redis.entities.RedisRefreshSession;

public interface RefreshSessionRepository extends KeyValueRepository<RedisRefreshSession, String> {
}
