package com.petfoster.dto.volunteer;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventCreateDTO {

    @NotBlank
    private String title;

    private String description;

    private LocalDateTime eventDate;

    private String eventTime;

    private String venue;
}
