package com.chat.controller.rest;


import com.chat.model.Chat;
import com.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chats")
public class ChatRestController {

    private final ChatService chatService;

    @GetMapping
    public ResponseEntity<List<Chat>> getAllChats() {
        List<Chat> chats = chatService.getAllChats();
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Chat>> getChatsByUserId(@PathVariable String userId) {
        List<Chat> chats = chatService.getChatsByUserId(userId);
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Chat> createChat(@RequestBody Chat chat) {
        System.err.println(chat);
        Chat createdChat = chatService.createChat(chat);
        return new ResponseEntity<>(createdChat, HttpStatus.CREATED);
    }

    // Add other endpoints for updating, deleting, etc. chats
}
