package com.chonus.app.service;

import com.chonus.app.service.dto.UfDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.chonus.app.domain.Uf}.
 */
public interface UfService {

    /**
     * Save a uf.
     *
     * @param ufDTO the entity to save.
     * @return the persisted entity.
     */
    UfDTO save(UfDTO ufDTO);

    /**
     * Get all the ufs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UfDTO> findAll(Pageable pageable);


    /**
     * Get the "id" uf.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UfDTO> findOne(String id);

    /**
     * Delete the "id" uf.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
