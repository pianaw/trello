package ru.kpfu.trelloapi.dto;

import lombok.*;

import javax.validation.constraints.Max;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {

    public Long id;

    @Max(value = 30, message = "Too long title")
    public String title;

    //OWNER ID
    public Long participant_id;
}
