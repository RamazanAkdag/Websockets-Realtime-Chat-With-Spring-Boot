package com.ramazanfirat.iosmobileapi.chat.groupChat;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupChatNotification {
    private String id;
    private String senderId;
    private String groupId;
    private String content;

}
