package ru.kpfu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.security.PermitAll;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SignInController {

    private static String authorizationRequestBaseUri
            = "oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls
            = new HashMap<>();

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @GetMapping("/auth/signin")
    @PermitAll
    public String getSignInPage(Model model) {
        return "login";
    }

    @PostMapping("/auth/signin")
    @PermitAll
    public String signIn() {
        return "redirect:/profile";
    }
}
