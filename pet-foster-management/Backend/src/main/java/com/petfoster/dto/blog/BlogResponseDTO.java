package com.petfoster.dto.blog;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BlogResponseDTO {

    private Long id;
    private String title;
    private String content;
    private String authorEmail;
    private LocalDateTime createdAt;
    private LocalDateTime publishedAt;
}
