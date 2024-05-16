package com.db.biblioteca.fixture;

import com.db.biblioteca.model.dto.CriarBibliotecaDTO;

public class CriarBibliotecaDtoFixture {
    
    public static CriarBibliotecaDTO gerarCriarBibliotecaDto() {
        return new CriarBibliotecaDTO("Biblioteca Central");
    }
    public static CriarBibliotecaDTO gerarCriarBibliotecaDtoNomeVazio() {
        return new CriarBibliotecaDTO("");
    }
}
