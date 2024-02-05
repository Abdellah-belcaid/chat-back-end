package com.chat.model;

import com.chat.model.enums.MessageStatus;
import com.chat.model.enums.MessageType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Message {

    @Id
    private String id;
    private String sender; // Assuming user ID
    private String content;
    private MessageType type;
    private MessageStatus status;
    private Date timestamp;


}