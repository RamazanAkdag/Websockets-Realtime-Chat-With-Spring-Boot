package com.ramazanfirat.iosmobileapi.user;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class User {
    @Id
    private String id;

    private String nickname;
    private String fullname;
    private Status status;
    private String passw;
    private String email;
    private List<String> roles;


}
