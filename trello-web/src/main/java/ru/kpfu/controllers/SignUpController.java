package ru.kpfu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.kpfu.message.GenericResponse;
import ru.kpfu.trelloapi.dto.UserDto;
import ru.kpfu.trelloapi.dto.UserForm;
import ru.kpfu.trelloapi.services.UserService;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.Date;

@Controller
public class SignUpController {

    @Autowired
    private UserService signUpService;

    @PermitAll
    @PostMapping("/signup")
    public String signUp(@Valid UserForm user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError());
            model.addAttribute("userForm", user);
            return "sign_up";
        }

        signUpService.save(UserDto.builder().email(user.email).name(user.email).password(user.password).build());
        return "login";
    }

    @GetMapping("/signup")
    @PermitAll
    public String signUp(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "sign_up";
    }
}
