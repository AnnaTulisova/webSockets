package com.tulisova.webSockets.service;

import com.tulisova.webSockets.dao.model.Chat;
import com.tulisova.webSockets.dao.model.ChatMessage;
import com.tulisova.webSockets.dao.model.MessageStatus;
import com.tulisova.webSockets.dao.model.User;
import com.tulisova.webSockets.dao.repository.ChatMessageRepository;
import com.tulisova.webSockets.dto.ChatMessageDTO;
import com.tulisova.webSockets.mapper.ChatMessageMapper;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatMessageService {

    private final ChatService chatService;
    private final UserService userService;

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;

    private final ChatMessageMapper chatMessageMapper;

    public String processMessage(ChatMessageDTO chatMessageDTO) {
        ChatMessage saved = save(chatMessageDTO);
        return saved.getSender().getFullName();
    }

    public long countNewMessages(Long senderId, Long recipientId) {
        return chatMessageRepository.countBySenderIdAndRecipientIdAndStatus(
                senderId, recipientId, MessageStatus.RECEIVED);
    }

    public List<ChatMessageDTO> findChatMessages(Long chatId, Long recipientId) {
        List<ChatMessage> messages = chatMessageRepository.findByChatId(chatId);

        if (messages.size() > 0) {
            updateStatuses(chatId, recipientId);
        }

        return messages.stream().map(message -> {
            String fullName = message.getSender().getFullName();
            return chatMessageMapper.toDTO(message).setSenderName(fullName);
        }).collect(Collectors.toList());
    }

    private void updateStatuses(Long chatId, Long recipientId) {
        List<ChatMessage> messages = chatMessageRepository.findByChatIdAndRecipientIdAndStatus(
                chatId,
                recipientId,
                MessageStatus.RECEIVED);

        messages.forEach(message -> message.setStatus(MessageStatus.DELIVERED));
        chatMessageRepository.saveAll(messages);
    }

    @Transactional
    private ChatMessage save(ChatMessageDTO chatMessageDTO) {
        chatMessageDTO.setStatus(MessageStatus.RECEIVED);
        ChatMessage chatMessage = chatMessageMapper.fromDTO(chatMessageDTO);
        User sender = userService.getById(chatMessageDTO.getSenderId());
        Chat chat = chatService.getById(chatMessageDTO.getChatId());

        List<Long> allUsersByChatId = chatService.findAllUsersByChatId(chatMessageDTO.getChatId());
        allUsersByChatId.remove(chatMessageDTO.getSenderId());

        chatMessage.setSender(sender)
                    .setChat(chat)
                    .setRecipient(userService.getById(allUsersByChatId.get(0)))
                    .setSendDateTime(LocalDateTime.now())
                    .setStatus(MessageStatus.RECEIVED);

        return chatMessageRepository.save(chatMessage);
    }

    public ChatMessage findById(Long id) throws Exception {
        return chatMessageRepository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return chatMessageRepository.save(chatMessage);
                })
                .orElseThrow(() -> new Exception("can't find message (" + id + ")"));
    }
}
