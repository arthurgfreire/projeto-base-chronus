package com.chonus.app.service.impl;

import com.chonus.app.service.RacaService;
import com.chonus.app.domain.Raca;
import com.chonus.app.repository.RacaRepository;
import com.chonus.app.service.dto.RacaDTO;
import com.chonus.app.service.mapper.RacaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Raca}.
 */
@Service
public class RacaServiceImpl implements RacaService {

    private final Logger log = LoggerFactory.getLogger(RacaServiceImpl.class);

    private final RacaRepository racaRepository;

    private final RacaMapper racaMapper;

    public RacaServiceImpl(RacaRepository racaRepository, RacaMapper racaMapper) {
        this.racaRepository = racaRepository;
        this.racaMapper = racaMapper;
    }

    /**
     * Save a raca.
     *
     * @param racaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RacaDTO save(RacaDTO racaDTO) {
        log.debug("Request to save Raca : {}", racaDTO);
        Raca raca = racaMapper.toEntity(racaDTO);
        raca = racaRepository.save(raca);
        return racaMapper.toDto(raca);
    }

    /**
     * Get all the racas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<RacaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Racas");
        return racaRepository.findAll(pageable)
            .map(racaMapper::toDto);
    }


    /**
     * Get one raca by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<RacaDTO> findOne(String id) {
        log.debug("Request to get Raca : {}", id);
        return racaRepository.findById(id)
            .map(racaMapper::toDto);
    }

    /**
     * Delete the raca by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Raca : {}", id);

        racaRepository.deleteById(id);
    }
}
