package com.chonus.app.service.impl;

import com.chonus.app.service.TipoSanguineoService;
import com.chonus.app.domain.TipoSanguineo;
import com.chonus.app.repository.TipoSanguineoRepository;
import com.chonus.app.service.dto.TipoSanguineoDTO;
import com.chonus.app.service.mapper.TipoSanguineoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TipoSanguineo}.
 */
@Service
public class TipoSanguineoServiceImpl implements TipoSanguineoService {

    private final Logger log = LoggerFactory.getLogger(TipoSanguineoServiceImpl.class);

    private final TipoSanguineoRepository tipoSanguineoRepository;

    private final TipoSanguineoMapper tipoSanguineoMapper;

    public TipoSanguineoServiceImpl(TipoSanguineoRepository tipoSanguineoRepository, TipoSanguineoMapper tipoSanguineoMapper) {
        this.tipoSanguineoRepository = tipoSanguineoRepository;
        this.tipoSanguineoMapper = tipoSanguineoMapper;
    }

    /**
     * Save a tipoSanguineo.
     *
     * @param tipoSanguineoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TipoSanguineoDTO save(TipoSanguineoDTO tipoSanguineoDTO) {
        log.debug("Request to save TipoSanguineo : {}", tipoSanguineoDTO);
        TipoSanguineo tipoSanguineo = tipoSanguineoMapper.toEntity(tipoSanguineoDTO);
        tipoSanguineo = tipoSanguineoRepository.save(tipoSanguineo);
        return tipoSanguineoMapper.toDto(tipoSanguineo);
    }

    /**
     * Get all the tipoSanguineos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<TipoSanguineoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TipoSanguineos");
        return tipoSanguineoRepository.findAll(pageable)
            .map(tipoSanguineoMapper::toDto);
    }


    /**
     * Get one tipoSanguineo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<TipoSanguineoDTO> findOne(String id) {
        log.debug("Request to get TipoSanguineo : {}", id);
        return tipoSanguineoRepository.findById(id)
            .map(tipoSanguineoMapper::toDto);
    }

    /**
     * Delete the tipoSanguineo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete TipoSanguineo : {}", id);

        tipoSanguineoRepository.deleteById(id);
    }
}
