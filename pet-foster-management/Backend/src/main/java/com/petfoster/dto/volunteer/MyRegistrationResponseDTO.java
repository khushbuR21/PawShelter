package com.petfoster.dto.volunteer;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MyRegistrationResponseDTO {

    private Long registrationId;
    private Long eventId;
    private String eventTitle;
    private String eventDescription;
    private LocalDateTime eventDate;
    private String eventTime;
    private String venue;
    private LocalDateTime registeredAt;
}
