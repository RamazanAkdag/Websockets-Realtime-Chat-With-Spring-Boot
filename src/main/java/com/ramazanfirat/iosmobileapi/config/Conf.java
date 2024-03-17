package com.ramazanfirat.iosmobileapi.config;

import com.ramazanfirat.iosmobileapi.chat.groupChat.GroupChatMessageRepository;
import com.ramazanfirat.iosmobileapi.chat.groupChat.GroupChatMessageService;
import com.ramazanfirat.iosmobileapi.chat.normalChat.IChatMessageService;
import com.ramazanfirat.iosmobileapi.chat.groupChat.IGroupChatMessageService;
import com.ramazanfirat.iosmobileapi.chat.normalChat.ChatMessageRepository;
import com.ramazanfirat.iosmobileapi.chat.normalChat.ChatMessageService;
import com.ramazanfirat.iosmobileapi.chatRoom.ChatRoomRepository;
import com.ramazanfirat.iosmobileapi.chatRoom.ChatRoomService;
import com.ramazanfirat.iosmobileapi.chatRoom.IChatRoomService;
import com.ramazanfirat.iosmobileapi.groupChatRoom.GroupChatRoomRepository;
import com.ramazanfirat.iosmobileapi.groupChatRoom.GroupChatRoomService;
import com.ramazanfirat.iosmobileapi.groupChatRoom.IGroupChatRoomService;
import com.ramazanfirat.iosmobileapi.user.IUserService;
import com.ramazanfirat.iosmobileapi.user.UserRepository;
import com.ramazanfirat.iosmobileapi.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.ramazanfirat.iosmobileapi")
public class Conf {


    @Bean
    public IUserService userService(UserRepository userRepository){
        return new UserService(userRepository);
    }

    @Bean
    public IChatRoomService chatRoomService(ChatRoomRepository chatRoomRepository){
        return new ChatRoomService(chatRoomRepository);
    }

    @Bean
    public IGroupChatRoomService groupChatRoomService(GroupChatRoomRepository groupChatRoomRepository){
        return new GroupChatRoomService(groupChatRoomRepository);
    }

    @Bean
    public IChatMessageService chatMessageService(ChatMessageRepository chatMessageRepository, ChatRoomRepository chatRoomRepository){
        return new ChatMessageService(chatMessageRepository, chatRoomService(chatRoomRepository));
    }

    @Bean
    public IGroupChatMessageService groupChatMessageService(GroupChatMessageRepository groupChatMessageRepository, GroupChatRoomRepository groupChatRoomRepository){
        return new GroupChatMessageService(groupChatMessageRepository, groupChatRoomService(groupChatRoomRepository));
    }

}
