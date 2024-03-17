package com.ramazanfirat.iosmobileapi.chatRoom;

import java.util.Optional;

public interface IChatRoomService {
    public Optional<String> getChatRoomId(
            String senderId,
            String recipientId,
            boolean createNewRoomIfNotExists
    );



}
