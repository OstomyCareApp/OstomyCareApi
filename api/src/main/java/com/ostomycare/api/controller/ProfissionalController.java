package com.ostomycare.api.controller;


import com.ostomycare.api.controller.dto.ProfissionalDTO;
import com.ostomycare.api.infraestructure.entity.UsuarioEntity;
import com.ostomycare.api.service.ProfissionalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }
    @GetMapping("/all")
    public List<ProfissionalDTO> listar() {
        return profissionalService.listarProfissionais();
    }

}
