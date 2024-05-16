package com.db.biblioteca.repositories.fixture;

import java.util.ArrayList;
import java.util.List;

import com.db.biblioteca.model.Biblioteca;

public class BibliotecaFixture {
    public static Biblioteca gerarBiblioteca(){
        return new Biblioteca("Nossa Biblioteca");
    }
    public static List<Biblioteca> gerarListaDeBibliotecas() {
        List<Biblioteca> listaDeBibliotecas = new ArrayList<>();
        
        listaDeBibliotecas.add(new Biblioteca("Biblioteca Central"));
        listaDeBibliotecas.add(new Biblioteca("Biblioteca Municipal"));
        listaDeBibliotecas.add(new Biblioteca("Biblioteca Universitária"));

        return listaDeBibliotecas;
    }
}
