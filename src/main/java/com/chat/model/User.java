package com.chat.model;

import com.chat.model.enums.UserStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class User {

    @Id
    private String id;
    private String name;
    private String avatar;
    private String email;
    private String password;
    private UserStatus status;
    private String bio;
    private String lastSeen;
    private List<String> chats;



}
