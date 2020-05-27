package com.chonus.app.service.mapper;


import com.chonus.app.domain.*;
import com.chonus.app.service.dto.UnidadeAtendimentoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UnidadeAtendimento} and its DTO {@link UnidadeAtendimentoDTO}.
 */
@Mapper(componentModel = "spring", uses = {EnderecoMapper.class, EntidadeMapper.class})
public interface UnidadeAtendimentoMapper extends EntityMapper<UnidadeAtendimentoDTO, UnidadeAtendimento> {

    @Mapping(source = "endereco.id", target = "enderecoId")
    @Mapping(source = "entidade.id", target = "entidadeId")
    UnidadeAtendimentoDTO toDto(UnidadeAtendimento unidadeAtendimento);

    @Mapping(source = "enderecoId", target = "endereco")
    @Mapping(source = "entidadeId", target = "entidade")
    UnidadeAtendimento toEntity(UnidadeAtendimentoDTO unidadeAtendimentoDTO);

    default UnidadeAtendimento fromId(String id) {
        if (id == null) {
            return null;
        }
        UnidadeAtendimento unidadeAtendimento = new UnidadeAtendimento();
        unidadeAtendimento.setId(id);
        return unidadeAtendimento;
    }
}
