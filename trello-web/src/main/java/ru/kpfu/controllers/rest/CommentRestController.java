package ru.kpfu.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.security.details.UserDetailsImpl;
import ru.kpfu.trelloapi.dto.CommentDto;
import ru.kpfu.trelloapi.dto.UserDto;
import ru.kpfu.trelloapi.services.CommentService;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/board/{boardId}/col/{colId}/card/{cardId}/comment")
public class CommentRestController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Long cardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentDto.setAuthorId(userDetails.getId());
        commentDto.setCardId(cardId);
        commentDto.setCreationDate(LocalDateTime.now().toString());

        commentDto = commentService.save(commentDto);
        commentDto.setAuthorName(userDetails.getName());
        return ResponseEntity.ok(commentDto);
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable Long cardId) {
        return ResponseEntity.ok(commentService.getCommentByCardId(cardId));
    }
}
