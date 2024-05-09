package com.db.biblioteca.model.dto;

import jakarta.validation.constraints.NotEmpty;

public class CriarBibliotecaDTO {
    @NotEmpty(message = "Nme da biblioteca deve ser informada")
    private String nome;

    public String getNome() {
        return nome;
    }

    
}
