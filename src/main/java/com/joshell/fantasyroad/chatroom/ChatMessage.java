package com.joshell.fantasyroad.chatroom;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ChatMessage {

    public enum MessageType{
        CHAT,
        JOIN,
        LEAVE
    }
    private MessageType type;
    private String content;
    private String sender;
}
