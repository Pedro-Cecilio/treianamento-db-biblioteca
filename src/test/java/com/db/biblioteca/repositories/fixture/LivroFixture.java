package com.db.biblioteca.repositories.fixture;

import com.db.biblioteca.model.Livro;

public class LivroFixture {
    
    public static Livro gerarLivro(){
        return new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 1954, null);
    }
}
