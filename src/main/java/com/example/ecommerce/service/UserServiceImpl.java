// UserServiceImpl.java
package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserDTO;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();  // Convert UserDTO to User
        // Set user properties from userDTO
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());  // Assume password is already hashed
        user.setUsername(userDTO.getUsername());
        user = userRepository.save(user);
        return new UserDTO(user);  // Convert User to UserDTO and return
    }

    @Override
    public User updateUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        // Update user properties from userDTO
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());  // Assume password is already hashed
        user.setUsername(userDTO.getUsername());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public UserDTO getUserById(Long id) {
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO loginUser(String email, String password) {
        return null;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return null;
    }

    @Override
    public UserDTO getUserByName(String name) {
        return null;
    }

    @Override
    public UserDTO uploadUserImage(Long userId, MultipartFile file) {
        return null;
    }

    @Override
    public UserDTO getUserDetails(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDTO(user);  // Convert User to UserDTO and return
    }
}