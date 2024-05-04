package com.ramazanfirat.iosmobileapi.user;



import java.util.List;
import java.util.Optional;

public interface IUserService {

    public void saveUser(User user);

    public void disconnect(User user);


    public List<User> findConnectedUsers();

    public List<User> findAllUsers();

    User findByNickname(String nickname);

    Optional<User> findById(String id);
}
