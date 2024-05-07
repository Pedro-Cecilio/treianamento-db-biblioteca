package com.db.biblioteca.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;


@Getter
public class CriarBibliotecaDto {
    
    @NotEmpty(message = "Nome da biblioteca deve ser informado")
    String nome;
    
}
