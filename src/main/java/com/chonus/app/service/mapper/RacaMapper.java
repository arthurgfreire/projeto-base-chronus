package com.chonus.app.service.mapper;


import com.chonus.app.domain.*;
import com.chonus.app.service.dto.RacaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Raca} and its DTO {@link RacaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RacaMapper extends EntityMapper<RacaDTO, Raca> {



    default Raca fromId(String id) {
        if (id == null) {
            return null;
        }
        Raca raca = new Raca();
        raca.setId(id);
        return raca;
    }
}
