package com.tulisova.webSockets.dao.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
public class Chat {
    @Id
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "user_chats",
            joinColumns = { @JoinColumn(name = "chat_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<User> participants;
}
