package ru.kpfu.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.security.details.UserDetailsImpl;
import ru.kpfu.trelloapi.dto.*;
import ru.kpfu.trelloapi.services.CardReplaceService;
import ru.kpfu.trelloapi.services.CardService;

import java.util.List;

@RestController
@RequestMapping("/api/board/{boardId}/col/{columnId}/card")
public class CardRestController {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardReplaceService cardReplaceService;

    @PostMapping
    public ResponseEntity<CardDto> createCard(@PathVariable Long columnId, @RequestBody CardDto cardDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cardDto.setColumnId(columnId);
        cardDto.setCreatorId(userDetails.getId());
        return ResponseEntity.ok(cardService.save(cardDto));
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<PublicUserDto> addUser(@RequestBody UserEmailDto userEmailDto, @PathVariable Long cardId) {
        return ResponseEntity.ok(cardService.addUser(userEmailDto.email, cardId));
    }

    @PatchMapping("/{cardId}")
    public ResponseEntity<CardDto> updateCard(@RequestBody CardDto cardDto) {
        return ResponseEntity.ok(cardService.save(cardDto));
    }

    @PatchMapping
    public ResponseEntity<?> replace(@RequestBody ElementsReplaceDto elementsReplaceDto) {
        cardReplaceService.replace(elementsReplaceDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{cardId}/status")
    public ResponseEntity<String> changeStatus(@PathVariable Long cardId, @RequestBody CardStatusDto statusDto) {
        cardService.updateStatus(CardDto.builder().id(cardId).status(statusDto.getStatus()).build());
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/{cardId}/participants")
    public ResponseEntity<List<PublicUserDto>> getCardParticipant(@PathVariable Long cardId) {
        return ResponseEntity.ok(cardService.getCardParticipants(CardDto.builder().id(cardId).build()));
    }


}
