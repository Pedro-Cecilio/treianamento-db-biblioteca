package com.db.biblioteca.repositories.fixture;

import com.db.biblioteca.model.Biblioteca;

public class BibliotecaFixture {
    public static Biblioteca gerarBiblioteca(){
        return new Biblioteca("Nossa Biblioteca");
    }
}
