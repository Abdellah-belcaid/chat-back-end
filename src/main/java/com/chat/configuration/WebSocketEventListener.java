/*package com.chat.Configuration;

import com.chat.model.ChatMessage;
import com.chat.model.enums.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageTemplate;

    @EventListener
    public void handleWebSocketDisconnectEvent(SessionDisconnectEvent event) {

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null && !username.isEmpty()) {

            log.info("User Disconnected: {} (Session ID: {})", username, headerAccessor.getSessionId());

            var chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVER)
                    .Sender(username)
                    .build();

//            messageTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }


}
*/