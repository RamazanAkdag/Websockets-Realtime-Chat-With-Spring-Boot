package com.ramazanfirat.iosmobileapi.chat.normalChat;

import com.ramazanfirat.iosmobileapi.chatRoom.IChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService implements IChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final IChatRoomService chatRoomService;


    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        var chatId = chatRoomService.getChatRoomId(
                chatMessage.getSenderId(),
                chatMessage.getRecipientId(),
                true
        ).orElseThrow();

        chatMessage.setChatId(chatId);
        chatMessageRepository.save(chatMessage);


        return chatMessage;
    }

    @Override
    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {

        var chatId = chatRoomService.getChatRoomId(
                senderId,
                recipientId,
                false
        );


        return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }


}
