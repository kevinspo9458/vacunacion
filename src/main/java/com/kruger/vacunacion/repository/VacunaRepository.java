package com.kruger.vacunacion.repository;

import com.kruger.vacunacion.entity.Vacuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacunaRepository extends JpaRepository<Vacuna, Long> {
}
