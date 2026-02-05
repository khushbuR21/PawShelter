package com.petfoster.service.impl;
       import java.util.Optional;

// your JPA entity
import org.modelmapper.ModelMapper;     // ModelMapper class

import com.petfoster.entity.User;
import com.petfoster.modelDTO.UserDTO;
import com.petfoster.repository.UserRepository;
import com.petfoster.security.JwtUtil;
import com.petfoster.service.AuthService;
import com.petfoster.dto.auth.LoginDTO;
import com.petfoster.dto.auth.JwtResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtUtil jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    
    public JwtResponseDTO login(LoginDTO dto) {
        // 1. Find user by email
        Optional<User> userOptional = userRepository.findByEmail(dto.getEmail());

        // 2. Check if user exists
        if (userOptional.isEmpty()) {
            return new JwtResponseDTO(null, "User does not exist with this email", null);
        }

        User user = userOptional.get();

        // 3. Verify password
        // matches(rawPasswordFromDTO, encodedPasswordFromDB)
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            return new JwtResponseDTO(null, "Invalid email or password", null);
        }

        // 4. Generate JWT Token
        String token = jwtUtils.generateToken(user.getEmail());

        // 5. Return success response with token and role for frontend routing
        return new JwtResponseDTO(token, "Login successful", user.getRole());
    }

    @Override
    public UserDTO register(UserDTO userDTO) {
    	
    	System.out.println("in service register method");
    	System.out.println(userDTO.getEmail());
        // Check if user already exists
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("User already exists with email: " + userDTO.getEmail());
        }

        // Convert DTO to Entity
        User user = modelMapper.map(userDTO, User.class);

        // Encode password
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        // Save user
        User savedUser = userRepository.save(user);

        // Convert back to DTO and return
        return modelMapper.map(savedUser, UserDTO.class);
    }
}
