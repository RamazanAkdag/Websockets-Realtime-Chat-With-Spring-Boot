package com.ramazanfirat.iosmobileapi.chat.groupChat;

import com.ramazanfirat.iosmobileapi.groupChatRoom.GroupChatRoom;
import com.ramazanfirat.iosmobileapi.groupChatRoom.IGroupChatRoomService;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupChatMessageService implements IGroupChatMessageService{

    private final GroupChatMessageRepository groupChatMessageRepository;
    
    private final IGroupChatRoomService groupChatRoomService;

    @Override
  public GroupChatMessage save(GroupChatMessage groupChatMessage) {
    GroupChatRoom groupChatRoom = groupChatRoomService.findGroupChatRoomById(groupChatMessage.getGroupId());
    if (groupChatRoom != null) {
      groupChatMessage.setTimestamp(new Date());
      groupChatMessageRepository.save(groupChatMessage);
      return groupChatMessage;
    } else {
      return null;
    }
  }

  @Override
  public List<GroupChatMessage> findGroupChatMessages(String groupChatId) {
    return groupChatMessageRepository.findByGroupIdOrderByTimestampAsc(groupChatId);
  }
}
