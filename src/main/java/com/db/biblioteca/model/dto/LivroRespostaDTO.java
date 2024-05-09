package com.db.biblioteca.model.dto;

public class LivroRespostaDTO {
    private Long id;
    private String titulo;
    private String autor;
    private Integer anoDePublicacao;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public Integer getAnoDePublicacao() {
        return anoDePublicacao;
    }
    public void setAnoDePublicacao(Integer anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    
}
