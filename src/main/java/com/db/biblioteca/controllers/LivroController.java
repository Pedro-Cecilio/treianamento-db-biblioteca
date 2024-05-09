package com.db.biblioteca.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.db.biblioteca.mapper.LivroMapper;
import com.db.biblioteca.model.Livro;
import com.db.biblioteca.model.dto.LivroRespostaDTO;
import com.db.biblioteca.services.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroController {
    private LivroService livroService;
    private LivroMapper livroMapper;

    public LivroController(LivroService livroService, LivroMapper livroMapper) {
        this.livroService = livroService;
        this.livroMapper = livroMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroRespostaDTO> buscarLivroPorId(@PathVariable("id") Long id) {
        Livro livro = this.livroService.buscarLivroPorId(id);
        LivroRespostaDTO resposta = livroMapper.toLivroRespostaDTO(livro);
        return ResponseEntity.ok().body(resposta);
    }

    @GetMapping("/titulo-biblioteca")
    public ResponseEntity<List<LivroRespostaDTO>> buscarLivrosPorTituloEBibliotecaId(
            @RequestParam(required = true) String titulo,
            @RequestParam(required = true) Long bibliotecaId) {
        List<Livro> livros = this.livroService.buscarLivrosPorTituloEBibliotecaId(titulo, bibliotecaId);
        List<LivroRespostaDTO> resposta = livroMapper.toListaDeLivroToRespostaDTO(livros);
        return ResponseEntity.ok().body(resposta);
    }

    @GetMapping("/autor-biblioteca")
    public ResponseEntity<List<LivroRespostaDTO>> buscarLivrosPorAutorEBibliotecaId(
            @RequestParam(required = true) String autor,
            @RequestParam(required = true) Long bibliotecaId) {
        List<Livro> livros = this.livroService.buscarLivrosPorAutorEBibliotecaId(autor, bibliotecaId);
        List<LivroRespostaDTO> resposta = livroMapper.toListaDeLivroToRespostaDTO(livros);
        return ResponseEntity.ok().body(resposta);
    }

    @GetMapping("/anoDePublicacao-biblioteca")
    public ResponseEntity<List<LivroRespostaDTO>> buscarLivrosPorAnoPublicacaoEBibliotecaId(
            @RequestParam(required = true) Integer anoDePublicacao,
            @RequestParam(required = true) Long bibliotecaId) {
        List<Livro> livros = this.livroService.buscarLivrosPorAnoPublicacaoEBibliotecaId(anoDePublicacao, bibliotecaId);
        List<LivroRespostaDTO> resposta = livroMapper.toListaDeLivroToRespostaDTO(livros);
        return ResponseEntity.ok().body(resposta);
    }

    @GetMapping("/biblioteca/{id}")
    public ResponseEntity<List<LivroRespostaDTO>> buscarLivroPorBibliotecaId(@PathVariable("id") Long id) {
        List<Livro> livros = this.livroService.buscarTodosLivrosPorBibliotecaId(id);
        List<LivroRespostaDTO> resposta = livroMapper.toListaDeLivroToRespostaDTO(livros);
        return ResponseEntity.ok().body(resposta);
    }

    @GetMapping("/titulo")
    public ResponseEntity<List<LivroRespostaDTO>> buscarLivroPorTitulo(@RequestParam(required = true) String titulo) {
        List<Livro> livros = this.livroService.buscarLivrosPorTitulo(titulo);
        List<LivroRespostaDTO> resposta = livroMapper.toListaDeLivroToRespostaDTO(livros);
        return ResponseEntity.ok().body(resposta);
    }

    @GetMapping("/autor")
    public ResponseEntity<List<LivroRespostaDTO>> buscarLivroPorAutor(@RequestParam(required = true) String autor) {
        List<Livro> livros = this.livroService.buscarLivrosPorAutor(autor);
        List<LivroRespostaDTO> resposta = livroMapper.toListaDeLivroToRespostaDTO(livros);
        return ResponseEntity.ok().body(resposta);
    }

    @GetMapping("/anoDePublicacao")
    public ResponseEntity<List<LivroRespostaDTO>> buscarLivroPorAnoPublicacao(
            @RequestParam(required = true) Integer anoPublicacao) {
        List<Livro> livros = this.livroService.buscarLivrosPorAnoDePublicacao(anoPublicacao);
        List<LivroRespostaDTO> resposta = livroMapper.toListaDeLivroToRespostaDTO(livros);
        return ResponseEntity.ok().body(resposta);
    }

}