package com.ramazanfirat.iosmobileapi.chat;

import com.ramazanfirat.iosmobileapi.chat.groupChat.GroupChatMessage;
import com.ramazanfirat.iosmobileapi.chat.groupChat.GroupChatNotification;
import com.ramazanfirat.iosmobileapi.chat.normalChat.IChatMessageService;
import com.ramazanfirat.iosmobileapi.chat.groupChat.IGroupChatMessageService;
import com.ramazanfirat.iosmobileapi.chat.normalChat.ChatMessage;
import com.ramazanfirat.iosmobileapi.chat.normalChat.ChatNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final IChatMessageService chatMessageService;
    private final IGroupChatMessageService groupChatMessageService;


    @MessageMapping("/chat")
    public void processMessage(
            @Payload ChatMessage chatMessage
    ){

        ChatMessage savedMsg = chatMessageService.save(chatMessage);

        // nickname/queue/messages
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId() ,
                "/queue/messages",
                ChatNotification.builder()
                        .id(savedMsg.getId())
                        .senderId(savedMsg.getSenderId())
                        .recipientId(savedMsg.getRecipientId())
                        .content(savedMsg.getContent())
                        .build()
        );

    }

   @MessageMapping("/groupChat")
    public void processGroupChatMessage(
        @Payload GroupChatMessage groupChatMessage
    ) {
        GroupChatMessage savedMessage = groupChatMessageService.save(groupChatMessage);

        // Grup sohbet mesajını tüm üyelere göndermek için
        messagingTemplate.convertAndSend("/topic/groupChats." + groupChatMessage.getGroupId(),
            GroupChatNotification.builder()
            .id(savedMessage.getId())
            .senderId(savedMessage.getSenderId())
            .groupId(savedMessage.getGroupId())
            .content(savedMessage.getContent())
            .build());
    }





    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(
            @PathVariable("senderId") String senderId,
            @PathVariable("recipientId") String recipientId
    ){
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId,recipientId));
    }
}
