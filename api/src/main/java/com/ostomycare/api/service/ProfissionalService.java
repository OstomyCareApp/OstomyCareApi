package com.ostomycare.api.service;

import com.ostomycare.api.controller.dto.ProfissionalDTO;
import com.ostomycare.api.infraestructure.enums.TipoUsuario;
import com.ostomycare.api.infraestructure.reposity.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfissionalService {

    private final UsuarioRepository usuarioRepository;

    public ProfissionalService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<ProfissionalDTO> listarProfissionais() {

        return usuarioRepository
                .findByTipoUsuario(TipoUsuario.PROFISSIONAL)
                .stream()
                .map(usuario -> new ProfissionalDTO(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getTelefone(),
                        usuario.getNumeroCoren(),
                        usuario.getExercicioProfissional()
                ))
                .toList();
    }
}