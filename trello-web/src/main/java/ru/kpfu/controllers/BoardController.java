package ru.kpfu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kpfu.trelloapi.services.CardColumnService;

@Controller
public class BoardController {

    @Autowired
    private CardColumnService boardDetailsService;

    @GetMapping("/board/{boardId}")
    @PreAuthorize("isAuthenticated() && hasPermission(#boardId, 'Long.class')")
    public String getBoardPage(@PathVariable Long boardId, Model model) {
        model.addAttribute("columns", boardDetailsService.getColumnsByBoardId(boardId));
        return "board";
    }
}
