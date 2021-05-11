package ru.kpfu.trelloapi.services;

import ru.kpfu.trelloapi.dto.BoardDto;

import java.util.List;
public interface BoardService extends CrudService<BoardDto, Long> {

    List<BoardDto> getUserBoards(Long id);

    Boolean addUser(String email, Long boardId);
}
