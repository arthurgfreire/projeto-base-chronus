package com.chonus.app.web.rest;

import com.chonus.app.service.TipoUsuarioService;
import com.chonus.app.web.rest.errors.BadRequestAlertException;
import com.chonus.app.service.dto.TipoUsuarioDTO;

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
 * REST controller for managing {@link com.chonus.app.domain.TipoUsuario}.
 */
@RestController
@RequestMapping("/api")
public class TipoUsuarioResource {

    private final Logger log = LoggerFactory.getLogger(TipoUsuarioResource.class);

    private static final String ENTITY_NAME = "projetoBaseChronusTipoUsuario";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoUsuarioService tipoUsuarioService;

    public TipoUsuarioResource(TipoUsuarioService tipoUsuarioService) {
        this.tipoUsuarioService = tipoUsuarioService;
    }

    /**
     * {@code POST  /tipo-usuarios} : Create a new tipoUsuario.
     *
     * @param tipoUsuarioDTO the tipoUsuarioDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoUsuarioDTO, or with status {@code 400 (Bad Request)} if the tipoUsuario has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipo-usuarios")
    public ResponseEntity<TipoUsuarioDTO> createTipoUsuario(@RequestBody TipoUsuarioDTO tipoUsuarioDTO) throws URISyntaxException {
        log.debug("REST request to save TipoUsuario : {}", tipoUsuarioDTO);
        if (tipoUsuarioDTO.getId() != null) {
            throw new BadRequestAlertException("A new tipoUsuario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoUsuarioDTO result = tipoUsuarioService.save(tipoUsuarioDTO);
        return ResponseEntity.created(new URI("/api/tipo-usuarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /tipo-usuarios} : Updates an existing tipoUsuario.
     *
     * @param tipoUsuarioDTO the tipoUsuarioDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoUsuarioDTO,
     * or with status {@code 400 (Bad Request)} if the tipoUsuarioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoUsuarioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipo-usuarios")
    public ResponseEntity<TipoUsuarioDTO> updateTipoUsuario(@RequestBody TipoUsuarioDTO tipoUsuarioDTO) throws URISyntaxException {
        log.debug("REST request to update TipoUsuario : {}", tipoUsuarioDTO);
        if (tipoUsuarioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoUsuarioDTO result = tipoUsuarioService.save(tipoUsuarioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tipoUsuarioDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /tipo-usuarios} : get all the tipoUsuarios.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoUsuarios in body.
     */
    @GetMapping("/tipo-usuarios")
    public ResponseEntity<List<TipoUsuarioDTO>> getAllTipoUsuarios(Pageable pageable) {
        log.debug("REST request to get a page of TipoUsuarios");
        Page<TipoUsuarioDTO> page = tipoUsuarioService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tipo-usuarios/:id} : get the "id" tipoUsuario.
     *
     * @param id the id of the tipoUsuarioDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoUsuarioDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipo-usuarios/{id}")
    public ResponseEntity<TipoUsuarioDTO> getTipoUsuario(@PathVariable String id) {
        log.debug("REST request to get TipoUsuario : {}", id);
        Optional<TipoUsuarioDTO> tipoUsuarioDTO = tipoUsuarioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoUsuarioDTO);
    }

    /**
     * {@code DELETE  /tipo-usuarios/:id} : delete the "id" tipoUsuario.
     *
     * @param id the id of the tipoUsuarioDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipo-usuarios/{id}")
    public ResponseEntity<Void> deleteTipoUsuario(@PathVariable String id) {
        log.debug("REST request to delete TipoUsuario : {}", id);

        tipoUsuarioService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
