package com.kruger.vacunacion.entity;

import com.kruger.vacunacion.enums.RolUsuarioEnum;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol_usuario")
    private RolUsuarioEnum rolUsuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RolUsuarioEnum getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(RolUsuarioEnum rolUsuario) {
        this.rolUsuario = rolUsuario;
    }
}
