package com.db.biblioteca.controller;

import org.springframework.web.bind.annotation.RestController;

import com.db.biblioteca.mapper.LivroMapper;
import com.db.biblioteca.model.Livro;
import com.db.biblioteca.model.dto.LivroRespostaDto;
import com.db.biblioteca.service.LivroService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "livro")
public class LivroController {
    private LivroService livroService;
    private LivroMapper livroMapper;

    public LivroController(LivroService livroService, LivroMapper livroMapper) {
        this.livroService = livroService;
        this.livroMapper = livroMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroRespostaDto> buscarLivroPorId(@PathVariable("id") Long id) {
        Livro livro = this.livroService.buscarLivroPorId(id);
        LivroRespostaDto resposta = livroMapper.toLivroRespostaDto(livro);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/titulo-biblioteca")
    public ResponseEntity<List<LivroRespostaDto>> buscarLivrosPorTituloEBibliotecaId(
            @RequestParam(required = true) String titulo,
            @RequestParam(required = true) Long bibliotecaId) {
        List<Livro> livros = this.livroService.buscarLivrosPorTituloEBibliotecaId(titulo, bibliotecaId);
        List<LivroRespostaDto> resposta = livroMapper.toListaLivroRespostaDto(livros);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/autor-biblioteca")
    public ResponseEntity<List<LivroRespostaDto>> buscarLivrosPorAutorEBibliotecaId(
            @RequestParam(required = true) String autor,
            @RequestParam(required = true) Long bibliotecaId) {
        List<Livro> livros = this.livroService.buscarLivrosPorAutorEBibliotecaId(autor, bibliotecaId);
        List<LivroRespostaDto> resposta = livroMapper.toListaLivroRespostaDto(livros);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/anoDePublicacao-biblioteca")
    public ResponseEntity<List<LivroRespostaDto>> buscarLivrosPorAnoDePublicacaoEBibliotecaId(
            @RequestParam(required = true) Integer anoDePublicacao,
            @RequestParam(required = true) Long bibliotecaId) {
        List<Livro> livros = this.livroService.buscarLivrosPorAnoPublicacaoEBibliotecaId(anoDePublicacao, bibliotecaId);
        List<LivroRespostaDto> resposta = livroMapper.toListaLivroRespostaDto(livros);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/biblioteca/{id}")
    public ResponseEntity<List<LivroRespostaDto>> buscarLivrosPorBibliotecaId(
            @PathVariable("id") Long id) {
        List<Livro> livros = this.livroService.buscarTodosLivrosPorBibliotecaId(id);
        List<LivroRespostaDto> resposta = livroMapper.toListaLivroRespostaDto(livros);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/titulo")
    public ResponseEntity<List<LivroRespostaDto>> buscarLivrosPorTitulo(
            @RequestParam(required = true) String titulo) {
        List<Livro> livros = this.livroService.buscarLivrosPorTitulo(titulo);
        List<LivroRespostaDto> resposta = livroMapper.toListaLivroRespostaDto(livros);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/autor")
    public ResponseEntity<List<LivroRespostaDto>> buscarLivrosPorAutor(
            @RequestParam(required = true) String autor) {
        List<Livro> livros = this.livroService.buscarLivrosPorAutor(autor);
        List<LivroRespostaDto> resposta = livroMapper.toListaLivroRespostaDto(livros);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/anoDePublicacao")
    public ResponseEntity<List<LivroRespostaDto>> buscarLivrosPorAnoDePublicacao(
            @RequestParam(required = true) Integer anoDePublicacao) {
        List<Livro> livros = this.livroService.buscarLivrosPorAnoDePublicacao(anoDePublicacao);
        List<LivroRespostaDto> resposta = livroMapper.toListaLivroRespostaDto(livros);
        return ResponseEntity.ok(resposta);
    }
}
