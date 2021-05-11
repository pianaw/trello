package ru.kpfu.security.oauth2.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.kpfu.entity.User;
import ru.kpfu.security.details.UserDetailsImpl;
import ru.kpfu.security.details.UserDetailsServiceImpl;
import ru.kpfu.security.oauth2.CustomOAuth2User;
import ru.kpfu.trelloapi.dto.UserDto;
import ru.kpfu.trelloapi.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        CustomOAuth2User oAuth2User = new CustomOAuth2User((OAuth2User) authentication.getPrincipal());

        UserDetails userDetails = null;
        try {
            userDetails = userDetailsService.loadUserByUsername(oAuth2User.getEmail());
        }
        catch (AuthenticationException e) {}

        if (userDetails == null) {
            UserDto userDto = userService.save(UserDto.builder()
                    .email(oAuth2User.getEmail())
                    .name(oAuth2User.getAttribute("name"))
                    .provider("GOOGLE")
                    .build()
            );
            User user = User.builder().id(userDto.id).name(userDto.name).email(userDto.email).role(User.Role.valueOf(userDto.role)).build();
            userDetails = new UserDetailsImpl(user);
        }

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()));

        httpServletResponse.sendRedirect("/profile");
    }


}
