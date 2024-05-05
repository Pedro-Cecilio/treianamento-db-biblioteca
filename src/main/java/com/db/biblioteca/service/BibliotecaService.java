package com.db.biblioteca.service;

import java.util.List;

import com.db.biblioteca.model.Biblioteca;
import com.db.biblioteca.model.Livro;

public interface BibliotecaService {
    boolean adicionarLivroNaBiblioteca(Long bibliotecaId, Livro livro);

    Biblioteca buscarBibliotecaPorId(Long id);

    boolean verificarSeBibliotecaContemLivro(Biblioteca biblioteca, Livro livro);
    boolean removerLivroDaBiblioteca(Long bibliotecaId, Long livroId);

    Biblioteca criarBiblioteca(Biblioteca biblioteca);
    void removerBibliotecaPorId(Long bibliotecaId);
    List<Biblioteca> buscarTodasBibliotecas();
}
