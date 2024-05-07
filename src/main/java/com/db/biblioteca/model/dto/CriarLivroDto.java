package com.db.biblioteca.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CriarLivroDto {
    @NotEmpty(message = "Titulo deve ser informado")
    private String titulo;

    @NotEmpty(message = "Autor deve ser informado")
    private String autor;

    @NotNull(message = "Ano de publicação deve ser informado")
    private Integer anoDePublicacao;
}
