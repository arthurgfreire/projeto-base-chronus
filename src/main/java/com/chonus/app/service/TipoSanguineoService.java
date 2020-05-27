package com.chonus.app.service;

import com.chonus.app.service.dto.TipoSanguineoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.chonus.app.domain.TipoSanguineo}.
 */
public interface TipoSanguineoService {

    /**
     * Save a tipoSanguineo.
     *
     * @param tipoSanguineoDTO the entity to save.
     * @return the persisted entity.
     */
    TipoSanguineoDTO save(TipoSanguineoDTO tipoSanguineoDTO);

    /**
     * Get all the tipoSanguineos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TipoSanguineoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tipoSanguineo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TipoSanguineoDTO> findOne(String id);

    /**
     * Delete the "id" tipoSanguineo.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
