package com.chonus.app.service.mapper;


import com.chonus.app.domain.*;
import com.chonus.app.service.dto.PessoaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Pessoa} and its DTO {@link PessoaDTO}.
 */
@Mapper(componentModel = "spring", uses = {PaisMapper.class, UfMapper.class, CidadeMapper.class, RacaMapper.class, TipoSanguineoMapper.class})
public interface PessoaMapper extends EntityMapper<PessoaDTO, Pessoa> {

    @Mapping(source = "pais.id", target = "paisId")
    @Mapping(source = "uf.id", target = "ufId")
    @Mapping(source = "cidade.id", target = "cidadeId")
    @Mapping(source = "raca.id", target = "racaId")
    @Mapping(source = "tipoSanguineo.id", target = "tipoSanguineoId")
    PessoaDTO toDto(Pessoa pessoa);

    @Mapping(source = "paisId", target = "pais")
    @Mapping(source = "ufId", target = "uf")
    @Mapping(source = "cidadeId", target = "cidade")
    @Mapping(source = "racaId", target = "raca")
    @Mapping(source = "tipoSanguineoId", target = "tipoSanguineo")
    Pessoa toEntity(PessoaDTO pessoaDTO);

    default Pessoa fromId(String id) {
        if (id == null) {
            return null;
        }
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        return pessoa;
    }
}
