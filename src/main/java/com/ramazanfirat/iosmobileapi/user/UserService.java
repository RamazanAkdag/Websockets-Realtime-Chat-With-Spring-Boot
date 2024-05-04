package com.ramazanfirat.iosmobileapi.user;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        if(userRepository.existsByNickname(user.getNickname())){
            return;
        }
        userRepository.save(user);
    }

    @Override
    public void disconnect(User user) {

        var storedUser = userRepository.findById(user.getNickname()).orElse(null);

        if (storedUser != null){
            storedUser.setStatus(Status.OFFLINE);
            userRepository.save(storedUser);
        }

    }

    @Override
    public List<User> findConnectedUsers() {
        return userRepository.findAllByStatus(Status.ONLINE);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }


}
