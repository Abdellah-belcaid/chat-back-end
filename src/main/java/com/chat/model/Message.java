package com.chat.model;

import com.chat.model.enums.MessageStatus;
import com.chat.model.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Message {

    @Id
    private String id;
    private String sender; // Assuming user ID
    private String content;
    private MessageType type;
    private MessageStatus status;
    private Date timestamp;
    private String chatId;

}