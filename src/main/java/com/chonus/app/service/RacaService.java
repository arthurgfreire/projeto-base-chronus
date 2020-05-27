package com.chonus.app.service;

import com.chonus.app.service.dto.RacaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.chonus.app.domain.Raca}.
 */
public interface RacaService {

    /**
     * Save a raca.
     *
     * @param racaDTO the entity to save.
     * @return the persisted entity.
     */
    RacaDTO save(RacaDTO racaDTO);

    /**
     * Get all the racas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RacaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" raca.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RacaDTO> findOne(String id);

    /**
     * Delete the "id" raca.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
