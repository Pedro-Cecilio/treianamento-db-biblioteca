package com.db.biblioteca.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CriarLivroDTO {
    @NotEmpty(message = "Titulo deve ser informado.")
    private String titulo;
    @NotEmpty(message = "Autor deve ser informado.")
    private String autor;
    @NotNull(message = "Ano de publicação deve ser informado.")
    private Integer anoDePublicacao;

    protected CriarLivroDTO() {
    }

    public CriarLivroDTO(String titulo, String autor, Integer anoDePublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoDePublicacao = anoDePublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public Integer getAnoDePublicacao() {
        return anoDePublicacao;
    }

}
