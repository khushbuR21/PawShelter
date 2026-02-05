package com.petfoster.service.impl;

import com.petfoster.entity.User;
import com.petfoster.modelDTO.UserDTO;
import com.petfoster.repository.UserRepository;
import com.petfoster.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setAddress(userDTO.getAddress());
        existingUser.setPhone(userDTO.getPhone());

        if(userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        if(userDTO.getRole() != null) {
            existingUser.setRole(userDTO.getRole());
        }

        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
