package com.ostomycare.api.controller.dto;

public record ProfissionalDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String numeroCoren,
        String exercicioProfissional
) {}