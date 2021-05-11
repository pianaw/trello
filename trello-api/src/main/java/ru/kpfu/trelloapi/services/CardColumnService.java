package ru.kpfu.trelloapi.services;


import ru.kpfu.trelloapi.dto.CardColumnDto;

import java.util.List;

public interface CardColumnService extends CrudService<CardColumnDto, Long> {

    List<CardColumnDto> getColumnsByBoardId(Long id);
}
