package ru.kpfu.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.security.details.UserDetailsImpl;
import ru.kpfu.security.jwt.JwtAuthentication;
import ru.kpfu.trelloapi.dto.BoardDto;
import ru.kpfu.trelloapi.dto.UserEmailDto;
import ru.kpfu.trelloapi.services.BoardService;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardRestController {

    @Autowired
    private BoardService boardService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BoardDto> createBoard(@RequestBody BoardDto boardDto, @AuthenticationPrincipal UserDetailsImpl user) {
        boardDto.setParticipant_id(user.getId());
        return ResponseEntity.ok(boardService.save(boardDto));
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<BoardDto>> getUsersBoards(@AuthenticationPrincipal UserDetailsImpl user) {
        return ResponseEntity.ok(boardService.getUserBoards(user.getId()));
    }

    @PutMapping("/{boardId}")
    @PreAuthorize("isAuthenticated() && hasPermission(#boardId, 'Long.class')")
    public void addUser(@RequestBody UserEmailDto userEmailDto, @PathVariable Long boardId) {
        boardService.addUser(userEmailDto.email, boardId);
    }
}
