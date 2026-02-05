package com.petfoster.dto.adoption;

import com.petfoster.enums.AdoptionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdoptionStatusDTO {

    @NotNull
    private AdoptionStatus status;
}
