package com.db.biblioteca.repositories.service;

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

}
