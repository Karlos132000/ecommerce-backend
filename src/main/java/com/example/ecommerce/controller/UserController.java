package com.example.ecommerce.controller;

import com.example.ecommerce.config.JWTGenerator;
import com.example.ecommerce.dto.UserDTO;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.ImageStorageService;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;

    @Autowired
    private ImageStorageService imageStorageService;

    private JWTGenerator jwtGenerator;

     Authentication authentication;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JWTGenerator jwtGenerator) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO newUser = userService.createUser(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO registeredUser = userService.registerUser(userDTO);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO) {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getEmail(),
                        userDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        String token = jwtGenerator.generateToken(authentication);

        // Retrieve the authenticated user's details
        UserDTO authenticatedUser = userService.getUserByEmail(userDTO.getEmail());

        // Create the response body with the token and user ID
        Map<String, Object> response = new HashMap<>();
        response.put("accessToken", token);
        response.put("userId", authenticatedUser.getId());

        // Return the response entity with the token and user ID in the body
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }


    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName().equals("anonymousUser")) {
            // Return an error response or prompt for authentication
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication is required");
        }
        String email = authentication.getName(); // The email should be the username set by Spring Security
        System.out.println("Email from Authentication: " + email);
        UserDTO userDTO = userService.getUserByEmail(email);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/upload-profile-image")
    public ResponseEntity<UserDTO> uploadProfileImage(@RequestParam("image") MultipartFile file, Authentication authentication) {
        // Assuming the username is the email, fetch the user ID from the database
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDTO userDTO = userService.uploadUserImage(user.getId(), file);
        return ResponseEntity.ok(userDTO);
    }





}



