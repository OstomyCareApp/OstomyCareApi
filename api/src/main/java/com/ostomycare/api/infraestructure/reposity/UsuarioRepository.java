package com.ostomycare.api.infraestructure.reposity;

import com.ostomycare.api.infraestructure.entity.UsuarioEntity;
import com.ostomycare.api.infraestructure.enums.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    UserDetails findByEmail(String email);
    List<UsuarioEntity> findByTipoUsuario(TipoUsuario tipoUsuario);
}
