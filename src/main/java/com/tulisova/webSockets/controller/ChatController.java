package com.tulisova.webSockets.controller;

import com.tulisova.webSockets.dao.model.ChatMessage;
import com.tulisova.webSockets.dao.model.User;
import com.tulisova.webSockets.dto.ChatDTO;
import com.tulisova.webSockets.dto.ChatMessageDTO;
import com.tulisova.webSockets.service.ChatMessageService;
import com.tulisova.webSockets.service.ChatService;
import com.tulisova.webSockets.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class ChatController {

    private final ChatMessageService chatMessageService;
    private final UserService userService;
    private final ChatService chatService;

    /*
    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        chatMessageService.processMessage(chatMessage);
    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public Long countNewMessages(
            @PathVariable String senderId,
            @PathVariable String recipientId) {

        return chatMessageService.countNewMessages(senderId, recipientId);
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public List<ChatMessage> findChatMessages (@PathVariable String senderId,
                                               @PathVariable String recipientId) {
        return chatMessageService.findChatMessages(senderId, recipientId);
    }

    @GetMapping("/messages/{id}")
    public ChatMessage findMessage ( @PathVariable String id) throws Exception {
        return chatMessageService.findById(id);
    }
    */

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model) {
        String userId = (String) request.getSession().getAttribute("userId");

        if (userId == null || userId.isEmpty()) {
            return "redirect:/login";
        }

        model.addAttribute("userId", userId);
        List<ChatDTO> userChats = chatService.getChatsByUserId(Long.getLong(userId));
        model.addAttribute("chats", userChats);

        return "chat_menu";
    }

    @RequestMapping(path = "/chat/{id}", method = RequestMethod.GET)
    public String getMessages(Long chatId, HttpServletRequest request, Model model) {
        long userId = (long) request.getSession().getAttribute("userId");
        List<ChatMessageDTO> chatMessages = chatMessageService.findChatMessages(chatId, userId);
        model.addAttribute("messages", chatMessages);

        return "chat";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request,
                          @RequestParam(defaultValue = "") String login,
                          @RequestParam(defaultValue = "") String password) {
        login = login.trim();

        if (login.isEmpty()) {
            return "login";
        }

        Optional<User> possibleUser = userService.findByLoginAndPassword(login, password);

        if(possibleUser.isPresent()) {
            request.getSession().setAttribute("username", login);
            request.getSession().setAttribute("userId", possibleUser.get().getId());
        }

        return "redirect:/";
    }

    @RequestMapping(path = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession(true).invalidate();

        return "redirect:/login";
    }
}
