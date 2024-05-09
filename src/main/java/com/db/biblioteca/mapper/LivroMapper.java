package com.db.biblioteca.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.db.biblioteca.model.Livro;
import com.db.biblioteca.model.dto.CriarLivroDTO;
import com.db.biblioteca.model.dto.LivroRespostaDTO;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    @Mapping(target = "biblioteca", ignore = true)
    Livro toLivro(CriarLivroDTO criarLivroDTO);

    LivroRespostaDTO toLivroRespostaDTO(Livro livro);

    List<LivroRespostaDTO> toListaDeLivroToRespostaDTO(List<Livro> livros);
}