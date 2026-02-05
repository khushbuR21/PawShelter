package com.petfoster.dto.volunteer;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class EventResponseDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private String eventTime;
    private String venue;
    private LocalDateTime createdAt;
}
