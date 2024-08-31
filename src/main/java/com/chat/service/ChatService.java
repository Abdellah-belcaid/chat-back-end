package com.chat.service;

import com.chat.model.Chat;
import com.chat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "chats")
public class ChatService {

    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Cacheable
    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    @CacheEvict(allEntries = true)
    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }

    @Cacheable
    public List<Chat> getChatsByUserId(String userId) {
        // Retrieve all chats
        List<Chat> allChats = chatRepository.findAll();

        // Filter chats where the user is among the participants
        return allChats.stream()
                .filter(chat -> chat.getParticipants().contains(userId))
                .collect(Collectors.toList());
    }

    // Add other chat-related methods like updateChat, deleteChat, etc.
}
