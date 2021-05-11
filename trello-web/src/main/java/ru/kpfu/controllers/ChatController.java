package ru.kpfu.controllers;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.HtmlUtils;
import ru.kpfu.controllers.message.Message;
import ru.kpfu.security.details.UserDetailsImpl;

import java.security.Principal;


@Controller
public class ChatController {

    @GetMapping("/board/{boardId}/chat")
    public String getChatPage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model, @PathVariable Long boardId) {
        model.addAttribute("authorName", userDetails.getUsername());
        return "chat";
    }

    @MessageMapping("/chat/{chatId}")
    @SendTo("/topic/{chatId}")
    public Message greeting(Principal user, Message message, @DestinationVariable("chatId")Long chatId) throws Exception {
        message.setAuthorName(user.getName());
        return message;
    }
}