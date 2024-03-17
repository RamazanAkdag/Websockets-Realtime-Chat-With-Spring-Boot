package com.ramazanfirat.iosmobileapi.chat.groupChat;

import com.ramazanfirat.iosmobileapi.chat.groupChat.GroupChatMessage;

import java.util.List;

public interface IGroupChatMessageService {

    public GroupChatMessage save(GroupChatMessage chatMessage);

    public List<GroupChatMessage> findGroupChatMessages(String groupChatId);
}
