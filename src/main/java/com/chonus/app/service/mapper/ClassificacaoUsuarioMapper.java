package com.chonus.app.service.mapper;


import com.chonus.app.domain.*;
import com.chonus.app.service.dto.ClassificacaoUsuarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClassificacaoUsuario} and its DTO {@link ClassificacaoUsuarioDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClassificacaoUsuarioMapper extends EntityMapper<ClassificacaoUsuarioDTO, ClassificacaoUsuario> {



    default ClassificacaoUsuario fromId(String id) {
        if (id == null) {
            return null;
        }
        ClassificacaoUsuario classificacaoUsuario = new ClassificacaoUsuario();
        classificacaoUsuario.setId(id);
        return classificacaoUsuario;
    }
}
