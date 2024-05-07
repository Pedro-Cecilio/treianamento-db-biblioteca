package com.db.biblioteca.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.db.biblioteca.fixture.BibliotecaFixture;
import com.db.biblioteca.fixture.LivroFixture;
import com.db.biblioteca.model.Biblioteca;
import com.db.biblioteca.model.Livro;
import com.db.biblioteca.repositories.BibliotecaRepository;
import com.db.biblioteca.services.impl.BibliotecaServiceImpl;
import com.db.biblioteca.services.impl.LivroServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BibliotecaServiceImplTest {

    @InjectMocks
    private BibliotecaServiceImpl bibliotecaServiceImpl;

    @Mock
    private BibliotecaRepository bibliotecaRepository;

    @Mock
    private LivroServiceImpl livroServiceImpl;

    private Livro livro;

    private Biblioteca biblioteca;

    @BeforeEach
    void setUp() {
        this.biblioteca = BibliotecaFixture.gerarBiblioteca();
        this.livro = LivroFixture.gerarLivro(biblioteca);
    }

    @Test
    @DisplayName("Deve ser possível adicionar um livro")
    void deveAdicionarLivroNaBiblioteca() {
        when(this.bibliotecaRepository.findById(1L)).thenReturn(Optional.of(this.biblioteca));

        assertDoesNotThrow(() -> this.bibliotecaServiceImpl.adicionarLivroNaBiblioteca(1L, this.livro));
    }

    @Test
    @DisplayName("Deve falhar ao adicionar um livro já adicionado")
    void deveFalharAoAdicionarLivroNaBiblioteca() {
        this.biblioteca.getLivros().add(livro);
        when(this.bibliotecaRepository.findById(1L)).thenReturn(Optional.of(this.biblioteca));

        assertThrows(IllegalArgumentException.class,
                () -> this.bibliotecaServiceImpl.adicionarLivroNaBiblioteca(1L, this.livro));
    }

    @Test
    @DisplayName("Deve buscar biblioteca por id")
    void deveBuscarBibliotecaPorId() {
        when(this.bibliotecaRepository.findById(1L)).thenReturn(Optional.of(this.biblioteca));
        assertDoesNotThrow(() -> this.bibliotecaServiceImpl.buscarBibliotecaPorId(1L));
    }

    @Test
    @DisplayName("Deve falhar ao buscar biblioteca por id")
    void deveFalharAoBuscarBibliotecaPorId() {
        assertThrows(NoSuchElementException.class, () -> this.bibliotecaServiceImpl.buscarBibliotecaPorId(1L));
    }
}
