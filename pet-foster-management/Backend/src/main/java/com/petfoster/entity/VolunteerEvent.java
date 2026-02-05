package com.petfoster.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "volunteer_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VolunteerEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDateTime eventDate;

    private String eventTime;

    private String venue;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
}
