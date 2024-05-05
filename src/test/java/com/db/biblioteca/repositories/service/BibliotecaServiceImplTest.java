package com.db.biblioteca.repositories.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

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
import com.db.biblioteca.repositories.BibliotecaRepository;
import com.db.biblioteca.repositories.fixture.BibliotecaFixture;
import com.db.biblioteca.repositories.fixture.LivroFixture;
import com.db.biblioteca.service.impl.BibliotecaServiceImpl;
import com.db.biblioteca.service.impl.LivroServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BibliotecaServiceImplTest {
    @InjectMocks
    private BibliotecaServiceImpl bibliotecaService;

    @Mock
    private BibliotecaRepository bibliotecaRepository;

    @Mock
    private LivroServiceImpl livroService;

    private Livro livro;
    private Biblioteca biblioteca;

    @BeforeEach
    void configurar() {
        this.livro = LivroFixture.gerarLivro();
        this.biblioteca = BibliotecaFixture.gerarBiblioteca();
    }

    @Test
    @DisplayName("Deve ser possível adicionar um livro")
    void deveAdicionarLivroCorretamente() {
        when(this.livroService.buscarLivroPorId(1L)).thenReturn(livro);
        when(this.bibliotecaRepository.findById(1L)).thenReturn(Optional.of(biblioteca));

        assertTrue(this.bibliotecaService.adicionarLivro(1L, 1L));
    }

    @Test
    @DisplayName("Deve ser possível adicionar um livro")
    void deveFalharAoAdicionarLivroJaExistente() {
        this.biblioteca.getLivros().add(livro);

        when(this.livroService.buscarLivroPorId(1L)).thenReturn(livro);
        when(this.bibliotecaRepository.findById(1L)).thenReturn(Optional.of(biblioteca));

        assertFalse(this.bibliotecaService.adicionarLivro(1L, 1L));
    }

    @Test
    @DisplayName("Deve ser possível buscar uma biblioteca por id")
    void deveBuscarBibliotecaPorIdCorretamente() {
        when(this.bibliotecaRepository.findById(1L)).thenReturn(Optional.of(biblioteca));

        assertDoesNotThrow(() -> this.bibliotecaService.buscarBibliotecaPorId(1L));
    }

    @Test
    @DisplayName("Deve ser possível buscar uma biblioteca por id")
    void deveFalharAoBuscarBibliotecaInexistentePorId() {
        when(this.bibliotecaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> this.bibliotecaService.buscarBibliotecaPorId(1L));
    }

    @Test
    @DisplayName("Deve retornar true ao verificar se livro pode ser adicionado na biblioteca")
    void deveRetornarTrueAoVerificarSeLivroPodeSerAdicionado() {
        assertTrue(this.bibliotecaService.verificarSeLivroPodeSerAdicionadoNaBiblioteca(biblioteca, livro));
    }
    @Test
    @DisplayName("Deve retornar false ao verificar se livro pode ser adicionado na biblioteca")
    void deveRetornarFalseAoVerificarSeLivroPodeSerAdicionado() {
        this.biblioteca.getLivros().add(livro);

        assertFalse(this.bibliotecaService.verificarSeLivroPodeSerAdicionadoNaBiblioteca(biblioteca, livro));
    }
}
