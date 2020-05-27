package com.chonus.app.service;

import com.chonus.app.service.dto.TipUsuPessoaEntidDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.chonus.app.domain.TipUsuPessoaEntid}.
 */
public interface TipUsuPessoaEntidService {

    /**
     * Save a tipUsuPessoaEntid.
     *
     * @param tipUsuPessoaEntidDTO the entity to save.
     * @return the persisted entity.
     */
    TipUsuPessoaEntidDTO save(TipUsuPessoaEntidDTO tipUsuPessoaEntidDTO);

    /**
     * Get all the tipUsuPessoaEntids.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TipUsuPessoaEntidDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tipUsuPessoaEntid.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TipUsuPessoaEntidDTO> findOne(String id);

    /**
     * Delete the "id" tipUsuPessoaEntid.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
