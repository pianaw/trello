package ru.kpfu.trelloapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.trelloapi.validation.annotations.Password;
import ru.kpfu.trelloapi.validation.annotations.UserName;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    public Long id;

    public String name;

    public String email;

    public String password;

    public String provider;

    public String role;
}
