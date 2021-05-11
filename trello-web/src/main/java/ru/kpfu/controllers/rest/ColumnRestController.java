package ru.kpfu.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.trelloapi.dto.CardColumnDto;
import ru.kpfu.trelloapi.dto.ElementsReplaceDto;
import ru.kpfu.trelloapi.services.CardColumnService;
import ru.kpfu.trelloapi.services.ColumnsReplaceService;

@RestController
@RequestMapping("/api/board/{boardId}/col")
public class ColumnRestController {

    @Autowired
    private ColumnsReplaceService columnsReplaceService;

    @Autowired
    private CardColumnService boardDetailsService;

    @PostMapping
    @PreAuthorize("isAuthenticated() && hasPermission(#boardId, 'Long.class')")
    public ResponseEntity<CardColumnDto> createColumn(@PathVariable Long boardId) {
        return ResponseEntity.ok(boardDetailsService.save(CardColumnDto.builder().boardId(boardId).build()));
    }

    @PatchMapping
    public void replaceColumns(@RequestBody ElementsReplaceDto columnsDto) {
        columnsReplaceService.replace(columnsDto);
    }

    @DeleteMapping("/{columnId}")
    public ResponseEntity<?> deleteColumn(@PathVariable Long columnId) {
        boardDetailsService.delete(CardColumnDto.builder().id(columnId).build());

        return ResponseEntity.ok("OK");
    }
}
