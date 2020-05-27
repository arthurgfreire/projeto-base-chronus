package com.chonus.app.service.impl;

import com.chonus.app.service.UfService;
import com.chonus.app.domain.Uf;
import com.chonus.app.repository.UfRepository;
import com.chonus.app.service.dto.UfDTO;
import com.chonus.app.service.mapper.UfMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Uf}.
 */
@Service
public class UfServiceImpl implements UfService {

    private final Logger log = LoggerFactory.getLogger(UfServiceImpl.class);

    private final UfRepository ufRepository;

    private final UfMapper ufMapper;

    public UfServiceImpl(UfRepository ufRepository, UfMapper ufMapper) {
        this.ufRepository = ufRepository;
        this.ufMapper = ufMapper;
    }

    /**
     * Save a uf.
     *
     * @param ufDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UfDTO save(UfDTO ufDTO) {
        log.debug("Request to save Uf : {}", ufDTO);
        Uf uf = ufMapper.toEntity(ufDTO);
        uf = ufRepository.save(uf);
        return ufMapper.toDto(uf);
    }

    /**
     * Get all the ufs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<UfDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Ufs");
        return ufRepository.findAll(pageable)
            .map(ufMapper::toDto);
    }


    /**
     * Get one uf by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<UfDTO> findOne(String id) {
        log.debug("Request to get Uf : {}", id);
        return ufRepository.findById(id)
            .map(ufMapper::toDto);
    }

    /**
     * Delete the uf by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Uf : {}", id);

        ufRepository.deleteById(id);
    }
}
