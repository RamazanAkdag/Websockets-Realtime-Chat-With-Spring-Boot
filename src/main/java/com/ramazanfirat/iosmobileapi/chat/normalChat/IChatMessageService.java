package com.ramazanfirat.iosmobileapi.chat.normalChat;

import com.ramazanfirat.iosmobileapi.chat.normalChat.ChatMessage;

import java.util.List;

public interface IChatMessageService {

    public ChatMessage save(ChatMessage chatMessage);

    public List<ChatMessage> findChatMessages(String senderId, String recipientId);
}
