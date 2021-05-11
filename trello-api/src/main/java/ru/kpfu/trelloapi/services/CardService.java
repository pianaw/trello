package ru.kpfu.trelloapi.services;

import ru.kpfu.trelloapi.dto.CardColumnDto;
import ru.kpfu.trelloapi.dto.CardDto;
import ru.kpfu.trelloapi.dto.PublicUserDto;

import java.util.List;

public interface CardService extends CrudService<CardDto, Long> {

    PublicUserDto addUser(String email, Long cardId);

    Boolean updateStatus(CardDto cardColumn);

    List<PublicUserDto> getCardParticipants(CardDto build);

}
