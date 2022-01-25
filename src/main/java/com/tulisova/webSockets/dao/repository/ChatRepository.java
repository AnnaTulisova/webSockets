package com.tulisova.webSockets.dao.repository;

import com.tulisova.webSockets.dao.model.Chat;
import com.tulisova.webSockets.dao.projection.EntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findAllByParticipantsId(Long userId);

    @Query(value = "select user_id as id from user_chats where chat_id = :chatId", nativeQuery = true)
    List<EntityId> findAllUsersIdsByChatId(Long chatId);
}
