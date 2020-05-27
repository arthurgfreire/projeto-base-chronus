package com.chonus.app.service;

import com.chonus.app.service.dto.UnidadeAtendimentoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.chonus.app.domain.UnidadeAtendimento}.
 */
public interface UnidadeAtendimentoService {

    /**
     * Save a unidadeAtendimento.
     *
     * @param unidadeAtendimentoDTO the entity to save.
     * @return the persisted entity.
     */
    UnidadeAtendimentoDTO save(UnidadeAtendimentoDTO unidadeAtendimentoDTO);

    /**
     * Get all the unidadeAtendimentos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UnidadeAtendimentoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" unidadeAtendimento.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UnidadeAtendimentoDTO> findOne(String id);

    /**
     * Delete the "id" unidadeAtendimento.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
