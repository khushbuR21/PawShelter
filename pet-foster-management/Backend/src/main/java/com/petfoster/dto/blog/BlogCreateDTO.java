package com.petfoster.dto.blog;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogCreateDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
