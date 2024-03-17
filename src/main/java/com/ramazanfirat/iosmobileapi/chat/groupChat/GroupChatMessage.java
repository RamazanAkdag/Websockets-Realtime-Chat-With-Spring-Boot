package com.ramazanfirat.iosmobileapi.chat.groupChat;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class GroupChatMessage {
    @Id
    private String id;
    private String groupId;
    private String senderId;
    private String content;
    private Date timestamp;
}
