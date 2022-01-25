package com.tulisova.webSockets.dao.model;

import com.tulisova.webSockets.config.jpa.PostgreSQLEnumType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Entity(name = "chat_message")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class ChatMessage {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_message_seq")
        @SequenceGenerator(name = "chat_message_seq", sequenceName = "chat_message_seq", allocationSize = 1)
        private Long id;

        @ManyToOne
        private Chat chat;

        @ManyToOne
        private User sender;

        @ManyToOne
        private User recipient;

        private String content;

        private LocalDateTime sendDateTime;

        @Enumerated(EnumType.STRING)
        @Type(type = "pgsql_enum")
        private MessageStatus status;

        @Enumerated(EnumType.STRING)
        @Type(type = "pgsql_enum")
        private MessageType type;
}
