package com.tulisova.webSockets.controller;

import com.tulisova.webSockets.dao.model.User;
import com.tulisova.webSockets.dto.ChatDTO;
import com.tulisova.webSockets.dto.ChatMessageDTO;
import com.tulisova.webSockets.service.ChatMessageService;
import com.tulisova.webSockets.service.ChatService;
import com.tulisova.webSockets.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
        Long userId = (Long) request.getSession().getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }

        String username = (String) request.getSession().getAttribute("username");

        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        List<ChatDTO> userChats = chatService.getChatsByUserId(userId);
        model.addAttribute("chats", userChats);

        return "chat_menu";
    }

    @RequestMapping(path = "/chat/{id}", method = RequestMethod.GET)
    public String getMessages(@PathVariable("id") Long id, HttpServletRequest request, Model model) {
        long userId = (long) request.getSession().getAttribute("userId");
        String username = (String) request.getSession().getAttribute("username");
        List<ChatMessageDTO> chatMessages = chatMessageService.findChatMessages(id, userId);

        model.addAttribute("messages", chatMessages);
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("chatId", id);

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

        if (login.isEmpty() || password.isEmpty()) {
            return "login";
        }

        Optional<User> possibleUser = userService.findByLoginAndPassword(login, password);

        if(possibleUser.isPresent()) {
            request.getSession().setAttribute("username", possibleUser.get().getFullName());
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
