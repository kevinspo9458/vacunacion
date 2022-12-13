package com.kruger.vacunacion.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vacuna_empleado")
public class VacunaEmpleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_vacunacion")
    @Temporal(TemporalType.DATE)
    private Date fechaVacunacion;

    @Column(name = "numero_dosis")
    private Integer numeroDosis;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="empleado_id", referencedColumnName = "id", nullable=false)
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="vacuna_id", referencedColumnName = "id", nullable=false)
    private Vacuna vacuna;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaVacunacion() {
        return fechaVacunacion;
    }

    public void setFechaVacunacion(Date fechaVacunacion) {
        this.fechaVacunacion = fechaVacunacion;
    }

    public Integer getNumeroDosis() {
        return numeroDosis;
    }

    public void setNumeroDosis(Integer numeroDosis) {
        this.numeroDosis = numeroDosis;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Vacuna getVacuna() {
        return vacuna;
    }

    public void setVacuna(Vacuna vacuna) {
        this.vacuna = vacuna;
    }
}
