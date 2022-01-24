package com.tulisova.webSockets.service;

import com.tulisova.webSockets.dao.repository.ChatMessageRepository;
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
        return chatRepository.findAllByParticipantsIdIn(userId).stream()
                .map(chatMapper::toDTO)
                .collect(Collectors.toList());

    }

}
