package com.db.biblioteca.model.dto;

import jakarta.validation.constraints.NotEmpty;


public class CriarBibliotecaDTO {
    @NotEmpty(message = "Nome da biblioteca deve ser informado")
    private String nome;

    protected CriarBibliotecaDTO(){}

    public CriarBibliotecaDTO(String nome){
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    
}
