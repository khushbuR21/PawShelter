package com.petfoster.dto.sos;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SosResponseDTO {

    private Long id;
    private String message;
    private String location;
    private String customerEmail;
    private LocalDateTime createdAt;
}
