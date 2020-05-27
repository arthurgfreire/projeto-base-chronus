package com.chonus.app.service.impl;

import com.chonus.app.service.TipUsuPessoaEntidService;
import com.chonus.app.domain.TipUsuPessoaEntid;
import com.chonus.app.repository.TipUsuPessoaEntidRepository;
import com.chonus.app.service.dto.TipUsuPessoaEntidDTO;
import com.chonus.app.service.mapper.TipUsuPessoaEntidMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TipUsuPessoaEntid}.
 */
@Service
public class TipUsuPessoaEntidServiceImpl implements TipUsuPessoaEntidService {

    private final Logger log = LoggerFactory.getLogger(TipUsuPessoaEntidServiceImpl.class);

    private final TipUsuPessoaEntidRepository tipUsuPessoaEntidRepository;

    private final TipUsuPessoaEntidMapper tipUsuPessoaEntidMapper;

    public TipUsuPessoaEntidServiceImpl(TipUsuPessoaEntidRepository tipUsuPessoaEntidRepository, TipUsuPessoaEntidMapper tipUsuPessoaEntidMapper) {
        this.tipUsuPessoaEntidRepository = tipUsuPessoaEntidRepository;
        this.tipUsuPessoaEntidMapper = tipUsuPessoaEntidMapper;
    }

    /**
     * Save a tipUsuPessoaEntid.
     *
     * @param tipUsuPessoaEntidDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TipUsuPessoaEntidDTO save(TipUsuPessoaEntidDTO tipUsuPessoaEntidDTO) {
        log.debug("Request to save TipUsuPessoaEntid : {}", tipUsuPessoaEntidDTO);
        TipUsuPessoaEntid tipUsuPessoaEntid = tipUsuPessoaEntidMapper.toEntity(tipUsuPessoaEntidDTO);
        tipUsuPessoaEntid = tipUsuPessoaEntidRepository.save(tipUsuPessoaEntid);
        return tipUsuPessoaEntidMapper.toDto(tipUsuPessoaEntid);
    }

    /**
     * Get all the tipUsuPessoaEntids.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<TipUsuPessoaEntidDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TipUsuPessoaEntids");
        return tipUsuPessoaEntidRepository.findAll(pageable)
            .map(tipUsuPessoaEntidMapper::toDto);
    }


    /**
     * Get one tipUsuPessoaEntid by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<TipUsuPessoaEntidDTO> findOne(String id) {
        log.debug("Request to get TipUsuPessoaEntid : {}", id);
        return tipUsuPessoaEntidRepository.findById(id)
            .map(tipUsuPessoaEntidMapper::toDto);
    }

    /**
     * Delete the tipUsuPessoaEntid by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete TipUsuPessoaEntid : {}", id);

        tipUsuPessoaEntidRepository.deleteById(id);
    }
}
