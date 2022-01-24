package com.tulisova.webSockets.mapper;

import com.tulisova.webSockets.dao.model.Chat;
import com.tulisova.webSockets.dto.ChatDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface ChatMapper {
    ChatDTO toDTO(Chat chat);
}
