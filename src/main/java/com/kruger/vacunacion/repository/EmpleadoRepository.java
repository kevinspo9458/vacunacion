package com.kruger.vacunacion.repository;

import com.kruger.vacunacion.entity.Empleado;
import com.kruger.vacunacion.enums.EstadoVacunacionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    List<Empleado> findByEstadoVacunacion(EstadoVacunacionEnum estadoVacunacion);

    @Query("SELECT o FROM Empleado o JOIN o.vacunasEmpleado ve JOIN ve.vacuna v WHERE v.id = :idVacuna")
    List<Empleado> findByIdVacunaEmpleado(Long idVacuna);

    @Query("SELECT o FROM Empleado o JOIN o.vacunasEmpleado ve WHERE ve.fechaVacunacion BETWEEN :fechaInicio AND :fechaFin")
    List<Empleado> findByFechaVacunaEmpleado(Date fechaInicio, Date fechaFin);
}
