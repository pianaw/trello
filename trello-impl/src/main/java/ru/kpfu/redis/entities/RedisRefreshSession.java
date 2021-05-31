package ru.kpfu.redis.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("refresh_session")
public class RedisRefreshSession {
    @Id
    public String uuid;
    public String token;
    public String ip;
    public String userId;
}
