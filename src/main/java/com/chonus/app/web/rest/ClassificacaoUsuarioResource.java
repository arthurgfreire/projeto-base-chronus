package com.chonus.app.web.rest;

import com.chonus.app.service.ClassificacaoUsuarioService;
import com.chonus.app.web.rest.errors.BadRequestAlertException;
import com.chonus.app.service.dto.ClassificacaoUsuarioDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.chonus.app.domain.ClassificacaoUsuario}.
 */
@RestController
@RequestMapping("/api")
public class ClassificacaoUsuarioResource {

    private final Logger log = LoggerFactory.getLogger(ClassificacaoUsuarioResource.class);

    private static final String ENTITY_NAME = "projetoBaseChronusClassificacaoUsuario";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClassificacaoUsuarioService classificacaoUsuarioService;

    public ClassificacaoUsuarioResource(ClassificacaoUsuarioService classificacaoUsuarioService) {
        this.classificacaoUsuarioService = classificacaoUsuarioService;
    }

    /**
     * {@code POST  /classificacao-usuarios} : Create a new classificacaoUsuario.
     *
     * @param classificacaoUsuarioDTO the classificacaoUsuarioDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new classificacaoUsuarioDTO, or with status {@code 400 (Bad Request)} if the classificacaoUsuario has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/classificacao-usuarios")
    public ResponseEntity<ClassificacaoUsuarioDTO> createClassificacaoUsuario(@RequestBody ClassificacaoUsuarioDTO classificacaoUsuarioDTO) throws URISyntaxException {
        log.debug("REST request to save ClassificacaoUsuario : {}", classificacaoUsuarioDTO);
        if (classificacaoUsuarioDTO.getId() != null) {
            throw new BadRequestAlertException("A new classificacaoUsuario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClassificacaoUsuarioDTO result = classificacaoUsuarioService.save(classificacaoUsuarioDTO);
        return ResponseEntity.created(new URI("/api/classificacao-usuarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /classificacao-usuarios} : Updates an existing classificacaoUsuario.
     *
     * @param classificacaoUsuarioDTO the classificacaoUsuarioDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated classificacaoUsuarioDTO,
     * or with status {@code 400 (Bad Request)} if the classificacaoUsuarioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the classificacaoUsuarioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/classificacao-usuarios")
    public ResponseEntity<ClassificacaoUsuarioDTO> updateClassificacaoUsuario(@RequestBody ClassificacaoUsuarioDTO classificacaoUsuarioDTO) throws URISyntaxException {
        log.debug("REST request to update ClassificacaoUsuario : {}", classificacaoUsuarioDTO);
        if (classificacaoUsuarioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClassificacaoUsuarioDTO result = classificacaoUsuarioService.save(classificacaoUsuarioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, classificacaoUsuarioDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /classificacao-usuarios} : get all the classificacaoUsuarios.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of classificacaoUsuarios in body.
     */
    @GetMapping("/classificacao-usuarios")
    public ResponseEntity<List<ClassificacaoUsuarioDTO>> getAllClassificacaoUsuarios(Pageable pageable) {
        log.debug("REST request to get a page of ClassificacaoUsuarios");
        Page<ClassificacaoUsuarioDTO> page = classificacaoUsuarioService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /classificacao-usuarios/:id} : get the "id" classificacaoUsuario.
     *
     * @param id the id of the classificacaoUsuarioDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the classificacaoUsuarioDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/classificacao-usuarios/{id}")
    public ResponseEntity<ClassificacaoUsuarioDTO> getClassificacaoUsuario(@PathVariable String id) {
        log.debug("REST request to get ClassificacaoUsuario : {}", id);
        Optional<ClassificacaoUsuarioDTO> classificacaoUsuarioDTO = classificacaoUsuarioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(classificacaoUsuarioDTO);
    }

    /**
     * {@code DELETE  /classificacao-usuarios/:id} : delete the "id" classificacaoUsuario.
     *
     * @param id the id of the classificacaoUsuarioDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/classificacao-usuarios/{id}")
    public ResponseEntity<Void> deleteClassificacaoUsuario(@PathVariable String id) {
        log.debug("REST request to delete ClassificacaoUsuario : {}", id);

        classificacaoUsuarioService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
