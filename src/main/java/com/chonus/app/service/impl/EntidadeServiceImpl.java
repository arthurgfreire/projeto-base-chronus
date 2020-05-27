package com.chonus.app.service.impl;

import com.chonus.app.service.EntidadeService;
import com.chonus.app.domain.Entidade;
import com.chonus.app.repository.EntidadeRepository;
import com.chonus.app.service.dto.EntidadeDTO;
import com.chonus.app.service.mapper.EntidadeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Entidade}.
 */
@Service
public class EntidadeServiceImpl implements EntidadeService {

    private final Logger log = LoggerFactory.getLogger(EntidadeServiceImpl.class);

    private final EntidadeRepository entidadeRepository;

    private final EntidadeMapper entidadeMapper;

    public EntidadeServiceImpl(EntidadeRepository entidadeRepository, EntidadeMapper entidadeMapper) {
        this.entidadeRepository = entidadeRepository;
        this.entidadeMapper = entidadeMapper;
    }

    /**
     * Save a entidade.
     *
     * @param entidadeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EntidadeDTO save(EntidadeDTO entidadeDTO) {
        log.debug("Request to save Entidade : {}", entidadeDTO);
        Entidade entidade = entidadeMapper.toEntity(entidadeDTO);
        entidade = entidadeRepository.save(entidade);
        return entidadeMapper.toDto(entidade);
    }

    /**
     * Get all the entidades.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<EntidadeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Entidades");
        return entidadeRepository.findAll(pageable)
            .map(entidadeMapper::toDto);
    }


    /**
     * Get one entidade by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<EntidadeDTO> findOne(String id) {
        log.debug("Request to get Entidade : {}", id);
        return entidadeRepository.findById(id)
            .map(entidadeMapper::toDto);
    }

    /**
     * Delete the entidade by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Entidade : {}", id);

        entidadeRepository.deleteById(id);
    }
}
