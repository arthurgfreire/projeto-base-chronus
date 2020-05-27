package com.chonus.app.service;

import com.chonus.app.service.dto.EntidadeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.chonus.app.domain.Entidade}.
 */
public interface EntidadeService {

    /**
     * Save a entidade.
     *
     * @param entidadeDTO the entity to save.
     * @return the persisted entity.
     */
    EntidadeDTO save(EntidadeDTO entidadeDTO);

    /**
     * Get all the entidades.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EntidadeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" entidade.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EntidadeDTO> findOne(String id);

    /**
     * Delete the "id" entidade.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
