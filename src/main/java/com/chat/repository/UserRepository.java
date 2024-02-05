package com.chat.repository;


import com.chat.model.Status;
import com.chat.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByStatus(Status status);
    User findByEmail(String email);
}
