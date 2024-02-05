package com.chat.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class User {

    @Id
    private String id;
    private String name;
    private String avatar;
    private String email;
    private String password;
    private String status;
    private String bio;
    private String lastSeen;
    private List<String> chats;
}
