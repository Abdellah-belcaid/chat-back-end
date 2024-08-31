package com.chat.controller.rest;


import com.chat.model.User;
import com.chat.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserRestController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        // Retrieve and return all users from the UserService
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String id) {
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/avatar/upload")
    public ResponseEntity<String> uploadAvatar(@RequestParam("id") String id, @RequestParam("avatar") MultipartFile avatar) {
        String avatarUrl = userService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().body(avatarUrl);
    }

    @GetMapping(path = "/avatar/{filename}", produces = {IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE})
    public ResponseEntity<byte[]> getAvatar(@PathVariable("filename") String filename) {
        try {
            byte[] avatarBytes = userService.getAvatarBytes(filename);
            return ResponseEntity.ok(avatarBytes);
        } catch (IOException e) {
            // Handle IOException appropriately, e.g., log the error or return a 404 response
            return ResponseEntity.notFound().build();
        }
    }
}
