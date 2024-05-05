package com.db.biblioteca.service;

import com.db.biblioteca.model.Biblioteca;
import com.db.biblioteca.model.Livro;

public interface BibliotecaService {
    boolean adicionarLivro(Long bibliotecaId, Long livroId);

    Biblioteca buscarBibliotecaPorId(Long id);

    boolean verificarSeLivroPodeSerAdicionadoNaBiblioteca(Biblioteca biblioteca, Livro livro);
}
