package com.tulisova.webSockets.mapper;

import com.tulisova.webSockets.dao.model.ChatMessage;
import com.tulisova.webSockets.dto.ChatMessageDTO;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

public interface ChatMessageMapper {
    @Mappings({
            @Mapping(target = "chatId", source = "chatMessage.chat.id"),
            @Mapping(target = "senderName", source = "chatMessage.sender.fullName"),
    })
    ChatMessageDTO toDTO(ChatMessage chatMessage);
}
