package com.petfoster.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sos_reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SosReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private String location;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;
}
