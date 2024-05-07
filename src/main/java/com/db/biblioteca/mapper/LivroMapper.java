package com.db.biblioteca.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;
import com.db.biblioteca.model.Livro;
import com.db.biblioteca.model.dto.CriarLivroDto;
import com.db.biblioteca.model.dto.LivroRespostaDto;

@Mapper(componentModel = "spring")
public interface LivroMapper {
    LivroMapper INSTANCE = Mappers.getMapper(LivroMapper.class);

    @Mapping(target = "biblioteca", ignore = true)
    Livro toLivro(CriarLivroDto dto);

    LivroRespostaDto toLivroRespostaDto(Livro livro); 
    List<LivroRespostaDto> toListaLivroRespostaDto(List<Livro> livro); 

}
