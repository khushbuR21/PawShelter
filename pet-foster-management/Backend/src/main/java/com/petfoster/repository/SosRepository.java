package com.petfoster.repository;

import com.petfoster.entity.SosReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SosRepository extends JpaRepository<SosReport, Long> {
}
