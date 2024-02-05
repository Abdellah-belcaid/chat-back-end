package com.chat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Chat {

    @Id
    private String id;
    private List<String> participants; // Assuming participant IDs
    private List<String> messages; // Assuming messages IDs
    private boolean isGroupChat;

    // Constructors, getters, and setters
}