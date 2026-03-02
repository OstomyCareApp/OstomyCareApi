package com.ostomycare.api.controller.dto;

import com.ostomycare.api.infraestructure.enums.TipoUsuario;

public record RegisterDTO( String nome,
                           String email,
                           String telefone,
                           String senha,
                           TipoUsuario tipoUsuario,
                           String numeroCoren,
                           String exercicioProfissional) {

}
