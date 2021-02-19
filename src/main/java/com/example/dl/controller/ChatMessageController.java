package com.example.dl.controller;

import com.example.dl.model.ChatMessage;
import com.example.dl.model.OutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ChatMessageController {

    @MessageMapping("/secured/chat")
    @SendTo("/secured/history")
    public OutputMessage send(ChatMessage msg) throws Exception {
        return new OutputMessage(
                msg.getFrom(),
                msg.getText(),
                new SimpleDateFormat("HH:mm").format(new Date()));
    }
}
