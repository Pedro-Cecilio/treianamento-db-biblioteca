package com.db.biblioteca.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.db.biblioteca.model.Livro;
import com.db.biblioteca.repositories.LivroRepository;
import com.db.biblioteca.service.LivroService;

@Service
public class LivroServiceImpl implements LivroService {
    private LivroRepository livroRepository;

    public LivroServiceImpl(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Override
    public Livro buscarLivroPorId(Long id) {
        return this.livroRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Livro não encontrado."));
    }

    @Override
    public List<Livro> buscarLivrosPorTituloEBibliotecaId(String tutulo, Long bibliotecaId) {
        return this.livroRepository.findAllByTituloAndBibliotecaId(tutulo, bibliotecaId);
    }

    @Override
    public List<Livro> buscarLivrosPorAutorEBibliotecaId(String autor, Long bibliotecaId) {
        return this.livroRepository.findAllByAutorAndBibliotecaId(autor, bibliotecaId);
    }

    @Override
    public List<Livro> buscarLivrosPorAnoPublicacaoEBibliotecaId(Integer anoPublicacao, Long bibliotecaId) {
        return this.livroRepository.findAllByAnoDePublicacaoAndBibliotecaId(anoPublicacao, bibliotecaId);
    }

    @Override
    public List<Livro> buscarTodosLivrosPorBibliotecaId(Long bibliotecaId) {
        return this.livroRepository.findAllByBibliotecaId(bibliotecaId);
    }

    @Override
    public List<Livro> buscarLivrosPorTitulo(String tutulo) {
        return this.livroRepository.findAllByTitulo(tutulo);
    }

    @Override
    public List<Livro> buscarLivrosPorAutor(String autor) {
        return this.livroRepository.findAllByAutor(autor);
    }

    @Override
    public List<Livro> buscarLivrosPorAnoDePublicacao(Integer anoPublicacao) {
        return this.livroRepository.findAllByAnoDePublicacao(anoPublicacao);
    }

}
