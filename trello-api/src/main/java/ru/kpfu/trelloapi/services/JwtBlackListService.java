package ru.kpfu.trelloapi.services;

public interface JwtBlackListService {
    void add(String token);

    boolean exists(String token);
}