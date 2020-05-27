package com.chonus.app.service;

import com.chonus.app.service.dto.ClassificacaoUsuarioDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.chonus.app.domain.ClassificacaoUsuario}.
 */
public interface ClassificacaoUsuarioService {

    /**
     * Save a classificacaoUsuario.
     *
     * @param classificacaoUsuarioDTO the entity to save.
     * @return the persisted entity.
     */
    ClassificacaoUsuarioDTO save(ClassificacaoUsuarioDTO classificacaoUsuarioDTO);

    /**
     * Get all the classificacaoUsuarios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClassificacaoUsuarioDTO> findAll(Pageable pageable);


    /**
     * Get the "id" classificacaoUsuario.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClassificacaoUsuarioDTO> findOne(String id);

    /**
     * Delete the "id" classificacaoUsuario.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
