package com.joshell.fantasyroad.chatroom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatRoom {
    private String roomId;
    private String hostUsername;
    private Map<String, WebSocketSession>  participants = new ConcurrentHashMap<>();
}
