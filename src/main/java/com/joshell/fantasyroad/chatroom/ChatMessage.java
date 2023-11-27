package com.joshell.fantasyroad.chatroom;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ChatMessage {
    private String content;
    private String sender;
}
