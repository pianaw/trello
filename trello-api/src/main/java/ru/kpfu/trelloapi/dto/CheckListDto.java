package ru.kpfu.trelloapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckListDto {

    public String description;

    public String isDone;

    public Long cardId;
}
