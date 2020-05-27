package com.chonus.app.service;

import com.chonus.app.service.dto.CidadeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.chonus.app.domain.Cidade}.
 */
public interface CidadeService {

    /**
     * Save a cidade.
     *
     * @param cidadeDTO the entity to save.
     * @return the persisted entity.
     */
    CidadeDTO save(CidadeDTO cidadeDTO);

    /**
     * Get all the cidades.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CidadeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" cidade.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CidadeDTO> findOne(String id);

    /**
     * Delete the "id" cidade.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
