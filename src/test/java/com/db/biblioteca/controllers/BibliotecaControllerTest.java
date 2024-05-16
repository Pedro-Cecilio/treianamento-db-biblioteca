package com.db.biblioteca.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.db.biblioteca.fixture.BibliotecaFixture;
import com.db.biblioteca.fixture.CriarBibliotecaDtoFixture;
import com.db.biblioteca.fixture.CriarLivroDtoFixture;
import com.db.biblioteca.fixture.LivroFixture;
import com.db.biblioteca.model.Biblioteca;
import com.db.biblioteca.model.Livro;
import com.db.biblioteca.model.dto.CriarBibliotecaDTO;
import com.db.biblioteca.model.dto.CriarLivroDTO;
import com.db.biblioteca.repositories.BibliotecaRepository;
import com.db.biblioteca.repositories.LivroRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.stream.Stream;

@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@SpringBootTest
@ActiveProfiles("test")
class BibliotecaControllerTest {
    private MockMvc mockMvc;
    private JacksonTester<CriarBibliotecaDTO> criarBibliotecaDtoJson;
    private JacksonTester<CriarLivroDTO> criarLivroDtoJson;
    private BibliotecaRepository bibliotecaRepository;
    private LivroRepository livroRepository;

    @Autowired
    public BibliotecaControllerTest(
            MockMvc mockMvc,
            JacksonTester<CriarBibliotecaDTO> criarBibliotecaDtoJson,
            JacksonTester<CriarLivroDTO> criarLivroDtoJson,
            BibliotecaRepository bibliotecaRepository,
            LivroRepository livroRepository) {
        this.mockMvc = mockMvc;
        this.criarBibliotecaDtoJson = criarBibliotecaDtoJson;
        this.criarLivroDtoJson = criarLivroDtoJson;
        this.bibliotecaRepository = bibliotecaRepository;
        this.livroRepository = livroRepository;
    }

    @AfterEach
    void tearDown() {
        this.bibliotecaRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve ser possível criar uma biblioteca")
    void deveCriarUmaBiblioteca() throws Exception {
        CriarBibliotecaDTO dto = CriarBibliotecaDtoFixture.gerarCriarBibliotecaDto();
        String json = criarBibliotecaDtoJson.write(dto).getJson();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/bibliotecas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nome").value(dto.getNome()))
                .andExpect(jsonPath("$.livros").isArray());
    }

    @Test
    @DisplayName("Deve falhar ao tentar criar uma biblioteca com nome vazio")
    void deveFalharAoCriarUmaBibliotecaNomeVazio() throws Exception {
        CriarBibliotecaDTO dto = CriarBibliotecaDtoFixture.gerarCriarBibliotecaDtoNomeVazio();
        String json = criarBibliotecaDtoJson.write(dto).getJson();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/bibliotecas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Nome da biblioteca deve ser informado"));
    }

    @Test
    @DisplayName("Deve ser possível adicionar um livro na biblioteca")
    void deveSerPossívelAdicionarUmLivroNaBiblioteca() throws Exception {
        Biblioteca biblioteca = BibliotecaFixture.gerarBiblioteca();
        this.bibliotecaRepository.save(biblioteca);
        CriarLivroDTO dto = CriarLivroDtoFixture.gerarCriarLivroDTO();
        String json = criarLivroDtoJson.write(dto).getJson();

        mockMvc.perform(MockMvcRequestBuilders
                .patch("/bibliotecas/{id}/livro", biblioteca.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Livro adicionado com sucesso!"));
    }

    public static Stream<Arguments> dadosInvalidosCriarLivro() {
        return Stream.of(
                Arguments.of(CriarLivroDtoFixture.gerarCriarLivroDTOTituloVazio(), "Titulo deve ser informado."),
                Arguments.of(CriarLivroDtoFixture.gerarCriarLivroDTOAutorVazio(), "Autor deve ser informado."),
                Arguments.of(CriarLivroDtoFixture.gerarCriarLivroDTOAnoNulo(),
                        "Ano de publicação deve ser informado."));
    }

    @ParameterizedTest
    @MethodSource("dadosInvalidosCriarLivro")
    @DisplayName("Deve falhar ao adicinar livro com dados inválidos")
    void deveFalharAoAdicionarUmLivroComTituloVazio(CriarLivroDTO dto, String mensagemDeErro) throws Exception {
        Biblioteca biblioteca = BibliotecaFixture.gerarBiblioteca();
        this.bibliotecaRepository.save(biblioteca);
        String json = criarLivroDtoJson.write(dto).getJson();

        mockMvc.perform(MockMvcRequestBuilders
                .patch("/bibliotecas/{id}/livro", biblioteca.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(mensagemDeErro));
    }

    @Test
    @DisplayName("Deve falhar ao tentar adicionar livro já existente na biblioteca")
    void deveFalharAoAdicionarUmLivroJaExistente() throws Exception {
        Biblioteca biblioteca = BibliotecaFixture.gerarBiblioteca();
        this.bibliotecaRepository.save(biblioteca);
        Livro livro = LivroFixture.gerarLivro(biblioteca);
        this.livroRepository.save(livro);

        CriarLivroDTO dto = CriarLivroDtoFixture.gerarCriarLivroDTO();
        String json = criarLivroDtoJson.write(dto).getJson();

        mockMvc.perform(MockMvcRequestBuilders
                .patch("/bibliotecas/{id}/livro", biblioteca.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Já possui esse livro na biblioteca"));
    }

    @Test
    @DisplayName("Deve ser possível buscar uma bibliteca por id")
    void deveSerPossivelBuscarUmaBibliotecaPorId() throws Exception {
        Biblioteca biblioteca = BibliotecaFixture.gerarBiblioteca();
        this.bibliotecaRepository.save(biblioteca);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/bibliotecas/{id}", biblioteca.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(biblioteca.getId()))
                .andExpect(jsonPath("$.nome").value(biblioteca.getNome()))
                .andExpect(jsonPath("$.livros").isArray());
    }

    @Test
    @DisplayName("Deve falhar ao tentar buscar uma biblioteca inexistente por id")
    void deveFalharAoBuscarUmaBibliotecaInexistentePorId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/bibliotecas/{id}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Biblioteca não encontrada"));
    }

    @Test
    @DisplayName("Deve ser possível listar todas bibliotecas")
    void deveBuscarTodasBibliotecas() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/bibliotecas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Deve ser possível deletar biblioteca")
    void deveDeletarBiblioteca() throws Exception {
        Biblioteca biblioteca = BibliotecaFixture.gerarBiblioteca();
        this.bibliotecaRepository.save(biblioteca);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/bibliotecas/{id}", biblioteca.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("Biblioteca deletada com sucesso!"));
    }

    @Test
    @DisplayName("Deve falhar ao tentar deletar biblioteca inexistente")
    void deveFalharAoDeletarBiblioteca() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/bibliotecas/{id}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Biblioteca não encontrada"));
    }

    @Test
    @DisplayName("Deve ser possível deletar livro da biblioteca")
    void deveDeletarLivroDaBiblioteca() throws Exception {
        Biblioteca biblioteca = BibliotecaFixture.gerarBiblioteca();
        this.bibliotecaRepository.save(biblioteca);

        Livro livro = LivroFixture.gerarLivro(biblioteca);
        this.livroRepository.save(livro);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/bibliotecas/{bibliotecaId}/livro/{livroId}", biblioteca.getId(), livro.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("Livro deletado com sucesso!"));
    }

    @Test
    @DisplayName("Deve falhar ao deletar livro da biblioteca ao passar livro inexistente")
    void deveFalharAoDeletarLivroDaBibliotecaAoInformarBibliotecaInexistente() throws Exception {
        Biblioteca biblioteca = BibliotecaFixture.gerarBiblioteca();
        this.bibliotecaRepository.save(biblioteca);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/bibliotecas/{bibliotecaId}/livro/{livroId}", biblioteca.getId(), 1L))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Livro não encontrado"));
    }
}
