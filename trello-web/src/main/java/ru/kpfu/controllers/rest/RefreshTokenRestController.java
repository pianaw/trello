package ru.kpfu.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.redis.services.RefreshSessionService;
import ru.kpfu.trelloapi.dto.TokensDto;

@RestController
@RequestMapping("/api/auth/refresh_token")
public class RefreshTokenRestController {

    @Autowired
    private RefreshSessionService refreshSessionService;

    @PostMapping
    public ResponseEntity<TokensDto> refreshToken(@RequestHeader("X-REFRESH-TOKEN") String token, @RequestAttribute("ip") String ip) {
        return ResponseEntity.ok(refreshSessionService.refreshSession(ip, token));
    }
}
