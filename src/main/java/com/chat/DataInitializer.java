package com.chat;

import com.chat.model.User;
import com.chat.model.enums.UserStatus;
import com.chat.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        // Save some initial users

        User user1 = User.builder().id(UUID.randomUUID().toString()).name("John Doe").email("john@example.com").password("johns_password123").status(UserStatus.ONLINE).bio("Hello, I'm John!").avatar("").chats(List.of(new String[0])).build();

        User user2 = User.builder().id(UUID.randomUUID().toString()).name("Alice Johnson").email("alice@example.com").password("alices_password456").status(UserStatus.ONLINE).bio("Nice to meet you!").avatar("").chats(List.of(new String[0])).build();

        User user3 = User.builder().id(UUID.randomUUID().toString()).name("Bob Smith").email("bob@example.com").password("bobs_password789").status(UserStatus.ONLINE).bio("Greetings!").avatar("").chats(List.of(new String[0])).build();

        User user4 = User.builder().id(UUID.randomUUID().toString()).name("Eva Davis").email("eva@example.com").password("evas_password101").status(UserStatus.ONLINE).bio("Hi there!").avatar("").chats(List.of(new String[0])).build();

        User user5 = User.builder().id(UUID.randomUUID().toString()).name("Grace Lee").email("grace@example.com").password("graces_password112").status(UserStatus.ONLINE).bio("Hello from Grace!").avatar("").chats(List.of(new String[0])).build();


       // userRepository.saveAll(List.of(user1, user2, user3, user4, user5));
    }
}
