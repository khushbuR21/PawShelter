package com.petfoster.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "volunteer_registrations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VolunteerRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private VolunteerEvent event;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    private LocalDateTime registeredAt;
}
