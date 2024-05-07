package com.db.biblioteca.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroRespostaDto {
    private Long id;
    private String titulo;
    private String autor;
    private Integer anoDePublicacao;
}
