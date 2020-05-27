package com.chonus.app.service.mapper;


import com.chonus.app.domain.*;
import com.chonus.app.service.dto.UfDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Uf} and its DTO {@link UfDTO}.
 */
@Mapper(componentModel = "spring", uses = {PaisMapper.class})
public interface UfMapper extends EntityMapper<UfDTO, Uf> {

    @Mapping(source = "pais.id", target = "paisId")
    UfDTO toDto(Uf uf);

    @Mapping(source = "paisId", target = "pais")
    Uf toEntity(UfDTO ufDTO);

    default Uf fromId(String id) {
        if (id == null) {
            return null;
        }
        Uf uf = new Uf();
        uf.setId(id);
        return uf;
    }
}
