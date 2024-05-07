package com.db.biblioteca.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;
import com.db.biblioteca.model.Biblioteca;
import com.db.biblioteca.model.Livro;
import com.db.biblioteca.model.dto.BibliotecaRespostaDto;
import com.db.biblioteca.model.dto.CriarBibliotecaDto;
import com.db.biblioteca.model.dto.LivroRespostaDto;

@Mapper(componentModel = "spring")
public interface BibliotecaMapper {
    BibliotecaMapper INSTANCE = Mappers.getMapper(BibliotecaMapper.class);

    @Mapping(target = "livros", ignore = true)
    Biblioteca toBiblioteca(CriarBibliotecaDto dto);

    BibliotecaRespostaDto toBibliotecaRespostaDto(Biblioteca biblioteca);
    List<BibliotecaRespostaDto> toListaBibliotecaRespostaDto(List<Biblioteca> bibliotecas);

    LivroRespostaDto toLivroRespostaDto(Livro livro); 
}
