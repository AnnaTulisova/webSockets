package com.tulisova.webSockets.dao.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Accessors(chain = true)
public class User {
    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private String password;

    private LocalDate birthDate;

    @ManyToMany
    @JoinTable(
            name = "user_chats",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "chat_id") }
    )
    private List<Chat> chats;

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
