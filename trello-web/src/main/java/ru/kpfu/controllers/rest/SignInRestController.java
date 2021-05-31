package ru.kpfu.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.trelloapi.dto.TokensDto;
import ru.kpfu.trelloapi.dto.UserEmailPasswordDto;
import ru.kpfu.trelloapi.services.LoginService;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("/api/auth")
public class SignInRestController {

    @Autowired
    private LoginService service;

    @PermitAll
    @PostMapping(path = "/signin")
    public ResponseEntity<TokensDto> signIn(@RequestBody UserEmailPasswordDto userDto, @RequestAttribute("ip") String ip) throws Throwable {
        userDto.setIp(ip);
        return ResponseEntity.ok(service.login(userDto));
    }
}
