package ru.kpfu.controllers.rest;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.message.GenericResponse;
import ru.kpfu.trelloapi.dto.UserDto;
import ru.kpfu.trelloapi.services.UserService;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/auth")
@ApiOperation(value = "SIGN UP")
public class SignUpRestController {

    @Autowired
    private UserService signUpService;

    @PermitAll
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserDto user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new GenericResponse(new Date(), HttpStatus.resolve(400), "Invalid user form"));
        }

        user.provider = "TRELLO";
        signUpService.save(user);
        return ResponseEntity.ok("Success");
    }
}