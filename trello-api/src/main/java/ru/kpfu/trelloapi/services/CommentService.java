package ru.kpfu.trelloapi.services;

import ru.kpfu.trelloapi.dto.CommentDto;

import java.util.List;

public interface CommentService extends CrudService<CommentDto, Long> {

    List<CommentDto> getCommentByCardId(Long cardId);
}
