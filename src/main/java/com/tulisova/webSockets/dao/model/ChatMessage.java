package com.tulisova.webSockets.dao.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Entity(name = "chat_message")
public class ChatMessage {

        @Id
        private Long id;

        @ManyToOne
        private Chat chat;

        @ManyToOne
        private User sender;

        @ManyToOne
        private User recipient;

        private String content;

        private LocalDateTime sendDateTime;

        private MessageStatus status;

        private MessageType type;
}
