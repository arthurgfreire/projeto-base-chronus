package com.chonus.app.service.mapper;


import com.chonus.app.domain.*;
import com.chonus.app.service.dto.TipoUsuarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TipoUsuario} and its DTO {@link TipoUsuarioDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoUsuarioMapper extends EntityMapper<TipoUsuarioDTO, TipoUsuario> {



    default TipoUsuario fromId(String id) {
        if (id == null) {
            return null;
        }
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setId(id);
        return tipoUsuario;
    }
}
