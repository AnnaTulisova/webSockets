package com.tulisova.webSockets.mapper;

import com.tulisova.webSockets.dao.model.ChatMessage;
import com.tulisova.webSockets.dto.ChatMessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface ChatMessageMapper {
    @Mappings({
            @Mapping(target = "chatId", source = "chatMessage.chat.id")
    })
    ChatMessageDTO toDTO(ChatMessage chatMessage);

    ChatMessage fromDTO(ChatMessageDTO chatMessageDTO);
}
