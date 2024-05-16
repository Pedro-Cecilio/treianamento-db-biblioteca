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

import com.db.biblioteca.model.Biblioteca;
import com.db.biblioteca.model.Livro;
import com.db.biblioteca.repositories.LivroRepository;
import com.db.biblioteca.repositories.fixture.BibliotecaFixture;
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
        Biblioteca biblioteca = BibliotecaFixture.gerarBiblioteca();
        this.livro = LivroFixture.gerarLivro(biblioteca);
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
        String titulo = livrosComMesmoTitulo.get(0).getTitulo();
        when(this.livroRepository.findAllByTituloAndBibliotecaId(titulo, 1L))
                .thenReturn(livrosComMesmoTitulo);

        List<Livro> resposta = this.livroService.buscarLivrosPorTituloEBibliotecaId(titulo, 1L);

        assertEquals(livrosComMesmoTitulo, resposta);
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia ao não encontrar livros com titulo na biblioteca")
    void deveRetonarListaVaziaAoBuscarLivrosPorTituloNaBiblioteca() {
        List<Livro> livrosComMesmoTitulo = List.of();
        String titulo = "Titulo Inexistente";
        when(this.livroRepository.findAllByTituloAndBibliotecaId(titulo, 1L))
                .thenReturn(livrosComMesmoTitulo);

        List<Livro> resposta = this.livroService.buscarLivrosPorTituloEBibliotecaId(titulo, 1L);

        assertEquals(livrosComMesmoTitulo, resposta);
    }

    @Test
    @DisplayName("Deve ser possível buscar livros por autor em uma biblioteca")
    void deveRetonarLivrosPorAutorNaBiblioteca() {
        List<Livro> livrosComMesmoAutor = LivroFixture.gerarListaDeLivrosMesmoAutor();
        String autor = livrosComMesmoAutor.get(0).getAutor();

        when(this.livroRepository.findAllByAutorAndBibliotecaId(autor, 1L))
                .thenReturn(livrosComMesmoAutor);

        List<Livro> resposta = this.livroService.buscarLivrosPorAutorEBibliotecaId(autor, 1L);

        assertEquals(livrosComMesmoAutor, resposta);
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia ao não encontrar livros com autor na biblioteca")
    void deveRetonarListaVaziaAoBuscarLivrosPorAutorNaBiblioteca() {
        List<Livro> livrosComMesmoAutor = List.of();
        String autor = "Autor inexistente";

        when(this.livroRepository.findAllByAutorAndBibliotecaId(autor, 1L))
                .thenReturn(livrosComMesmoAutor);

        List<Livro> resposta = this.livroService.buscarLivrosPorAutorEBibliotecaId(autor, 1L);

        assertEquals(livrosComMesmoAutor, resposta);
    }

    @Test
    @DisplayName("Deve ser possível buscar livros por ano de publicação em uma biblioteca")
    void deveRetonarLivrosPorAnoDePublicacaoNaBiblioteca() {
        List<Livro> livrosComMesmoAnoDePublicacao = LivroFixture.gerarListaDeLivrosMesmoAnoDePublicacao();
        Integer anoDePublicacao = livrosComMesmoAnoDePublicacao.get(0).getAnoDePublicacao();

        when(this.livroRepository.findAllByAnoDePublicacaoAndBibliotecaId(anoDePublicacao, 1L))
                .thenReturn(livrosComMesmoAnoDePublicacao);

        List<Livro> resposta = this.livroService
                .buscarLivrosPorAnoPublicacaoEBibliotecaId(anoDePublicacao, 1L);

        assertEquals(livrosComMesmoAnoDePublicacao, resposta);
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia ao não encontrar livros com ano de publicação na biblioteca")
    void deveRetonarListaVaziaAoBuscarLivrosPorAnoDePublicacaoNaBiblioteca() {
        List<Livro> livrosComMesmoAnoDePublicacao = List.of();
        Integer anoDePublicacao = 0;
        when(this.livroRepository.findAllByAnoDePublicacaoAndBibliotecaId(anoDePublicacao, 1L))
                .thenReturn(livrosComMesmoAnoDePublicacao);

        List<Livro> resposta = this.livroService
                .buscarLivrosPorAnoPublicacaoEBibliotecaId(anoDePublicacao, 1L);

        assertEquals(livrosComMesmoAnoDePublicacao, resposta);
    }

    @Test
    @DisplayName("Deve ser possível buscar todos livros da biblioteca")
    void deveRetonarTodosLivrosDaBiblioteca() {
        List<Livro> listaDeLivros = LivroFixture.gerarListaDeLivros();

        when(this.livroRepository.findAllByBibliotecaId(1L))
                .thenReturn(listaDeLivros);

        List<Livro> resposta = this.livroService
                .buscarTodosLivrosPorBibliotecaId(1L);

        assertEquals(listaDeLivros, resposta);
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia ao não encontrar livros na biblioteca")
    void deveRetonarListaVaziaAoBuscarLivrosDaBiblioteca() {
        List<Livro> listaDeLivros = List.of();
        when(this.livroRepository.findAllByBibliotecaId(1L))
                .thenReturn(listaDeLivros);

        List<Livro> resposta = this.livroService
                .buscarTodosLivrosPorBibliotecaId(1L);

        assertEquals(listaDeLivros, resposta);
    }

    @Test
    @DisplayName("Deve ser possível buscar todos livros com mesmo titulo, em todas bibliotecas")
    void deveRetonarTodosLivrosComMesmoTitulo() {
        List<Livro> listaDeLivros = LivroFixture.gerarListaDeLivrosMesmoTitulo();
        String titulo = listaDeLivros.get(0).getTitulo();
        when(this.livroRepository.findAllByTitulo(titulo))
                .thenReturn(listaDeLivros);

        List<Livro> resposta = this.livroService
                .buscarLivrosPorTitulo(titulo);

        assertEquals(listaDeLivros, resposta);
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia ao não encontrar livros com titulo informado")
    void deveRetonarListaVaziaAoBuscarLivrosPorTitulo() {
        List<Livro> listaDeLivros = List.of();
        String titulo = "Titulo inexistente";

        when(this.livroRepository.findAllByTitulo(titulo))
                .thenReturn(listaDeLivros);

        List<Livro> resposta = this.livroService
                .buscarLivrosPorTitulo(titulo);

        assertEquals(listaDeLivros, resposta);
    }

    @Test
    @DisplayName("Deve ser possível buscar todos livros com mesmo autor, em todas bibliotecas")
    void deveRetonarTodosLivrosComMesmoAutor() {
        List<Livro> listaDeLivros = LivroFixture.gerarListaDeLivrosMesmoAutor();
        String autor = listaDeLivros.get(0).getAutor();
        when(this.livroRepository.findAllByAutor(autor))
                .thenReturn(listaDeLivros);

        List<Livro> resposta = this.livroService
                .buscarLivrosPorAutor(autor);

        assertEquals(listaDeLivros, resposta);
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia ao não encontrar livros com autor informado")
    void deveRetonarListaVaziaAoBuscarLivrosPorAutor() {
        List<Livro> listaDeLivros = List.of();
        String autor = "Autor inexistente";

        when(this.livroRepository.findAllByAutor(autor))
                .thenReturn(listaDeLivros);

        List<Livro> resposta = this.livroService
                .buscarLivrosPorAutor(autor);

        assertEquals(listaDeLivros, resposta);
    }

    @Test
    @DisplayName("Deve ser possível buscar todos livros com mesmo ano de publicação, em todas bibliotecas")
    void deveRetonarTodosLivrosComMesmoAnoDePublicacao() {
        List<Livro> listaDeLivros = LivroFixture.gerarListaDeLivrosMesmoAnoDePublicacao();
        Integer anoDePublicacao = listaDeLivros.get(0).getAnoDePublicacao();
        when(this.livroRepository.findAllByAnoDePublicacao(anoDePublicacao))
                .thenReturn(listaDeLivros);

        List<Livro> resposta = this.livroService
                .buscarLivrosPorAnoDePublicacao(anoDePublicacao);

        assertEquals(listaDeLivros, resposta);
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia ao não encontrar livros com ano de publicação informado")
    void deveRetonarListaVaziaAoBuscarLivrosPorAnoDePublicacao() {
        List<Livro> listaDeLivros = List.of();
        Integer anoDePublicacao = 0;

        when(this.livroRepository.findAllByAnoDePublicacao(anoDePublicacao))
                .thenReturn(listaDeLivros);

        List<Livro> resposta = this.livroService
                .buscarLivrosPorAnoDePublicacao(anoDePublicacao);

        assertEquals(listaDeLivros, resposta);
    }
}
