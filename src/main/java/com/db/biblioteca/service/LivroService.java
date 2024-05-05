package com.db.biblioteca.service;

import com.db.biblioteca.model.Livro;

public interface LivroService {
    Livro buscarLivroPorId(Long id);
}
