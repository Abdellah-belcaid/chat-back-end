package com.chat.service;

import com.chat.model.User;
import com.chat.model.enums.UserStatus;
import com.chat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.chat.utils.Constants.AVATAR_FILE_STORAGE_LOCATION;

@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "users")
public class UserService {

    private final UserRepository userRepository;

    @Cacheable
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Cacheable
    public List<User> findConnectedUsers() {
        return userRepository.findAllByStatus(UserStatus.ONLINE);
    }

    public User authenticateUser(String email, String password) {
        try {
            User user = userRepository.findByEmail(email);
            if (user != null && isPasswordMatch(password, user.getPassword())) {
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred during authentication", e);
        }
    }

    private boolean isPasswordMatch(String rawPassword, String hashedPassword) {
        return Objects.equals(rawPassword, hashedPassword);
    }

    @Cacheable
    public User getUserById(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    @CacheEvict(allEntries = true)
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @CacheEvict(allEntries = true)
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public String uploadAvatar(String userId, MultipartFile file) {
        User user = getUserById(userId);
        String avatarUrl = saveAvatarAndGenerateUrl(userId, file);
        user.setAvatar(avatarUrl);
        userRepository.save(user);
        return avatarUrl;
    }

    public byte[] getAvatarBytes(String filename) throws IOException {
        Path filePath = Paths.get(AVATAR_FILE_STORAGE_LOCATION, filename);
        return Files.readAllBytes(filePath);
    }

    private String saveAvatarAndGenerateUrl(String userId, MultipartFile file) {
        String filename = userId + extractFileExtension(file.getOriginalFilename());
        try {
            Path fileStorageLocation = Paths.get(AVATAR_FILE_STORAGE_LOCATION).toAbsolutePath().normalize();
            if (!Files.exists(fileStorageLocation)) {
                Files.createDirectories(fileStorageLocation);
            }
            Files.copy(file.getInputStream(), fileStorageLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            return ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/users/avatar/").path(filename).toUriString();
        } catch (IOException e) {
            log.error("Unable to save avatar for user id: {}, filename: {}", userId, filename, e);
            throw new RuntimeException("Unable to save avatar");
        }
    }

    private String extractFileExtension(String filename) {
        return Optional.ofNullable(filename).filter(name -> name.contains(".")).map(name -> "." + name.substring(filename.lastIndexOf(".") + 1)).orElse(".png");
    }
}
