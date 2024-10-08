package com.chat.repository;

import com.chat.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByChatId(String chatId);
    //List<Message> findByChatId(String chatId);
    // Add custom methods if needed
}
