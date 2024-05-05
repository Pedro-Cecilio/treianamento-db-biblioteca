package com.db.biblioteca.repositories.service;

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
import org.springframework.boot.test.context.SpringBootTest;

import com.db.biblioteca.model.Livro;
import com.db.biblioteca.repositories.LivroRepository;
import com.db.biblioteca.repositories.fixture.LivroFixture;
import com.db.biblioteca.service.impl.LivroServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class LivroServiceImplTest {

    @InjectMocks
    private LivroServiceImpl livroService;
    @Mock
    private LivroRepository livroRepository;

    private Livro livro;

    @BeforeEach
    void configurar() {
        this.livro = LivroFixture.gerarLivro();
    }

    @Test
    @DisplayName("Deve ser possível buscar um livro por id, ao passar um id existente")
    void deveBuscarLivroExistentePorId() {
        when(this.livroRepository.findById(1L)).thenReturn(Optional.of(this.livro));
        assertDoesNotThrow(() -> this.livroService.buscarLivroPorId(1L));
    }

    @Test
    @DisplayName("Deve falhar ao buscar um livro por id, ao passar um id inexistente")
    void deveFalharAoBuscarLivroInexistentePorId() {
        when(this.livroRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> this.livroService.buscarLivroPorId(1L));
    }

    @Test
    @DisplayName("Deve ser possível buscar livros por titulo em uma biblioteca")
    void deveRetonarLivrosPorTituloNaBiblioteca() {
        List<Livro> livrosComMesmoTitulo = LivroFixture.gerarListaDeLivrosMesmoTitulo();
        when(this.livroRepository.findAllByTituloAndBibliotecaId(this.livro.getTitulo(), 1L))
                .thenReturn(livrosComMesmoTitulo);

        List<Livro> resposta = this.livroService.buscarLivrosPorTituloEBibliotecaId(this.livro.getTitulo(), 1L);

        assertEquals(livrosComMesmoTitulo, resposta);
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia ao não encontrar livros com titulo na biblioteca")
    void deveRetonarListaVaziaAoBuscarLivrosPorTituloNaBiblioteca() {
        List<Livro> livrosComMesmoTitulo = List.of();
        when(this.livroRepository.findAllByTituloAndBibliotecaId(this.livro.getTitulo(), 1L))
                .thenReturn(livrosComMesmoTitulo);

        List<Livro> resposta = this.livroService.buscarLivrosPorTituloEBibliotecaId(this.livro.getTitulo(), 1L);

        assertEquals(livrosComMesmoTitulo, resposta);
    }

    @Test
    @DisplayName("Deve ser possível buscar livros por autor em uma biblioteca")
    void deveRetonarLivrosPorAutorNaBiblioteca() {
        List<Livro> livrosComMesmoAutor = LivroFixture.gerarListaDeLivrosMesmoAutor();

        when(this.livroRepository.findAllByAutorAndBibliotecaId(this.livro.getAutor(), 1L))
                .thenReturn(livrosComMesmoAutor);

        List<Livro> resposta = this.livroService.buscarLivrosPorAutorEBibliotecaId(this.livro.getAutor(), 1L);

        assertEquals(livrosComMesmoAutor, resposta);
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia ao não encontrar livros com autor na biblioteca")
    void deveRetonarListaVaziaAoBuscarLivrosPorAutorNaBiblioteca() {
        List<Livro> livrosComMesmoAutor = List.of();
        when(this.livroRepository.findAllByAutorAndBibliotecaId(this.livro.getAutor(), 1L))
                .thenReturn(livrosComMesmoAutor);

        List<Livro> resposta = this.livroService.buscarLivrosPorAutorEBibliotecaId(this.livro.getAutor(), 1L);

        assertEquals(livrosComMesmoAutor, resposta);
    }

    @Test
    @DisplayName("Deve ser possível buscar livros por ano de publicação em uma biblioteca")
    void deveRetonarLivrosPorAnoDePublicacaoNaBiblioteca() {
        List<Livro> livrosComMesmoAnoDePublicacao = LivroFixture.gerarListaDeLivrosMesmoAnoDePublicacao();

        when(this.livroRepository.findAllByAnoDePublicacaoAndBibliotecaId(this.livro.getAnoDePublicacao(), 1L))
                .thenReturn(livrosComMesmoAnoDePublicacao);

        List<Livro> resposta = this.livroService.buscarLivrosPorAnoPublicacaoEBibliotecaId(this.livro.getAnoDePublicacao(), 1L);

        assertEquals(livrosComMesmoAnoDePublicacao, resposta);
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia ao não encontrar livros com ano de publicação na biblioteca")
    void deveRetonarListaVaziaAoBuscarLivrosPorAnoDePublicacaoNaBiblioteca() {
        List<Livro> livrosComMesmoAnoDePublicacao = List.of();
        when(this.livroRepository.findAllByAnoDePublicacaoAndBibliotecaId(this.livro.getAnoDePublicacao(), 1L))
                .thenReturn(livrosComMesmoAnoDePublicacao);

        List<Livro> resposta = this.livroService.buscarLivrosPorAnoPublicacaoEBibliotecaId(this.livro.getAnoDePublicacao(), 1L);

        assertEquals(livrosComMesmoAnoDePublicacao, resposta);
    }
}
