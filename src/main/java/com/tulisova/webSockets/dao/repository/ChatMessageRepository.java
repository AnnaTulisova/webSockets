package com.tulisova.webSockets.dao.repository;

import com.tulisova.webSockets.dao.model.ChatMessage;
import com.tulisova.webSockets.dao.model.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    long countBySenderIdAndRecipientIdAndStatus(Long senderId, Long recipientId, MessageStatus received);

    List<ChatMessage> findByChatId(Long chatId);

    List<ChatMessage> findByChatIdAndRecipientIdAndStatus(Long chatId, Long recipientId, MessageStatus received);
}
