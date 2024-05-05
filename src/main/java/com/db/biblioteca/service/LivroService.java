package com.db.biblioteca.service;

import java.util.List;

import com.db.biblioteca.model.Livro;

public interface LivroService {
    Livro buscarLivroPorId(Long id);

    List<Livro> buscarLivrosPorTituloEBibliotecaId(String tutulo, Long bibliotecaId);

    // List<Livro> buscarLivrosPorAutorNaBiblioteca(Long bibliotecaId, String tutulo);

    // List<Livro> buscarLivrosPorAnoPublicacaoNaBiblioteca(Long bibliotecaId, String tutulo);
}
