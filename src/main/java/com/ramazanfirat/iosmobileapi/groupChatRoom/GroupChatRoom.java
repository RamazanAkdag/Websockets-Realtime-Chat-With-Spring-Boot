package com.ramazanfirat.iosmobileapi.groupChatRoom;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class GroupChatRoom {
    @Id
    private String id;
    private String name;
    private List<String> participtians;

}
