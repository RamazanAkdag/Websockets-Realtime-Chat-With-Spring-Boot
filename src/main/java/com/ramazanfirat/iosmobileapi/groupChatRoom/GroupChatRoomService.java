package com.ramazanfirat.iosmobileapi.groupChatRoom;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupChatRoomService implements IGroupChatRoomService {
    
    private final GroupChatRoomRepository groupChatRoomRepository;

     @Override
    public GroupChatRoom createGroupChatRoom(List<String> participantIds) {
        GroupChatRoom groupChatRoom = GroupChatRoom.builder()
            .participtians(participantIds)
            .build();
        groupChatRoomRepository.save(groupChatRoom);
        return groupChatRoom;
    }

    @Override
    public GroupChatRoom findGroupChatRoomById(String roomId) {
        return groupChatRoomRepository.findById(roomId).orElse(null);
    }

    @Override
    public List<GroupChatRoom> findGroupChatRoomsByUserId(String userId) {
        return groupChatRoomRepository.findByParticiptiansContains(userId);
    }

    @Override
    public void addUserToGroupChatRoom(String roomId, String userId) {
        GroupChatRoom room = findGroupChatRoomById(roomId);
        if (room != null && !room.getParticiptians().contains(userId)) {
            room.getParticiptians().add(userId);
            groupChatRoomRepository.save(room);
        }
    } 

    @Override
    public void removeUserFromGroupChatRoom(String roomId, String userId) {
        GroupChatRoom room = findGroupChatRoomById(roomId);
        if (room != null && room.getParticiptians().contains(userId)) {
            room.getParticiptians().remove(userId);
            groupChatRoomRepository.save(room);
        }
    }

    @Override
    public void deleteGroupChatRoom(String roomId) {
        groupChatRoomRepository.deleteById(roomId);
    }
}
