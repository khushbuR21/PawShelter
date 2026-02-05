package com.petfoster.dto.adoption;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdoptionRequestDTO {

    @NotNull
    private Long petId;
}
