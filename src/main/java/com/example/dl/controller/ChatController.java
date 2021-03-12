package com.example.dl.controller;

import com.example.dl.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final SimpMessagingTemplate template;

    @Autowired
    ChatController(SimpMessagingTemplate template){
        this.template = template;
    }

    @MessageMapping("/send/message")
    public void sendMessage(String message){
        ChatMessage chatMessage = new ChatMessage(SecurityContextHolder.getContext().getAuthentication().getName(), message);
        System.out.println("username: " + chatMessage.getUsername() + " message: "  + chatMessage.getMessage());
        this.template.convertAndSend("/message",  chatMessage);
    }
}