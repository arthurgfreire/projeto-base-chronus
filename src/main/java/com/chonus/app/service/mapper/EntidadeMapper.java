package com.chonus.app.service.mapper;


import com.chonus.app.domain.*;
import com.chonus.app.service.dto.EntidadeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Entidade} and its DTO {@link EntidadeDTO}.
 */
@Mapper(componentModel = "spring", uses = {EnderecoMapper.class})
public interface EntidadeMapper extends EntityMapper<EntidadeDTO, Entidade> {

    @Mapping(source = "endereco.id", target = "enderecoId")
    EntidadeDTO toDto(Entidade entidade);

    @Mapping(source = "enderecoId", target = "endereco")
    @Mapping(target = "unidadeAtendimentos", ignore = true)
    Entidade toEntity(EntidadeDTO entidadeDTO);

    default Entidade fromId(String id) {
        if (id == null) {
            return null;
        }
        Entidade entidade = new Entidade();
        entidade.setId(id);
        return entidade;
    }
}
