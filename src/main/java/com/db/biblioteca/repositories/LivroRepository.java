package com.db.biblioteca.repositories;

import com.db.biblioteca.model.Livro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findAllByTituloAndBibliotecaId(String titulo, Long bibliotecaId);
    List<Livro> findAllByAutorAndBibliotecaId(String autor, Long bibliotecaId);
    List<Livro> findAllByAnoDePublicacaoAndBibliotecaId(Integer anoDePublicacao, Long bibliotecaId);
    List<Livro> findAllByBibliotecaId(Long bibliotecaId);
    List<Livro> findAllByTitulo(String titulo);
    List<Livro> findAllByAutor(String autor);
    List<Livro> findAllByAnoDePublicacao(Integer anoDePublicacao);
}
