package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserDTO;
import com.example.ecommerce.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    User updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    UserDTO registerUser(UserDTO userDTO);

    UserDTO loginUser(String email, String password);

    UserDTO getUserByEmail(String email);

    UserDTO getUserByName(String name);

    UserDTO uploadUserImage(Long userId, MultipartFile file);


    UserDTO getUserDetails(Long userId);
}
