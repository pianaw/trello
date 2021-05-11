package ru.kpfu.security.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.kpfu.repositories.UserRepository;
import ru.kpfu.security.details.UserDetailsImpl;

import java.io.Serializable;

@Component
public class BoardAccessPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        if (authentication == null || o == null) {
            return false;
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        if (o.getClass().equals(Long.class)) {
            return userRepository.isParticipantBoard(userDetails.getId(), (Long)o) != null;
        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
