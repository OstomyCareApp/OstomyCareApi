package com.ostomycare.api.infraestructure.enums;

public enum TipoUsuario {
    PROFISSIONAL ("profissional"),
    PACIENTE ("paciente");

    private String tipo;

    TipoUsuario(String tipo){
        this.tipo= tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
