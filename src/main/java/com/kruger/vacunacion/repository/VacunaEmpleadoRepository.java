package com.kruger.vacunacion.repository;

import com.kruger.vacunacion.entity.VacunaEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacunaEmpleadoRepository extends JpaRepository<VacunaEmpleado, Long> {
}
