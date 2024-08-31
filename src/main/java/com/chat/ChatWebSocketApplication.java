package com.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ChatWebSocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatWebSocketApplication.class, args);
	}

}
