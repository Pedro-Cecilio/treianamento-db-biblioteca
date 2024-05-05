package com.db.biblioteca.service.impl;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.db.biblioteca.model.Biblioteca;
import com.db.biblioteca.model.Livro;
import com.db.biblioteca.repositories.BibliotecaRepository;
import com.db.biblioteca.service.BibliotecaService;

@Service
public class BibliotecaServiceImpl implements BibliotecaService {

    private BibliotecaRepository bibliotecaRepository;
    private LivroServiceImpl livroService;

    public BibliotecaServiceImpl(BibliotecaRepository bibliotecaRepository, LivroServiceImpl livroService) {
        this.bibliotecaRepository = bibliotecaRepository;
        this.livroService = livroService;
    }

    public boolean adicionarLivroNaBiblioteca(Long bibliotecaId, Long livroId) {
        Livro livro = this.livroService.buscarLivroPorId(livroId);
        Biblioteca biblioteca = this.buscarBibliotecaPorId(bibliotecaId);
        if (!verificarSeLivroPodeSerAdicionadoNaBiblioteca(biblioteca, livro))
            return false;
        return biblioteca.getLivros().add(livro);
    }

    public Biblioteca buscarBibliotecaPorId(Long id) {
        return this.bibliotecaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Biblioteca não encontrada."));
    }

    public boolean verificarSeLivroPodeSerAdicionadoNaBiblioteca(Biblioteca biblioteca, Livro livro) {
        boolean possuiLivro = biblioteca.getLivros().contains(livro);
        return !possuiLivro;
    }
}
