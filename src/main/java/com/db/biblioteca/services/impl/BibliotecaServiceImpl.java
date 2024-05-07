package com.db.biblioteca.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.db.biblioteca.model.Biblioteca;
import com.db.biblioteca.model.Livro;
import com.db.biblioteca.repositories.BibliotecaRepository;
import com.db.biblioteca.services.BibliotecaService;

@Service
public class BibliotecaServiceImpl implements BibliotecaService {

    private BibliotecaRepository bibliotecaRepository;

    private LivroServiceImpl livroServiceImpl;

    public BibliotecaServiceImpl(BibliotecaRepository bibliotecaRepository, LivroServiceImpl livroServiceImpl) {
        this.bibliotecaRepository = bibliotecaRepository;
        this.livroServiceImpl = livroServiceImpl;
    }

    @Override
    public void adicionarLivroNaBiblioteca(Long bibliotecaId, Livro livro) {
        Biblioteca biblioteca = buscarBibliotecaPorId(bibliotecaId);

        if (verificarSeBibliotecaContemLivro(biblioteca, livro)) {
            throw new IllegalArgumentException("Já possui esse livro na biblioteca");
        }
        livro.setBiblioteca(biblioteca);
        biblioteca.getLivros().add(livro);
        this.bibliotecaRepository.save(biblioteca);
    }

    @Override
    public Biblioteca buscarBibliotecaPorId(Long id) {
        return bibliotecaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Biblioteca não encontrada"));
    }

    @Override
    public boolean verificarSeBibliotecaContemLivro(Biblioteca biblioteca, Livro livro) {
        return biblioteca.getLivros().contains(livro);
    }

    @Override
    public void removerLivroDaBiblioteca(Long bibliotecaId, Long livroId) {
        Livro livro = livroServiceImpl.buscarLivroPorId(livroId);
        Biblioteca biblioteca = buscarBibliotecaPorId(bibliotecaId);
        if (!verificarSeBibliotecaContemLivro(biblioteca, livro)) {
            throw new NoSuchElementException("Biblioteca não contém o livro!");
        }

        biblioteca.getLivros().remove(livro);
        this.bibliotecaRepository.save(biblioteca);
    }

    @Override
    public Biblioteca criarBiblioteca(Biblioteca biblioteca) {
        if (biblioteca == null) {
            throw new IllegalArgumentException("Biblioteca deve ser informada!");
        }
        return bibliotecaRepository.save(biblioteca);
    }

    @Override
    public void removerBibliotecaPorId(Long bibliotecaId) {
        Biblioteca biblioteca = buscarBibliotecaPorId(bibliotecaId);
        this.bibliotecaRepository.delete(biblioteca);
    }

    @Override
    public List<Biblioteca> buscarTodasBibliotecas() {
        return this.bibliotecaRepository.findAll();
    }

}
