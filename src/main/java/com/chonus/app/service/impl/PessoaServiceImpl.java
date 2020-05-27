package com.chonus.app.service.impl;

import com.chonus.app.service.PessoaService;
import com.chonus.app.domain.Pessoa;
import com.chonus.app.repository.PessoaRepository;
import com.chonus.app.service.dto.PessoaDTO;
import com.chonus.app.service.mapper.PessoaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Pessoa}.
 */
@Service
public class PessoaServiceImpl implements PessoaService {

    private final Logger log = LoggerFactory.getLogger(PessoaServiceImpl.class);

    private final PessoaRepository pessoaRepository;

    private final PessoaMapper pessoaMapper;

    public PessoaServiceImpl(PessoaRepository pessoaRepository, PessoaMapper pessoaMapper) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
    }

    /**
     * Save a pessoa.
     *
     * @param pessoaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PessoaDTO save(PessoaDTO pessoaDTO) {
        log.debug("Request to save Pessoa : {}", pessoaDTO);
        Pessoa pessoa = pessoaMapper.toEntity(pessoaDTO);
        pessoa = pessoaRepository.save(pessoa);
        return pessoaMapper.toDto(pessoa);
    }

    /**
     * Get all the pessoas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<PessoaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pessoas");
        return pessoaRepository.findAll(pageable)
            .map(pessoaMapper::toDto);
    }


    /**
     * Get one pessoa by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<PessoaDTO> findOne(String id) {
        log.debug("Request to get Pessoa : {}", id);
        return pessoaRepository.findById(id)
            .map(pessoaMapper::toDto);
    }

    /**
     * Delete the pessoa by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Pessoa : {}", id);

        pessoaRepository.deleteById(id);
    }
}
