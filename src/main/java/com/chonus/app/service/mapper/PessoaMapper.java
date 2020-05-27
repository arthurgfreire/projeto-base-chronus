package com.chonus.app.service.mapper;


import com.chonus.app.domain.*;
import com.chonus.app.service.dto.PessoaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Pessoa} and its DTO {@link PessoaDTO}.
 */
@Mapper(componentModel = "spring", uses = {EnderecoMapper.class, RacaMapper.class, TipoSanguineoMapper.class, ClassificacaoUsuarioMapper.class})
public interface PessoaMapper extends EntityMapper<PessoaDTO, Pessoa> {

    @Mapping(source = "endereco.id", target = "enderecoId")
    @Mapping(source = "raca.id", target = "racaId")
    @Mapping(source = "tipoSanguineo.id", target = "tipoSanguineoId")
    @Mapping(source = "classificacaoUsuario.id", target = "classificacaoUsuarioId")
    PessoaDTO toDto(Pessoa pessoa);

    @Mapping(source = "enderecoId", target = "endereco")
    @Mapping(source = "racaId", target = "raca")
    @Mapping(source = "tipoSanguineoId", target = "tipoSanguineo")
    @Mapping(source = "classificacaoUsuarioId", target = "classificacaoUsuario")
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
