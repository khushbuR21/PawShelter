package com.petfoster.service;

import com.petfoster.dto.auth.LoginDTO;
import com.petfoster.modelDTO.UserDTO;
import com.petfoster.dto.auth.JwtResponseDTO;

public interface AuthService {
    JwtResponseDTO login(LoginDTO dto);
    UserDTO register(UserDTO userDTO);
}
