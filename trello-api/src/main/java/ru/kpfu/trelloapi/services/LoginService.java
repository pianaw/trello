package ru.kpfu.trelloapi.services;

import ru.kpfu.trelloapi.dto.TokensDto;

public interface LoginService<T> {

    TokensDto login(T user) throws Throwable;
}
