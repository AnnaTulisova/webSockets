package com.tulisova.webSockets.service;

import com.tulisova.webSockets.dao.model.User;
import com.tulisova.webSockets.dao.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    public User getById(Long senderId) {
        return userRepository.findById(senderId).orElse(null);
    }
}
