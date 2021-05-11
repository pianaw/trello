package ru.kpfu.trelloapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardColumnDto {

    public Long id;

    public Set<CardDto> cards;

    public Long boardId;

    public Long orderId;
}
