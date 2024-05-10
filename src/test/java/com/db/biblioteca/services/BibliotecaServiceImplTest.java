package com.db.biblioteca.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
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

    @Test
    @DisplayName("Deve remover um livro")
    void deveRemoverUmLivro() {
        this.biblioteca.getLivros().add(livro);
        when(this.bibliotecaRepository.findById(1L)).thenReturn(Optional.of(this.biblioteca));
        when(this.livroServiceImpl.buscarLivroPorId(1L)).thenReturn(this.livro);

        assertDoesNotThrow(() -> this.bibliotecaServiceImpl.removerLivroDaBiblioteca(1L, 1L));

        verify(this.bibliotecaRepository, times(1)).findById(1L);
        verify(this.livroServiceImpl, times(1)).buscarLivroPorId(1L);
        verify(this.bibliotecaRepository, times(1)).save(this.biblioteca);
    }

    @Test
    @DisplayName("Deve falhar ao remover um livro")
    void deveFalharAoRemoverUmLivro() {
        when(this.bibliotecaRepository.findById(1L)).thenReturn(Optional.of(this.biblioteca));
        when(this.livroServiceImpl.buscarLivroPorId(1L)).thenReturn(this.livro);

        assertThrows(NoSuchElementException.class, () -> this.bibliotecaServiceImpl.removerLivroDaBiblioteca(1L, 1L));

        verify(this.bibliotecaRepository, times(1)).findById(1L);
        verify(this.livroServiceImpl, times(1)).buscarLivroPorId(1L);
        verify(this.bibliotecaRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve criar uma biblioteca")
    void deveCriarUmaBiblioteca() {
        Biblioteca biblioteca = BibliotecaFixture.gerarBiblioteca();

        when(this.bibliotecaRepository.save(any())).thenReturn(biblioteca);

        Biblioteca bibliotecaSalva = this.bibliotecaServiceImpl.criarBiblioteca(biblioteca);

        assertNotNull(bibliotecaSalva);
        assertEquals(biblioteca, bibliotecaSalva);

        verify(this.bibliotecaRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deve remover uma biblioteca por ID")
    void deveRemoverUnaBibliotecaPorId() {
        when(bibliotecaRepository.findById(1L)).thenReturn(Optional.of(biblioteca));

        assertDoesNotThrow(() -> bibliotecaServiceImpl.removerBibliotecaPorId(1L));

        verify(bibliotecaRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve falhar ao remover uma biblioteca por ID ")
    void deveFalharAoRemoverBibliotecaPorId() {
        when(bibliotecaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> bibliotecaServiceImpl.removerBibliotecaPorId(1L));

        verify(bibliotecaRepository, times(1)).findById(1L);

        verify(bibliotecaRepository, never()).delete(any());
    }

    @Test
    @DisplayName("Deve buscar todas as bibliotecas")
    void deveBuscarTodasBibliotecas() {
        List<Biblioteca> bibliotecas = new ArrayList<>();
        bibliotecas.add(biblioteca);

        when(bibliotecaRepository.findAll()).thenReturn(bibliotecas);

        List<Biblioteca> bibliotecasRetornadas = bibliotecaServiceImpl.buscarTodasBibliotecas();

        assertNotNull(bibliotecasRetornadas);

        assertEquals(1, bibliotecasRetornadas.size());
        assertEquals(biblioteca.getNome(), bibliotecasRetornadas.get(0).getNome());

        verify(bibliotecaRepository, times(1)).findAll();
    }
}