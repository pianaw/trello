package ru.kpfu.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.entity.CheckList;
import ru.kpfu.trelloapi.dto.CheckListDto;
import ru.kpfu.trelloapi.services.CheckListService;

import java.util.List;

@RestController
@RequestMapping("/api/board/{boardId}/col/{columnId}/card/{cardId}/check_list")
public class CheckListRestController {

    @Autowired
    private CheckListService checkListService;

    @PostMapping
    public void createCheckList(@PathVariable Long cardId, @RequestBody CheckListDto checkListDto) {
        checkListDto.setCardId(cardId);
        checkListService.save(checkListDto);
    }

    @GetMapping
    public ResponseEntity<List<CheckListDto>> getCheckLists(@PathVariable Long cardId) {
        return ResponseEntity.ok(checkListService.getCheckListsByCardId(cardId));
    }
}
