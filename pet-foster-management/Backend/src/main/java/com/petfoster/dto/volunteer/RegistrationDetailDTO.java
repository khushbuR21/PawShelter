package com.petfoster.dto.volunteer;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class RegistrationDetailDTO {

    private Long registrationId;
    private String customerName;
    private String customerEmail;
    private LocalDateTime registeredAt;
}
