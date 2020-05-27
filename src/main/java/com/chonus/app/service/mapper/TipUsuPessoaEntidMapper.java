package com.chonus.app.service.mapper;


import com.chonus.app.domain.*;
import com.chonus.app.service.dto.TipUsuPessoaEntidDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TipUsuPessoaEntid} and its DTO {@link TipUsuPessoaEntidDTO}.
 */
@Mapper(componentModel = "spring", uses = {PessoaMapper.class, EntidadeMapper.class, TipoUsuarioMapper.class})
public interface TipUsuPessoaEntidMapper extends EntityMapper<TipUsuPessoaEntidDTO, TipUsuPessoaEntid> {

    @Mapping(source = "pessoa.id", target = "pessoaId")
    @Mapping(source = "entidade.id", target = "entidadeId")
    @Mapping(source = "tipoUsuario.id", target = "tipoUsuarioId")
    TipUsuPessoaEntidDTO toDto(TipUsuPessoaEntid tipUsuPessoaEntid);

    @Mapping(source = "pessoaId", target = "pessoa")
    @Mapping(source = "entidadeId", target = "entidade")
    @Mapping(source = "tipoUsuarioId", target = "tipoUsuario")
    TipUsuPessoaEntid toEntity(TipUsuPessoaEntidDTO tipUsuPessoaEntidDTO);

    default TipUsuPessoaEntid fromId(String id) {
        if (id == null) {
            return null;
        }
        TipUsuPessoaEntid tipUsuPessoaEntid = new TipUsuPessoaEntid();
        tipUsuPessoaEntid.setId(id);
        return tipUsuPessoaEntid;
    }
}
