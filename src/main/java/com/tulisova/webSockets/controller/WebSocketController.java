package com.tulisova.webSockets.controller;

import com.tulisova.webSockets.dto.ChatMessageDTO;
import com.tulisova.webSockets.service.ChatMessageService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class WebSocketController {

    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/user/publicChatRoom")
    public ChatMessageDTO sendMessage(@Payload ChatMessageDTO chatMessage) {
        String senderName = chatMessageService.processMessage(chatMessage);
        chatMessage.setSenderName(senderName);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/user/publicChatRoom")
    public ChatMessageDTO addUser(@Payload ChatMessageDTO chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSenderName());
        return chatMessage;
    }
}
