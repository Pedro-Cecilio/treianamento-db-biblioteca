package com.db.biblioteca.repositories.fixture;

import java.util.ArrayList;
import java.util.List;

import com.db.biblioteca.model.Livro;

public class LivroFixture {
    
    public static Livro gerarLivro(){
        return new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 1954, null);
    }
    
    public static List<Livro> gerarListaDeLivros() {
        List<Livro> listaDeLivros = new ArrayList<>();

        listaDeLivros.add(new Livro("1984", "George Orwell", 1949, null));
        listaDeLivros.add(new Livro("Admirável Mundo Novo", "Aldous Huxley", 1932, null));
        listaDeLivros.add(new Livro("Dom Quixote", "Miguel de Cervantes", 1605, null));
        listaDeLivros.add(new Livro("Crime e Castigo", "Fiódor Dostoiévski", 1866, null));

        return listaDeLivros;
    }


    public static List<Livro> gerarListaDeLivrosMesmoTitulo() {
        String titulo = "O Senhor dos Anéis";
        List<Livro> listaDeLivros = new ArrayList<>();

        listaDeLivros.add(new Livro(titulo, "Autor 1", 1954, null));
        listaDeLivros.add(new Livro(titulo, "Autor 2", 1955, null));
        listaDeLivros.add(new Livro(titulo, "Autor 3", 1956, null));

        return listaDeLivros;
    }


    public static List<Livro> gerarListaDeLivrosMesmoAutor() {
        String autor = "J.R.R. Tolkien"; 
        List<Livro> listaDeLivros = new ArrayList<>();

        listaDeLivros.add(new Livro("O Senhor dos Anéis", autor, 1954, null));
        listaDeLivros.add(new Livro("A Sociedade do Anel", autor, 1954, null));
        listaDeLivros.add(new Livro("As Duas Torres", autor, 1954, null));
        listaDeLivros.add(new Livro("O Retorno do Rei", autor, 1955, null));

        return listaDeLivros;
    }

    public static List<Livro> gerarListaDeLivrosMesmoAnoDePublicacao() {
        int anoDePublicacao = 1949;
        
        List<Livro> listaDeLivros = new ArrayList<>();
        
        listaDeLivros.add(new Livro("1984", "George Orwell", anoDePublicacao, null));
        listaDeLivros.add(new Livro("Admirável Mundo Novo", "Aldous Huxley", anoDePublicacao, null));
        listaDeLivros.add(new Livro("Fahrenheit 451", "Ray Bradbury", anoDePublicacao, null));

        return listaDeLivros;
    }
}
