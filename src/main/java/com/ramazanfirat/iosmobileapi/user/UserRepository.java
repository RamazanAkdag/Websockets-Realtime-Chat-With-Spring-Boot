package com.ramazanfirat.iosmobileapi.user;

import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {
    List<User> findAllByStatus(Status status);


    boolean existsByNickname(String nickname);


    User findByNickname(String nickname);
}
