package ru.kpfu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.security.details.UserDetailsImpl;
import ru.kpfu.security.oauth2.CustomOAuth2User;
import ru.kpfu.trelloapi.dto.UserDto;
import ru.kpfu.trelloapi.services.BoardService;
import ru.kpfu.trelloapi.services.UserService;

import javax.annotation.security.PermitAll;
import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PermitAll
    @GetMapping("/profile")
    public String getProfilePage(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        model.addAttribute("boards", boardService.getUserBoards(user.getId()));

        return "profile";
    }
}
