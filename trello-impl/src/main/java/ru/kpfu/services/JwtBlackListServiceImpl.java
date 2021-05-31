package ru.kpfu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.redis.repositores.BlackListRepository;
import ru.kpfu.trelloapi.services.JwtBlackListService;

@Service
public class JwtBlackListServiceImpl implements JwtBlackListService {

    @Autowired
    private BlackListRepository blacklistRepository;

    @Override
    public void add(String token) {
        blacklistRepository.save(token);
    }

    @Override
    public boolean exists(String token) {
        return blacklistRepository.exists(token);
    }
}