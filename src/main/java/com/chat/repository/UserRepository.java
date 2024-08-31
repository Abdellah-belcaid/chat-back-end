package com.chat.repository;


import com.chat.model.User;
import com.chat.model.enums.UserStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByStatus(UserStatus status);

    User findByEmail(String email);
}
