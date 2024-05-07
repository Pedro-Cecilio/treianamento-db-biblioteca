package com.db.biblioteca.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BibliotecaRespostaDto {
    private Long id;
    private String nome;
    private List<LivroRespostaDto> livros;
}
