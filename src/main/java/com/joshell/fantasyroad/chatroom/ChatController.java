package com.joshell.fantasyroad.chatroom;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ChatController {
    private final CommandService commandService;

    /**
     * Checks if the chat message is a command, and handles it if so
     * @param chatMessage
     * @return chatMessage
     */
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload  ChatMessage chatMessage){
        if (chatMessage.getContent().startsWith("/")){
            String result = commandService.processCommand(chatMessage.getContent());
            chatMessage.setContent(result);
            return chatMessage;
        }
        return chatMessage;
    }
}
