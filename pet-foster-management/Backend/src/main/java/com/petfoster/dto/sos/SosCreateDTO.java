package com.petfoster.dto.sos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SosCreateDTO {

    @NotBlank
    private String message;

    private String location;
}
