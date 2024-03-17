package com.ramazanfirat.iosmobileapi.chat.groupChat;

import com.ramazanfirat.iosmobileapi.chat.groupChat.GroupChatMessage;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupChatMessageRepository extends MongoRepository<GroupChatMessage,String> {

    public List<GroupChatMessage> findByGroupIdOrderByTimestampAsc(String groupChatId);
}
