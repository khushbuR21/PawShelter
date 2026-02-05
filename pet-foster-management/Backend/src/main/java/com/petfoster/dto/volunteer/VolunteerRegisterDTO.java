package com.petfoster.dto.volunteer;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VolunteerRegisterDTO {

    @NotNull
    private Long eventId;
}
