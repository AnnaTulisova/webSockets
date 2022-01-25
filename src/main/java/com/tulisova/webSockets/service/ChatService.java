package com.tulisova.webSockets.service;

import com.tulisova.webSockets.dao.model.Chat;
import com.tulisova.webSockets.dao.projection.EntityId;
import com.tulisova.webSockets.dao.repository.ChatRepository;
import com.tulisova.webSockets.dto.ChatDTO;
import com.tulisova.webSockets.mapper.ChatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    private final ChatMapper chatMapper;

    public List<ChatDTO> getChatsByUserId(Long userId) {
        return chatRepository.findAllByParticipantsId(userId).stream()
                .map(chatMapper::toDTO)
                .collect(Collectors.toList());

    }

    public List<Long> findAllUsersByChatId(Long chatId) {
        return chatRepository.findAllUsersIdsByChatId(chatId).stream()
                .map(EntityId::getId)
                .collect(Collectors.toList());
    }

    public Chat getById(Long chatId) {
        return chatRepository.findById(chatId).orElse(null);
    }
}
