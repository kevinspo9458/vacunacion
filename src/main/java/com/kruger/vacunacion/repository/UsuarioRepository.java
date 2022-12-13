package com.kruger.vacunacion.repository;

import com.kruger.vacunacion.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findUsuarioByUsername(String username);
}
