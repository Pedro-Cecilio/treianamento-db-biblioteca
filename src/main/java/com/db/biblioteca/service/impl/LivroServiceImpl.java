package com.db.biblioteca.service.impl;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.db.biblioteca.model.Livro;
import com.db.biblioteca.repositories.LivroRepository;
import com.db.biblioteca.service.LivroService;

@Service
public class LivroServiceImpl implements LivroService{
    private LivroRepository livroRepository;

    public LivroServiceImpl(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro buscarLivroPorId(Long id) {
        return this.livroRepository.findById(id).orElseThrow(()->new NoSuchElementException("Livro não encontrado."));
    }
}
