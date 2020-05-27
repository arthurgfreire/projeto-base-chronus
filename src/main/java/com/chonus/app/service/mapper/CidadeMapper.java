package com.chonus.app.service.mapper;


import com.chonus.app.domain.*;
import com.chonus.app.service.dto.CidadeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cidade} and its DTO {@link CidadeDTO}.
 */
@Mapper(componentModel = "spring", uses = {UfMapper.class})
public interface CidadeMapper extends EntityMapper<CidadeDTO, Cidade> {

    @Mapping(source = "uf.id", target = "ufId")
    CidadeDTO toDto(Cidade cidade);

    @Mapping(source = "ufId", target = "uf")
    Cidade toEntity(CidadeDTO cidadeDTO);

    default Cidade fromId(String id) {
        if (id == null) {
            return null;
        }
        Cidade cidade = new Cidade();
        cidade.setId(id);
        return cidade;
    }
}
