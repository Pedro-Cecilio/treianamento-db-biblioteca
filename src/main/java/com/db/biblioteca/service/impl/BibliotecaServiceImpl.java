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

    public boolean adicionarLivroNaBiblioteca(Long bibliotecaId, Livro livro) {
        Biblioteca biblioteca = this.buscarBibliotecaPorId(bibliotecaId);
        if (livro == null || verificarSeBibliotecaContemLivro(biblioteca, livro))
            return false;
        livro.setBiblioteca(biblioteca);
        return biblioteca.getLivros().add(livro);
    }

    public Biblioteca buscarBibliotecaPorId(Long id) {
        return this.bibliotecaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Biblioteca não encontrada."));
    }

    @Override
    public boolean verificarSeBibliotecaContemLivro(Biblioteca biblioteca, Livro livro) {
        return biblioteca.getLivros().contains(livro);
    }

    @Override
    public boolean removerLivroDaBiblioteca(Long bibliotecaId, Long livroId) {
        Livro livro = this.livroService.buscarLivroPorId(livroId);
        Biblioteca biblioteca = this.buscarBibliotecaPorId(bibliotecaId);
        if (!verificarSeBibliotecaContemLivro(biblioteca, livro))
            return false;
        biblioteca.getLivros().remove(livro);
        this.bibliotecaRepository.save(biblioteca);
        return true;
    }

    @Override
    public Biblioteca criarBiblioteca(Biblioteca biblioteca) {
        if (biblioteca == null)
            throw new IllegalArgumentException("Biblioteca deve ser informada");

        return this.bibliotecaRepository.save(biblioteca);
    }

    @Override
    public void removerBibliotecaPorId(Long bibliotecaId) {
        Biblioteca biblioteca = this.buscarBibliotecaPorId(bibliotecaId);
        this.bibliotecaRepository.delete(biblioteca);
    }
}
