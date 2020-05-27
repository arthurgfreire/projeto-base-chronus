package com.chonus.app.service;

import com.chonus.app.service.dto.EnderecoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.chonus.app.domain.Endereco}.
 */
public interface EnderecoService {

    /**
     * Save a endereco.
     *
     * @param enderecoDTO the entity to save.
     * @return the persisted entity.
     */
    EnderecoDTO save(EnderecoDTO enderecoDTO);

    /**
     * Get all the enderecos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnderecoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" endereco.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnderecoDTO> findOne(String id);

    /**
     * Delete the "id" endereco.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
