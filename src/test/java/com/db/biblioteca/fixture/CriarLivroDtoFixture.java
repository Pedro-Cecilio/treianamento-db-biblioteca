package com.db.biblioteca.fixture;

import com.db.biblioteca.model.dto.CriarLivroDTO;

public class CriarLivroDtoFixture {
    public static CriarLivroDTO gerarCriarLivroDTO(){
        return new CriarLivroDTO("O Senhor dos Anéis", "J.R.R. Tolkien", 1954);
    }

    public static CriarLivroDTO gerarCriarLivroDTOTituloVazio(){
        return new CriarLivroDTO("", "J.R.R. Tolkien", 1954);
    }
    public static CriarLivroDTO gerarCriarLivroDTOAutorVazio(){
        return new CriarLivroDTO("O Senhor dos Anéis", "", 1954);
    }
    public static CriarLivroDTO gerarCriarLivroDTOAnoNulo(){
        return new CriarLivroDTO("O Senhor dos Anéis", "J.R.R. Tolkien", null);
    }
}
