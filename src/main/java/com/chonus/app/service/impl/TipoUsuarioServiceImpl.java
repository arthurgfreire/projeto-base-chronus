package com.chonus.app.service.impl;

import com.chonus.app.service.TipoUsuarioService;
import com.chonus.app.domain.TipoUsuario;
import com.chonus.app.repository.TipoUsuarioRepository;
import com.chonus.app.service.dto.TipoUsuarioDTO;
import com.chonus.app.service.mapper.TipoUsuarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TipoUsuario}.
 */
@Service
public class TipoUsuarioServiceImpl implements TipoUsuarioService {

    private final Logger log = LoggerFactory.getLogger(TipoUsuarioServiceImpl.class);

    private final TipoUsuarioRepository tipoUsuarioRepository;

    private final TipoUsuarioMapper tipoUsuarioMapper;

    public TipoUsuarioServiceImpl(TipoUsuarioRepository tipoUsuarioRepository, TipoUsuarioMapper tipoUsuarioMapper) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
        this.tipoUsuarioMapper = tipoUsuarioMapper;
    }

    /**
     * Save a tipoUsuario.
     *
     * @param tipoUsuarioDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TipoUsuarioDTO save(TipoUsuarioDTO tipoUsuarioDTO) {
        log.debug("Request to save TipoUsuario : {}", tipoUsuarioDTO);
        TipoUsuario tipoUsuario = tipoUsuarioMapper.toEntity(tipoUsuarioDTO);
        tipoUsuario = tipoUsuarioRepository.save(tipoUsuario);
        return tipoUsuarioMapper.toDto(tipoUsuario);
    }

    /**
     * Get all the tipoUsuarios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<TipoUsuarioDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TipoUsuarios");
        return tipoUsuarioRepository.findAll(pageable)
            .map(tipoUsuarioMapper::toDto);
    }


    /**
     * Get one tipoUsuario by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<TipoUsuarioDTO> findOne(String id) {
        log.debug("Request to get TipoUsuario : {}", id);
        return tipoUsuarioRepository.findById(id)
            .map(tipoUsuarioMapper::toDto);
    }

    /**
     * Delete the tipoUsuario by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete TipoUsuario : {}", id);

        tipoUsuarioRepository.deleteById(id);
    }
}
