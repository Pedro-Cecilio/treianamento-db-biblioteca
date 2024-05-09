package com.db.biblioteca.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.biblioteca.mapper.BibliotecaMapper;
import com.db.biblioteca.mapper.LivroMapper;
import com.db.biblioteca.model.Biblioteca;
import com.db.biblioteca.model.Livro;
import com.db.biblioteca.model.dto.BibliotecaRespostaDTO;
import com.db.biblioteca.model.dto.CriarBibliotecaDTO;
import com.db.biblioteca.model.dto.CriarLivroDTO;
import com.db.biblioteca.services.BibliotecaService;

@RestController
@RequestMapping(value = "/bibliotecas")
public class BibliotecaController {

    private BibliotecaMapper bibliotecaMapper;
    private BibliotecaService bibliotecaService;
    private LivroMapper livroMapper;

    public BibliotecaController(BibliotecaMapper bibliotecaMapper, BibliotecaService bibliotecaService,
            LivroMapper livroMapper) {
        this.bibliotecaMapper = bibliotecaMapper;
        this.bibliotecaService = bibliotecaService;
        this.livroMapper = livroMapper;
    }

    @PostMapping
    public BibliotecaRespostaDTO criarBiblioteca(@RequestBody CriarBibliotecaDTO dto) {
        Biblioteca biblioteca = bibliotecaMapper.toBiblioteca(dto);
        this.bibliotecaService.criarBiblioteca(biblioteca);
        return bibliotecaMapper.toBibliotecaRespostaDTO(biblioteca);
    }

    @PatchMapping("/{id}/livro")
    public ResponseEntity<String> adicionarLivro(@RequestBody CriarLivroDTO dto, @PathVariable("id") Long id) {
        Livro livro = livroMapper.toLivro(dto);
        System.out.println(livro.getTitulo());
        System.out.println(livro.getAutor());
        System.out.println(livro.getAnoDePublicacao());
        this.bibliotecaService.adicionarLivroNaBiblioteca(id, livro);
        return ResponseEntity.ok().body("Livro adicionado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BibliotecaRespostaDTO> listarBibliotecaPorId(@PathVariable("id") Long id) {
        Biblioteca biblioteca = this.bibliotecaService.buscarBibliotecaPorId(id);
        BibliotecaRespostaDTO resposta = bibliotecaMapper.toBibliotecaRespostaDTO(biblioteca);
        return ResponseEntity.ok().body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<BibliotecaRespostaDTO>> listarTodasAsBibliotecas() {
        List<Biblioteca> bibliotecas = this.bibliotecaService.buscarTodasBibliotecas();
        List<BibliotecaRespostaDTO> resposta = bibliotecaMapper.toListToBibliotecaRespostaDTO(bibliotecas);
        return ResponseEntity.ok().body(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarBiblioteca(@PathVariable("id") Long id) {
        this.bibliotecaService.removerBibliotecaPorId(id);
        return ResponseEntity.ok().body("Biblioteca deletada com sucesso!");
    }

    @DeleteMapping("/{bibliotecaId}/livro/{livroId}")
    public ResponseEntity<String> deletarLivroDaBiblioteca(@PathVariable("bibliotecaId") Long bibliotecaId,
            @PathVariable("livroId") Long livroId) {
        this.bibliotecaService.removerLivroDaBiblioteca(bibliotecaId, livroId);
        return ResponseEntity.ok().body("Livro deletado com sucesso!");

    }

}