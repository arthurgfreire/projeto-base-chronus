package com.chonus.app.service.impl;

import com.chonus.app.service.CidadeService;
import com.chonus.app.domain.Cidade;
import com.chonus.app.repository.CidadeRepository;
import com.chonus.app.service.dto.CidadeDTO;
import com.chonus.app.service.mapper.CidadeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Cidade}.
 */
@Service
public class CidadeServiceImpl implements CidadeService {

    private final Logger log = LoggerFactory.getLogger(CidadeServiceImpl.class);

    private final CidadeRepository cidadeRepository;

    private final CidadeMapper cidadeMapper;

    public CidadeServiceImpl(CidadeRepository cidadeRepository, CidadeMapper cidadeMapper) {
        this.cidadeRepository = cidadeRepository;
        this.cidadeMapper = cidadeMapper;
    }

    /**
     * Save a cidade.
     *
     * @param cidadeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CidadeDTO save(CidadeDTO cidadeDTO) {
        log.debug("Request to save Cidade : {}", cidadeDTO);
        Cidade cidade = cidadeMapper.toEntity(cidadeDTO);
        cidade = cidadeRepository.save(cidade);
        return cidadeMapper.toDto(cidade);
    }

    /**
     * Get all the cidades.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<CidadeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cidades");
        return cidadeRepository.findAll(pageable)
            .map(cidadeMapper::toDto);
    }


    /**
     * Get one cidade by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<CidadeDTO> findOne(String id) {
        log.debug("Request to get Cidade : {}", id);
        return cidadeRepository.findById(id)
            .map(cidadeMapper::toDto);
    }

    /**
     * Delete the cidade by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Cidade : {}", id);

        cidadeRepository.deleteById(id);
    }
}
