package com.tulisova.webSockets.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChatNotification {
    private Long id;
    private Long senderId;
    private String senderName;
}