package com.kruger.vacunacion.entity;

import com.kruger.vacunacion.enums.EstadoVacunacionEnum;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cedula", length = 10, unique = true)
    private String cedula;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "correo")
    private String correo;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimento;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_vacunacion")
    private EstadoVacunacionEnum estadoVacunacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(mappedBy="empleado", fetch = FetchType.LAZY)
    private List<VacunaEmpleado> vacunasEmpleado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaNacimento() {
        return fechaNacimento;
    }

    public void setFechaNacimento(Date fechaNacimento) {
        this.fechaNacimento = fechaNacimento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public EstadoVacunacionEnum getEstadoVacunacion() {
        return estadoVacunacion;
    }

    public void setEstadoVacunacion(EstadoVacunacionEnum estadoVacunacion) {
        this.estadoVacunacion = estadoVacunacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<VacunaEmpleado> getVacunasEmpleado() {
        return vacunasEmpleado;
    }

    public void setVacunasEmpleado(List<VacunaEmpleado> vacunasEmpleado) {
        this.vacunasEmpleado = vacunasEmpleado;
    }
}
