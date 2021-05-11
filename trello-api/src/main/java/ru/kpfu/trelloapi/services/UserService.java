package ru.kpfu.trelloapi.services;

import ru.kpfu.trelloapi.dto.UserDto;

import java.util.Optional;

public interface UserService extends CrudService<UserDto, Long> {

    Optional<UserDto> findByEmail(String email);
}
