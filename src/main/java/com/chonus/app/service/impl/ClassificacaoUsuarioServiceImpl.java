package com.chonus.app.service.impl;

import com.chonus.app.service.ClassificacaoUsuarioService;
import com.chonus.app.domain.ClassificacaoUsuario;
import com.chonus.app.repository.ClassificacaoUsuarioRepository;
import com.chonus.app.service.dto.ClassificacaoUsuarioDTO;
import com.chonus.app.service.mapper.ClassificacaoUsuarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ClassificacaoUsuario}.
 */
@Service
public class ClassificacaoUsuarioServiceImpl implements ClassificacaoUsuarioService {

    private final Logger log = LoggerFactory.getLogger(ClassificacaoUsuarioServiceImpl.class);

    private final ClassificacaoUsuarioRepository classificacaoUsuarioRepository;

    private final ClassificacaoUsuarioMapper classificacaoUsuarioMapper;

    public ClassificacaoUsuarioServiceImpl(ClassificacaoUsuarioRepository classificacaoUsuarioRepository, ClassificacaoUsuarioMapper classificacaoUsuarioMapper) {
        this.classificacaoUsuarioRepository = classificacaoUsuarioRepository;
        this.classificacaoUsuarioMapper = classificacaoUsuarioMapper;
    }

    /**
     * Save a classificacaoUsuario.
     *
     * @param classificacaoUsuarioDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ClassificacaoUsuarioDTO save(ClassificacaoUsuarioDTO classificacaoUsuarioDTO) {
        log.debug("Request to save ClassificacaoUsuario : {}", classificacaoUsuarioDTO);
        ClassificacaoUsuario classificacaoUsuario = classificacaoUsuarioMapper.toEntity(classificacaoUsuarioDTO);
        classificacaoUsuario = classificacaoUsuarioRepository.save(classificacaoUsuario);
        return classificacaoUsuarioMapper.toDto(classificacaoUsuario);
    }

    /**
     * Get all the classificacaoUsuarios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<ClassificacaoUsuarioDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClassificacaoUsuarios");
        return classificacaoUsuarioRepository.findAll(pageable)
            .map(classificacaoUsuarioMapper::toDto);
    }


    /**
     * Get one classificacaoUsuario by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<ClassificacaoUsuarioDTO> findOne(String id) {
        log.debug("Request to get ClassificacaoUsuario : {}", id);
        return classificacaoUsuarioRepository.findById(id)
            .map(classificacaoUsuarioMapper::toDto);
    }

    /**
     * Delete the classificacaoUsuario by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete ClassificacaoUsuario : {}", id);

        classificacaoUsuarioRepository.deleteById(id);
    }
}
