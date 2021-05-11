package ru.kpfu.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.entity.User;
import ru.kpfu.errors.UserAlreadyExistException;
import ru.kpfu.repositories.UserRepository;
import ru.kpfu.trelloapi.dto.UserDto;
import ru.kpfu.trelloapi.services.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<UserDto> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public UserDto save(UserDto userDto) {
        if (userDto.provider == null) {
            userDto.provider = "TRELLO";
        }

        User user = User.builder()
                .name(userDto.name)
                .email(userDto.email)
                .role(User.Role.USER)
                .provider(User.Provider.valueOf(userDto.provider))
                .build();

        if (userDto.password != null) {
            user.hashPassword = passwordEncoder.encode(userDto.password);
        }

        try {
            user = userRepository.save(user);
        }
        catch (Exception e) {
            throw new UserAlreadyExistException("Registration error. Perhaps a user with this email already exists");
        }

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public Boolean delete(UserDto userDto) {
        return null;
    }

    @Override
    public Boolean deleteById(Long aLong) {
        return null;
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(UserDto.builder()
                .name(user.get().name)
                .email(user.get().email)
                .build());

    }
}
