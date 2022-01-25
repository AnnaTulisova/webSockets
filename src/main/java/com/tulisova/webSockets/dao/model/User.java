package com.tulisova.webSockets.dao.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "users")
@Data
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    private String firstName;

    private String lastName;

    private String login;

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
