package com.chonus.app.web.rest;

import com.chonus.app.service.UnidadeAtendimentoService;
import com.chonus.app.web.rest.errors.BadRequestAlertException;
import com.chonus.app.service.dto.UnidadeAtendimentoDTO;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.chonus.app.domain.UnidadeAtendimento}.
 */
@RestController
@RequestMapping("/api")
public class UnidadeAtendimentoResource {

    private final Logger log = LoggerFactory.getLogger(UnidadeAtendimentoResource.class);

    private static final String ENTITY_NAME = "projetoBaseChronusUnidadeAtendimento";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UnidadeAtendimentoService unidadeAtendimentoService;

    public UnidadeAtendimentoResource(UnidadeAtendimentoService unidadeAtendimentoService) {
        this.unidadeAtendimentoService = unidadeAtendimentoService;
    }

    /**
     * {@code POST  /unidade-atendimentos} : Create a new unidadeAtendimento.
     *
     * @param unidadeAtendimentoDTO the unidadeAtendimentoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new unidadeAtendimentoDTO, or with status {@code 400 (Bad Request)} if the unidadeAtendimento has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/unidade-atendimentos")
    public ResponseEntity<UnidadeAtendimentoDTO> createUnidadeAtendimento(@Valid @RequestBody UnidadeAtendimentoDTO unidadeAtendimentoDTO) throws URISyntaxException {
        log.debug("REST request to save UnidadeAtendimento : {}", unidadeAtendimentoDTO);
        if (unidadeAtendimentoDTO.getId() != null) {
            throw new BadRequestAlertException("A new unidadeAtendimento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UnidadeAtendimentoDTO result = unidadeAtendimentoService.save(unidadeAtendimentoDTO);
        return ResponseEntity.created(new URI("/api/unidade-atendimentos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /unidade-atendimentos} : Updates an existing unidadeAtendimento.
     *
     * @param unidadeAtendimentoDTO the unidadeAtendimentoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated unidadeAtendimentoDTO,
     * or with status {@code 400 (Bad Request)} if the unidadeAtendimentoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the unidadeAtendimentoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/unidade-atendimentos")
    public ResponseEntity<UnidadeAtendimentoDTO> updateUnidadeAtendimento(@Valid @RequestBody UnidadeAtendimentoDTO unidadeAtendimentoDTO) throws URISyntaxException {
        log.debug("REST request to update UnidadeAtendimento : {}", unidadeAtendimentoDTO);
        if (unidadeAtendimentoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UnidadeAtendimentoDTO result = unidadeAtendimentoService.save(unidadeAtendimentoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, unidadeAtendimentoDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /unidade-atendimentos} : get all the unidadeAtendimentos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of unidadeAtendimentos in body.
     */
    @GetMapping("/unidade-atendimentos")
    public ResponseEntity<List<UnidadeAtendimentoDTO>> getAllUnidadeAtendimentos(Pageable pageable) {
        log.debug("REST request to get a page of UnidadeAtendimentos");
        Page<UnidadeAtendimentoDTO> page = unidadeAtendimentoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /unidade-atendimentos/:id} : get the "id" unidadeAtendimento.
     *
     * @param id the id of the unidadeAtendimentoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the unidadeAtendimentoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/unidade-atendimentos/{id}")
    public ResponseEntity<UnidadeAtendimentoDTO> getUnidadeAtendimento(@PathVariable String id) {
        log.debug("REST request to get UnidadeAtendimento : {}", id);
        Optional<UnidadeAtendimentoDTO> unidadeAtendimentoDTO = unidadeAtendimentoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(unidadeAtendimentoDTO);
    }

    /**
     * {@code DELETE  /unidade-atendimentos/:id} : delete the "id" unidadeAtendimento.
     *
     * @param id the id of the unidadeAtendimentoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/unidade-atendimentos/{id}")
    public ResponseEntity<Void> deleteUnidadeAtendimento(@PathVariable String id) {
        log.debug("REST request to delete UnidadeAtendimento : {}", id);

        unidadeAtendimentoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
