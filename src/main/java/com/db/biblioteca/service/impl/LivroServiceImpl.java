package com.db.biblioteca.service.impl;

import java.util.List;
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

    @Override
    public List<Livro> buscarLivrosPorTituloEBibliotecaId(String tutulo, Long bibliotecaId) {
       return this.livroRepository.findAllByTituloAndBibliotecaId(tutulo, bibliotecaId);
    }

	@Override
	public List<Livro> buscarLivrosPorAutorEBibliotecaId(String autor, Long bibliotecaId) {
		return this.livroRepository.findAllByAutorAndBibliotecaId(autor, bibliotecaId);
	}

}
