package ru.kpfu.trelloapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import javax.validation.Valid;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {
    public Long id;

    public Long cardId;

    public String name;

    public byte[] data;

    public String type;
}
