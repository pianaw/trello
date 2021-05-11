package ru.kpfu.trelloapi.services;

import ru.kpfu.trelloapi.dto.CheckListDto;

import java.util.List;

public interface CheckListService extends CrudService<CheckListDto, Long>{

    List<CheckListDto> getCheckListsByCardId(Long cardId);
}
