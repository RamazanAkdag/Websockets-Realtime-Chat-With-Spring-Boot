package com.ramazanfirat.iosmobileapi.groupChatRoom;


import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupChatRoomRepository extends MongoRepository<GroupChatRoom, String> {

    public List<GroupChatRoom> findByParticiptiansContains(String userId);

}
