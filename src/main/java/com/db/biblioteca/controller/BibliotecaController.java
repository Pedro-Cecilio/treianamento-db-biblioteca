package com.db.biblioteca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.biblioteca.mapper.BibliotecaMapper;
import com.db.biblioteca.mapper.LivroMapper;
import com.db.biblioteca.model.Biblioteca;
import com.db.biblioteca.model.Livro;
import com.db.biblioteca.model.dto.BibliotecaRespostaDto;
import com.db.biblioteca.model.dto.CriarBibliotecaDto;
import com.db.biblioteca.model.dto.CriarLivroDto;
import com.db.biblioteca.service.BibliotecaService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping(value = "/biblioteca")
public class BibliotecaController {
    private BibliotecaService bibliotecaService;
    private BibliotecaMapper bibliotecaMapper;
    private LivroMapper livroMapper;
    public BibliotecaController(BibliotecaService bibliotecaService, BibliotecaMapper bibliotecaMapper, LivroMapper livroMapper){
        this.bibliotecaService = bibliotecaService;
        this.bibliotecaMapper = bibliotecaMapper;
        this.livroMapper = livroMapper;
    }
    
    @PostMapping
    public BibliotecaRespostaDto criarBiblioteca(@RequestBody CriarBibliotecaDto dto) {
        Biblioteca biblioteca = bibliotecaMapper.toBiblioteca(dto);
        this.bibliotecaService.criarBiblioteca(biblioteca);
        return bibliotecaMapper.toBibliotecaRespostaDto(biblioteca);
    }

    @PatchMapping("/{id}/livro")
    public ResponseEntity<String> adicionarLivro(@RequestBody CriarLivroDto dto, @PathVariable("id") Long id) {
        Livro livro = livroMapper.toLivro(dto);
        this.bibliotecaService.adicionarLivroNaBiblioteca(id, livro);
        return ResponseEntity.status(HttpStatus.OK).body("Livro adicionado com sucesso!");
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<BibliotecaRespostaDto> listarBibliotecaPorId(@PathVariable("id") Long id) {
        Biblioteca biblioteca = this.bibliotecaService.buscarBibliotecaPorId(id);
        BibliotecaRespostaDto resposta = bibliotecaMapper.toBibliotecaRespostaDto(biblioteca);
        return ResponseEntity.ok().body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<BibliotecaRespostaDto>> listarTodasBibliotecas() {
        List<Biblioteca> biblioteca = this.bibliotecaService.buscarTodasBibliotecas();
        List<BibliotecaRespostaDto> resposta = bibliotecaMapper.toListaBibliotecaRespostaDto(biblioteca);
        return ResponseEntity.ok().body(resposta);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarBiblioteca(@PathVariable("id") Long id) {
        this.bibliotecaService.removerBibliotecaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body("Biblioteca deletada com sucesso!");
    }
    
    @DeleteMapping("/{bibliotecaId}/livro/{livroId}")
    public ResponseEntity<String> deletarLivro(@PathVariable("bibliotecaId") Long bibliotecaId, @PathVariable("livroId") Long livroId) {
        this.bibliotecaService.removerLivroDaBiblioteca(bibliotecaId, livroId);
        return ResponseEntity.status(HttpStatus.OK).body("Livro deletado com sucesso!");
    }
}
