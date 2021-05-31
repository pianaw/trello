package ru.kpfu.redis.repositores;

public interface BlackListRepository {
    void save(String token);

    boolean exists(String token);
}
