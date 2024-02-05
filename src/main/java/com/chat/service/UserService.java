package com.chat.service;

import com.chat.model.Status;
import com.chat.model.User;
import com.chat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user) {
        //user.setStatus(Status.ONLINE);
        userRepository.save(user);
    }


    public void disconnect(User user) {
       /* var storedUser = userRepository.findById(user.getNickName()).orElse(null);
        if (storedUser != null) {
            //storedUser.setStatus(Status.OFFLINE);
            userRepository.save(storedUser);
        }*/
    }


    public List<User> getAllUsers() {
        // Retrieve all users from the repository
        return userRepository.findAll();
    }
    public User registerUser(User user) {
        // Additional validation logic can be added here if needed
        return userRepository.save(user);
    }
    public List<User> findConnectedUsers() {
        return userRepository.findAllByStatus(Status.ONLINE);
    }

    public User authenticateUser(String email, String password) {
        try {
            // Retrieve the user by email from your database
            User user = userRepository.findByEmail(email);

            // Check if the user exists and if the provided password matches
            if (user != null && isPasswordMatch(password, user.getPassword())) {
                return user;
            } else {
                // Invalid credentials or user not found
                return null;
            }
        } catch (Exception e) {
            // Handle other exceptions (e.g., database connection issue)
            throw new RuntimeException("An error occurred during authentication", e);
        }
    }

    // Helper method to check if the provided password matches the stored hashed password
    private boolean isPasswordMatch(String rawPassword, String hashedPassword) {
       // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //return passwordEncoder.matches(rawPassword, hashedPassword);
        return Objects.equals(rawPassword, hashedPassword);
    }
}