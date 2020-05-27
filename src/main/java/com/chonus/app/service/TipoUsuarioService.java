package com.chonus.app.service;

import com.chonus.app.service.dto.TipoUsuarioDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.chonus.app.domain.TipoUsuario}.
 */
public interface TipoUsuarioService {

    /**
     * Save a tipoUsuario.
     *
     * @param tipoUsuarioDTO the entity to save.
     * @return the persisted entity.
     */
    TipoUsuarioDTO save(TipoUsuarioDTO tipoUsuarioDTO);

    /**
     * Get all the tipoUsuarios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TipoUsuarioDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tipoUsuario.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TipoUsuarioDTO> findOne(String id);

    /**
     * Delete the "id" tipoUsuario.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
