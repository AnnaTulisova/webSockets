package com.tulisova.webSockets.dao.repository;

import com.tulisova.webSockets.dao.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findAllByParticipantsIdIn(Long userId);

}
