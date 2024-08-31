package com.chat.service;

import com.chat.model.Chat;
import com.chat.model.Message;
import com.chat.repository.ChatRepository;
import com.chat.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@CacheConfig(cacheNames = "messages")
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final ChatService chatService;

    @Cacheable
    public List<Message> getMessagesByChatId(String chatId) {
        return messageRepository.findByChatId(chatId);
    }

    public Message sendMessage(Message message) {
        Message savedMessage = messageRepository.save(message);

        Chat chat = chatRepository.findById(message.getChatId())
                .orElseThrow(() -> new RuntimeException("Chat not found"));
        chat.getMessages().add(savedMessage.getId());
        chatRepository.save(chat);

        return savedMessage;
    }

    @Cacheable
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message updateMessage(String id, Message message) {
        if (!messageRepository.existsById(id)) {
            throw new IllegalArgumentException("Message not found with ID: " + id);
        }
        message.setId(id);
        return messageRepository.save(message);
    }

    @CacheEvict(allEntries = true)
    public void deleteMessage(String id) {
        if (!messageRepository.existsById(id)) {
            throw new IllegalArgumentException("Message not found with ID: " + id);
        }
        messageRepository.deleteById(id);
    }

    @Cacheable
    public List<Message> getMessagesByUserId(String userId) {
        List<Message> userMessages = new ArrayList<>();
        List<Chat> userChats = chatService.getChatsByUserId(userId);

        for (Chat chat : userChats) {
            List<Message> chatMessages = getMessagesByChatId(chat.getId());
            userMessages.addAll(chatMessages);
        }

        return userMessages;
    }
}
