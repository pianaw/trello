package ru.kpfu.trelloapi.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.trelloapi.validation.annotations.Email;
import ru.kpfu.trelloapi.validation.annotations.UserName;
import ru.kpfu.trelloapi.validation.annotations.Password;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {

    @UserName(message = "Invalid name format")
    public String name;

    @Email(message = "Invalid email")
    public String email;

    @Password(message = "Too weak password")
    public String password;
}
