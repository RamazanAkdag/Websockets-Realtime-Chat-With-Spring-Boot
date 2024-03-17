
package com.ramazanfirat.iosmobileapi.groupChatRoom;

import com.ramazanfirat.iosmobileapi.chat.groupChat.GroupChatMessage;
import com.ramazanfirat.iosmobileapi.chat.groupChat.GroupChatMessageService;
import com.ramazanfirat.iosmobileapi.chat.groupChat.IGroupChatMessageService;
import com.ramazanfirat.iosmobileapi.groupChatRoom.GroupChatRoom;
import com.ramazanfirat.iosmobileapi.groupChatRoom.IGroupChatRoomService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
public class GroupController {

  private final IGroupChatRoomService groupChatRoomService;
  private final IGroupChatMessageService GroupChatMessageService;

  @MessageMapping("/groupChat.createRoom")
  @SendTo("/groupChats")  // Tüm grup sohbetlerini dinleyen client'lara gönderilir
  public GroupChatRoom createRoom(@Payload List<String> participantIds) {
    return groupChatRoomService.createGroupChatRoom(participantIds);
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

  @MessageMapping("/groupChat.fetchGroupChatHistory")
  @SendTo("/user")  // İlgili kullanıcıya gönderilir
  public List<GroupChatMessage> fetchGroupChatHistory(@Payload String roomId) {
    return GroupChatMessageService.findGroupChatMessages(roomId);
  }

  @MessageMapping("/groupChat.fetchJoinedGroups")
  @SendTo("/user")  // İlgili kullanıcıya gönderilir
  public List<GroupChatRoom> fetchJoinedGroups(@Payload String userId) {
    return groupChatRoomService.findGroupChatRoomsByUserId(userId);
  }
}
