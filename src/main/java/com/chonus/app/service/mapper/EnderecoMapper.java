package com.chonus.app.service.mapper;


import com.chonus.app.domain.*;
import com.chonus.app.service.dto.EnderecoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Endereco} and its DTO {@link EnderecoDTO}.
 */
@Mapper(componentModel = "spring", uses = {PaisMapper.class, UfMapper.class, CidadeMapper.class})
public interface EnderecoMapper extends EntityMapper<EnderecoDTO, Endereco> {

    @Mapping(source = "pais.id", target = "paisId")
    @Mapping(source = "uf.id", target = "ufId")
    @Mapping(source = "cidade.id", target = "cidadeId")
    EnderecoDTO toDto(Endereco endereco);

    @Mapping(source = "paisId", target = "pais")
    @Mapping(source = "ufId", target = "uf")
    @Mapping(source = "cidadeId", target = "cidade")
    Endereco toEntity(EnderecoDTO enderecoDTO);

    default Endereco fromId(String id) {
        if (id == null) {
            return null;
        }
        Endereco endereco = new Endereco();
        endereco.setId(id);
        return endereco;
    }
}
