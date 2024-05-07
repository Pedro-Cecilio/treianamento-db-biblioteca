package com.db.biblioteca.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "bibliotecas")
public class Biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @OneToMany(mappedBy = "biblioteca", cascade = { CascadeType.REMOVE,
            CascadeType.PERSIST }, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Livro> livros;

    protected Biblioteca() {
    }

    public Biblioteca(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Livro> getLivros() {
        return livros;
    }
}
