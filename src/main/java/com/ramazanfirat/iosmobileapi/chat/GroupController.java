
package com.ramazanfirat.iosmobileapi.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramazanfirat.iosmobileapi.chat.groupChat.GroupChatMessage;
import com.ramazanfirat.iosmobileapi.chat.groupChat.IGroupChatMessageService;
import com.ramazanfirat.iosmobileapi.groupChatRoom.GroupChatRoom;
import com.ramazanfirat.iosmobileapi.groupChatRoom.IGroupChatRoomService;
import java.util.List;
import java.util.logging.Logger;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RestController
@RequestMapping("/groupChat")
@RequiredArgsConstructor
public class GroupController {


  Logger logger = Logger.getLogger(GroupController.class.getName());

  private final IGroupChatRoomService groupChatRoomService;
  private final IGroupChatMessageService GroupChatMessageService;

  @MessageMapping("/groupChat.createRoom")
  @SendTo("/groupChats")  // Tüm grup sohbetlerini dinleyen client'lara gönderilir
  public GroupChatRoom createRoom(@Payload GroupChatRoom groupChatRoom) {
    System.out.println("create room");
    return groupChatRoomService.createGroupChatRoom(groupChatRoom);
  }

  @MessageMapping("/groupChat.joinRoom")
  @SendTo("/user")  // İlgili kullanıcıya gönderilir
  public void joinRoom(@Payload String roomId, @Payload String userId) {
    groupChatRoomService.addUserToGroupChatRoom(roomId, userId);
  }

  @MessageMapping("/groupChat.leaveRoom")
  @SendTo("/groupChats")  // Tüm grup sohbetlerini dinleyen client'lara gönderilir
  public void leaveRoom(@Payload String roomId, @Payload String userId) {
    groupChatRoomService.removeUserFromGroupChatRoom(roomId, userId);
  }

  @MessageMapping("/groupChat.deleteRoom")
  @SendTo("/groupChats")  // Tüm grup sohbetlerini dinleyen client'lara gönderilir
  public void deleteRoom(@Payload String roomId) {
    groupChatRoomService.deleteGroupChatRoom(roomId);
  }

  @PostMapping("/fetchGroupChatHistory/{roomId}")
  public List<GroupChatMessage> fetchGroupChatHistory(@PathVariable String roomId) {
    var messages = GroupChatMessageService.findGroupChatMessages(roomId);
    System.out.println(messages);
    return messages;
  }

  @PostMapping("/fetchJoinedGroups/{userId}")
  public List<GroupChatRoom> fetchJoinedGroups(@PathVariable String userId) {
    var list = groupChatRoomService.findGroupChatRoomsByUserId(userId);


    return list;
  }
}
