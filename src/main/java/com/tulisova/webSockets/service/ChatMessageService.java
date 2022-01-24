package com.tulisova.webSockets.service;

import com.tulisova.webSockets.dao.model.ChatMessage;
import com.tulisova.webSockets.dto.ChatDTO;
import com.tulisova.webSockets.dto.ChatMessageDTO;
import com.tulisova.webSockets.dto.ChatNotification;
import com.tulisova.webSockets.dao.model.MessageStatus;
import com.tulisova.webSockets.dao.repository.ChatMessageRepository;
import com.tulisova.webSockets.mapper.ChatMessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;

    private final ChatMessageMapper chatMessageMapper;

    public void processMessage(ChatMessage chatMessage) {
        ChatMessage saved = save(chatMessage);

        messagingTemplate.convertAndSendToUser(
                chatMessage.getChat().getId().toString(),"/queue/messages",
                new ChatNotification()
                        .setId(saved.getId())
                        .setSenderId(saved.getSender().getId())
                        .setSenderName(saved.getSender().getFullName())
        );
    }

    public long countNewMessages(Long senderId, Long recipientId) {
        return chatMessageRepository.countBySenderIdAndRecipientIdAndStatus(
                senderId, recipientId, MessageStatus.RECEIVED);
    }

    public List<ChatMessageDTO> findChatMessages(Long chatId, Long recipientId) {
        List<ChatMessage> messages = chatMessageRepository.findByChatId(chatId);

        if(messages.size() > 0) {
            updateStatuses(chatId, recipientId);
        }

        return messages.stream().map(chatMessageMapper::toDTO).collect(Collectors.toList());
    }

    private void updateStatuses(Long chatId, Long recipientId) {
        List<ChatMessage> messages = chatMessageRepository.findByChatIdAndRecipientIdAndStatus(
                chatId,
                recipientId,
                MessageStatus.RECEIVED);

        messages.forEach(message -> message.setStatus(MessageStatus.DELIVERED));
        chatMessageRepository.saveAll(messages);
    }

    private ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
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
