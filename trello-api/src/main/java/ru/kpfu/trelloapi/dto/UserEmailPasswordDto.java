package ru.kpfu.trelloapi.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEmailPasswordDto {

    public String email;

    public String password;

    public String ip;
}
