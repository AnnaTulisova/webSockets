package com.tulisova.webSockets.dto;

import com.tulisova.webSockets.dao.model.MessageStatus;
import com.tulisova.webSockets.dao.model.MessageType;

import java.time.LocalDateTime;

public class ChatMessageDTO {

    private Long id;

    private Long chatId;

    private Long senderName;

    private String content;

    private LocalDateTime sendDateTime;

    private MessageStatus status;

    private MessageType type;
}
