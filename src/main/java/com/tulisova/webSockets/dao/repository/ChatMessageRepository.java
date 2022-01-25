package com.tulisova.webSockets.dao.repository;

import com.tulisova.webSockets.dao.model.ChatMessage;
import com.tulisova.webSockets.dao.model.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    long countBySenderIdAndRecipientIdAndStatus(Long senderId, Long recipientId, MessageStatus received);

    List<ChatMessage> findByChatId(Long chatId);

    List<ChatMessage> findByChatIdAndRecipientIdAndStatus(Long chatId, Long recipientId, MessageStatus received);
}
