package com.ramazanfirat.iosmobileapi.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService {

    public void saveUser(User user);

    public void disconnect(User user);


    public List<User> findConnectedUsers();

    public List<User> findAllUsers();
}
