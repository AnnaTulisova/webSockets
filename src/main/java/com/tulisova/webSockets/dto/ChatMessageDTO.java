package com.tulisova.webSockets.dto;

import com.tulisova.webSockets.dao.model.MessageStatus;
import com.tulisova.webSockets.dao.model.MessageType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ChatMessageDTO {

    private Long id;

    private Long chatId;

    private Long senderId;

    private String senderName;

    private String content;

    private LocalDateTime sendDateTime;

    private MessageStatus status;

    private MessageType type;
}
