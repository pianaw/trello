package ru.kpfu.security.jwt;

import lombok.AllArgsConstructor;
import ru.kpfu.security.details.UserDetailsImpl;

import java.util.List;

@AllArgsConstructor
public class JwtResponse {

    private String jwt;
    private UserDetailsImpl userDetails;
    private String email;
    List<String> roles;
}
