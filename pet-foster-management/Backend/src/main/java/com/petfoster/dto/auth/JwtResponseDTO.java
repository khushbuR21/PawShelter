package com.petfoster.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class JwtResponseDTO {
    private String token;
    private String message;
    private String role;
}
