package com.db.biblioteca.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.db.biblioteca.model.Biblioteca;
import com.db.biblioteca.model.Livro;
import com.db.biblioteca.model.dto.BibliotecaRespostaDTO;
import com.db.biblioteca.model.dto.CriarBibliotecaDTO;
import com.db.biblioteca.model.dto.LivroRespostaDTO;

@Mapper(componentModel = "spring")
public interface BibliotecaMapper {

    // BibliotecaMapper INSTANCE = Mappers.getMapper(BibliotecaMapper.class);

    @Mapping(target = "livros", ignore = true)
    Biblioteca toBiblioteca(CriarBibliotecaDTO criarBibliotecaDTO);

    BibliotecaRespostaDTO toBibliotecaRespostaDTO(Biblioteca biblioteca);

    List<BibliotecaRespostaDTO> toListToBibliotecaRespostaDTO(List<Biblioteca> bibliotecas);

    LivroRespostaDTO toLivroRespostaDTO(Livro livro);

}
