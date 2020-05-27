package com.chonus.app.service.impl;

import com.chonus.app.service.UnidadeAtendimentoService;
import com.chonus.app.domain.UnidadeAtendimento;
import com.chonus.app.repository.UnidadeAtendimentoRepository;
import com.chonus.app.service.dto.UnidadeAtendimentoDTO;
import com.chonus.app.service.mapper.UnidadeAtendimentoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UnidadeAtendimento}.
 */
@Service
public class UnidadeAtendimentoServiceImpl implements UnidadeAtendimentoService {

    private final Logger log = LoggerFactory.getLogger(UnidadeAtendimentoServiceImpl.class);

    private final UnidadeAtendimentoRepository unidadeAtendimentoRepository;

    private final UnidadeAtendimentoMapper unidadeAtendimentoMapper;

    public UnidadeAtendimentoServiceImpl(UnidadeAtendimentoRepository unidadeAtendimentoRepository, UnidadeAtendimentoMapper unidadeAtendimentoMapper) {
        this.unidadeAtendimentoRepository = unidadeAtendimentoRepository;
        this.unidadeAtendimentoMapper = unidadeAtendimentoMapper;
    }

    /**
     * Save a unidadeAtendimento.
     *
     * @param unidadeAtendimentoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UnidadeAtendimentoDTO save(UnidadeAtendimentoDTO unidadeAtendimentoDTO) {
        log.debug("Request to save UnidadeAtendimento : {}", unidadeAtendimentoDTO);
        UnidadeAtendimento unidadeAtendimento = unidadeAtendimentoMapper.toEntity(unidadeAtendimentoDTO);
        unidadeAtendimento = unidadeAtendimentoRepository.save(unidadeAtendimento);
        return unidadeAtendimentoMapper.toDto(unidadeAtendimento);
    }

    /**
     * Get all the unidadeAtendimentos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<UnidadeAtendimentoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UnidadeAtendimentos");
        return unidadeAtendimentoRepository.findAll(pageable)
            .map(unidadeAtendimentoMapper::toDto);
    }


    /**
     * Get one unidadeAtendimento by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<UnidadeAtendimentoDTO> findOne(String id) {
        log.debug("Request to get UnidadeAtendimento : {}", id);
        return unidadeAtendimentoRepository.findById(id)
            .map(unidadeAtendimentoMapper::toDto);
    }

    /**
     * Delete the unidadeAtendimento by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete UnidadeAtendimento : {}", id);

        unidadeAtendimentoRepository.deleteById(id);
    }
}
