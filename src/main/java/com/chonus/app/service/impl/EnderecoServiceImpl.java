package com.chonus.app.service.impl;

import com.chonus.app.service.EnderecoService;
import com.chonus.app.domain.Endereco;
import com.chonus.app.repository.EnderecoRepository;
import com.chonus.app.service.dto.EnderecoDTO;
import com.chonus.app.service.mapper.EnderecoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Endereco}.
 */
@Service
public class EnderecoServiceImpl implements EnderecoService {

    private final Logger log = LoggerFactory.getLogger(EnderecoServiceImpl.class);

    private final EnderecoRepository enderecoRepository;

    private final EnderecoMapper enderecoMapper;

    public EnderecoServiceImpl(EnderecoRepository enderecoRepository, EnderecoMapper enderecoMapper) {
        this.enderecoRepository = enderecoRepository;
        this.enderecoMapper = enderecoMapper;
    }

    /**
     * Save a endereco.
     *
     * @param enderecoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnderecoDTO save(EnderecoDTO enderecoDTO) {
        log.debug("Request to save Endereco : {}", enderecoDTO);
        Endereco endereco = enderecoMapper.toEntity(enderecoDTO);
        endereco = enderecoRepository.save(endereco);
        return enderecoMapper.toDto(endereco);
    }

    /**
     * Get all the enderecos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<EnderecoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Enderecos");
        return enderecoRepository.findAll(pageable)
            .map(enderecoMapper::toDto);
    }


    /**
     * Get one endereco by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<EnderecoDTO> findOne(String id) {
        log.debug("Request to get Endereco : {}", id);
        return enderecoRepository.findById(id)
            .map(enderecoMapper::toDto);
    }

    /**
     * Delete the endereco by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Endereco : {}", id);

        enderecoRepository.deleteById(id);
    }
}
