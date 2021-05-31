package ru.kpfu.security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.kpfu.entity.User;
import ru.kpfu.security.details.UserDetailsImpl;

import java.util.Collection;
import java.util.Collections;

public class JwtAuthentication implements Authentication {

    private final String token;

    private String authority;

    private boolean isAuthenticated;

    private Object principal;

    public JwtAuthentication(String token) {
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.authority));
    }

    @Override
    public Object getCredentials() {
        return principal;
    }

    @Override
    public Object getDetails() {
        return principal;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    @Override
    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return token;
    }

    public void setAuthority(String role) {
        this.authority = role;
    }
}
