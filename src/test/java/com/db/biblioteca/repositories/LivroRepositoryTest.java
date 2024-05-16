package com.db.biblioteca.repositories;

import com.db.biblioteca.model.Biblioteca;
import com.db.biblioteca.model.Livro;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @AfterEach
    void tearDown() {
        livroRepository.deleteAll();
        bibliotecaRepository.deleteAll();
    }

    @Test
    void testCreateLivro() {
        Biblioteca biblioteca = new Biblioteca("Biblioteca João XXIII");
        bibliotecaRepository.save(biblioteca);

        Livro livro = new Livro("Código Limpo", "Robert C. Martin", 2008, biblioteca);
        livroRepository.save(livro);

        List<Livro> livros = livroRepository.findAll();
        List<Biblioteca> bibliotecas = bibliotecaRepository.findAll();

        assertEquals(1, livros.size());
        assertEquals(1, bibliotecas.size());
    }

}
