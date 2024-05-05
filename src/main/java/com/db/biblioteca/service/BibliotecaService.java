package com.db.biblioteca.service;

import com.db.biblioteca.model.Biblioteca;
import com.db.biblioteca.model.Livro;

public interface BibliotecaService {
    boolean adicionarLivroNaBiblioteca(Long bibliotecaId, Livro livro);

    Biblioteca buscarBibliotecaPorId(Long id);

    boolean verificarSeBibliotecaContemLivro(Biblioteca biblioteca, Livro livro);
    boolean removerLivroDaBiblioteca(Long bibliotecaId, Long livroId);

    // boolean adicionarBiblioteca(Biblioteca biblioteca);
    // boolean removerBibliotecaPorId(Long bibliotecaId);
}
