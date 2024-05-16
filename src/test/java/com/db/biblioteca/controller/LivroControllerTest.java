package com.db.biblioteca.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.db.biblioteca.fixture.BibliotecaFixture;
import com.db.biblioteca.fixture.LivroFixture;
import com.db.biblioteca.model.Biblioteca;
import com.db.biblioteca.model.Livro;
import com.db.biblioteca.repositories.BibliotecaRepository;
import com.db.biblioteca.repositories.LivroRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class LivroControllerTest {
    private MockMvc mockMvc;
    private BibliotecaRepository bibliotecaRepository;
    private LivroRepository livroRepository;

    @Autowired
    public LivroControllerTest(
            MockMvc mockMvc,
            BibliotecaRepository bibliotecaRepository,
            LivroRepository livroRepository) {
        this.mockMvc = mockMvc;
        this.bibliotecaRepository = bibliotecaRepository;
        this.livroRepository = livroRepository;
    }

    @Test
    @DisplayName("Deve ser possível buscar livro por id")
    void deveBuscarLivroPorId() throws Exception {
        Biblioteca biblioteca = BibliotecaFixture.gerarBiblioteca();
        this.bibliotecaRepository.save(biblioteca);
        Livro livro = LivroFixture.gerarLivro(biblioteca);
        this.livroRepository.save(livro);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/livros/{id}", livro.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(livro.getId()))
                .andExpect(jsonPath("$.titulo").value(livro.getTitulo()))
                .andExpect(jsonPath("$.autor").value(livro.getAutor()))
                .andExpect(jsonPath("$.anoDePublicacao").value(livro.getAnoDePublicacao()));
    }

    @Test
    @DisplayName("Deve falhar ao buscar livro inexistente por id")
    void deveFalharAoBuscarLivroInexistentePorId() throws Exception {
        Biblioteca biblioteca = BibliotecaFixture.gerarBiblioteca();
        this.bibliotecaRepository.save(biblioteca);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/livros/{id}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Livro não encontrado"));
    }

    @Test
    @DisplayName("Deve ser possível buscar livros por titulo e biblioteca")
    void deveBuscarLivroPorTituloEBiblioteca() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/livros/titulo-biblioteca")
                .param("titulo", "titulo")
                .param("bibliotecaId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Deve ser possível buscar livros por autor e biblioteca")
    void deveBuscarLivroPorAutorEBiblioteca() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/livros/autor-biblioteca")
                .param("autor", "autor")
                .param("bibliotecaId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

    }

    @Test
    @DisplayName("Deve ser possível buscar livros por anoDePublicacao e biblioteca")
    void deveBuscarLivroPorAnoDePublicacaoEBiblioteca() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/livros/anoDePublicacao-biblioteca")
                .param("anoDePublicacao", "1500")
                .param("bibliotecaId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Deve ser possível buscar livros por biblioteca id")
    void deveBuscarLivrosPorBibliotecaId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/livros/biblioteca/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Deve ser possível buscar livros por titulo")
    void deveBuscarLivrosPorTitulo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/livros/titulo")
                .param("titulo", "titulo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Deve ser possível buscar livros por autor")
    void deveBuscarLivrosPorAutor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/livros/autor")
                .param("autor", "autor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Deve ser possível buscar livros por anoDePublicacao")
    void deveBuscarLivrosPorAnoDePublicacao() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/livros/anoDePublicacao")
                .param("anoDePublicacao", "1500"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

}
