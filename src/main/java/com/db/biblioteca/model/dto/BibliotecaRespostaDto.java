package com.db.biblioteca.model.dto;

import java.util.List;

public class BibliotecaRespostaDTO {
    private Long id;
    private String nome;

    private List<LivroRespostaDTO> livros;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<LivroRespostaDTO> getLivros() {
        return livros;
    }

    public void setLivros(List<LivroRespostaDTO> livros) {
        this.livros = livros;
    }

    

}
