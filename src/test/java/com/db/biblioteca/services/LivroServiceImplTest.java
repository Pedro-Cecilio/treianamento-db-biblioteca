package com.db.biblioteca.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.db.biblioteca.fixture.BibliotecaFixture;
import com.db.biblioteca.fixture.LivroFixture;
import com.db.biblioteca.model.Livro;
import com.db.biblioteca.repositories.LivroRepository;
import com.db.biblioteca.services.impl.LivroServiceImpl;

@ExtendWith(MockitoExtension.class)
class LivroServiceImplTest {

    @InjectMocks
    private LivroServiceImpl livroServiceImpl;

    @Mock
    private LivroRepository livroRepository;

    private Livro livro;

    @BeforeEach
    void setUp() {
        livro = LivroFixture.gerarLivro(BibliotecaFixture.gerarBiblioteca());
    }

    @Test
    @DisplayName("Deve ser possível encontrar livro por id")
    void deveEncontrarLivroPorId() {
        when(this.livroRepository.findById(1L)).thenReturn(Optional.of(livro));

        assertDoesNotThrow(() -> this.livroServiceImpl.buscarLivroPorId(1L));
    }

    @Test
    void naoDeveSerEncontradoLivroPorId() {
        when(this.livroRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> this.livroServiceImpl.buscarLivroPorId(1L));
    }

    @Test
    void deveRetornarLivrosPorTituloNaBiblioteca() {
        List<Livro> livros = LivroFixture.gerarListaDeLivrosMesmoTitulo();

        String titulo = livros.get(0).getTitulo();

        when(this.livroRepository.findAllByTituloAndBibliotecaId(titulo, 1L)).thenReturn(livros);

        List<Livro> resposta = livroServiceImpl.buscarLivrosPorTituloEBibliotecaId(titulo, 1L);
        assertEquals(livros, resposta);
    }

    @Test
    void deveRetornarLivrosPorAutorNaBiblioteca() {
        List<Livro> livros = LivroFixture.gerarListaDeLivrosMesmoAutor();

        String autor = livros.get(0).getAutor();

        when(this.livroRepository.findAllByAutorAndBibliotecaId(autor, 1L)).thenReturn(livros);
        
        List<Livro> resposta = livroServiceImpl.buscarLivrosPorAutorEBibliotecaId(autor, 1L);
        assertEquals(livros, resposta);
    }

    @Test
    void deveRetornarLivrosPorAnoDePublicacaoNaBiblioteca() {
        List<Livro> livros = LivroFixture.gerarListaDeLivrosMesmoAnoDePublicacao();

        Integer anoDePublicacao = livros.get(0).getAnoDePublicacao();

        when(this.livroRepository.findAllByAnoDePublicacaoAndBibliotecaId(anoDePublicacao, 1L)).thenReturn(livros);
        
        List<Livro> resposta = livroServiceImpl.buscarLivrosPorAnoPublicacaoEBibliotecaId(anoDePublicacao, 1L);
        assertEquals(livros, resposta);
    }

    @Test
    void deveRetornarTodosLivrosDaBiblioteca() {
        List<Livro> livros = LivroFixture.gerarListaDeLivros();

        when(this.livroRepository.findAllByBibliotecaId(1L)).thenReturn(livros);
        
        List<Livro> resposta = livroServiceImpl.buscarTodosLivrosPorBibliotecaId(1L);
        assertEquals(livros, resposta);
    }

    @Test
    void deveRetornarTodosLivrosPorTitulo() {
        List<Livro> livros = LivroFixture.gerarListaDeLivrosMesmoTitulo();

        String titulo = livros.get(0).getTitulo();

        when(this.livroRepository.findAllByTitulo(titulo)).thenReturn(livros);
        
        List<Livro> resposta = livroServiceImpl.buscarLivrosPorTitulo(titulo);
        assertEquals(livros, resposta);
    }

    @Test
    void deveRetornarTodosLivrosPorAutor() {
        List<Livro> livros = LivroFixture.gerarListaDeLivrosMesmoAutor();

        String autor = livros.get(0).getAutor();

        when(this.livroRepository.findAllByAutor(autor)).thenReturn(livros);
        
        List<Livro> resposta = livroServiceImpl.buscarLivrosPorAutor(autor);
        assertEquals(livros, resposta);
    }

    @Test
    void deveRetornarTodosLivrosPorAnoDePublicacao() {
        List<Livro> livros = LivroFixture.gerarListaDeLivrosMesmoAnoDePublicacao();

        Integer anoDePublicacao = livros.get(0).getAnoDePublicacao();

        when(this.livroRepository.findAllByAnoDePublicacao(anoDePublicacao)).thenReturn(livros);
        
        List<Livro> resposta = livroServiceImpl.buscarLivrosPorAnoDePublicacao(anoDePublicacao);
        assertEquals(livros, resposta);
    }
}
