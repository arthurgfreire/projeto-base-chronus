package com.chonus.app.service.mapper;


import com.chonus.app.domain.*;
import com.chonus.app.service.dto.TipoSanguineoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TipoSanguineo} and its DTO {@link TipoSanguineoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoSanguineoMapper extends EntityMapper<TipoSanguineoDTO, TipoSanguineo> {



    default TipoSanguineo fromId(String id) {
        if (id == null) {
            return null;
        }
        TipoSanguineo tipoSanguineo = new TipoSanguineo();
        tipoSanguineo.setId(id);
        return tipoSanguineo;
    }
}
