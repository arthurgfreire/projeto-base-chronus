package com.chonus.app.web.rest;

import com.chonus.app.service.UfService;
import com.chonus.app.web.rest.errors.BadRequestAlertException;
import com.chonus.app.service.dto.UfDTO;

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
 * REST controller for managing {@link com.chonus.app.domain.Uf}.
 */
@RestController
@RequestMapping("/api")
public class UfResource {

    private final Logger log = LoggerFactory.getLogger(UfResource.class);

    private static final String ENTITY_NAME = "projetoBaseChronusUf";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UfService ufService;

    public UfResource(UfService ufService) {
        this.ufService = ufService;
    }

    /**
     * {@code POST  /ufs} : Create a new uf.
     *
     * @param ufDTO the ufDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ufDTO, or with status {@code 400 (Bad Request)} if the uf has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ufs")
    public ResponseEntity<UfDTO> createUf(@Valid @RequestBody UfDTO ufDTO) throws URISyntaxException {
        log.debug("REST request to save Uf : {}", ufDTO);
        if (ufDTO.getId() != null) {
            throw new BadRequestAlertException("A new uf cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UfDTO result = ufService.save(ufDTO);
        return ResponseEntity.created(new URI("/api/ufs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /ufs} : Updates an existing uf.
     *
     * @param ufDTO the ufDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ufDTO,
     * or with status {@code 400 (Bad Request)} if the ufDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ufDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ufs")
    public ResponseEntity<UfDTO> updateUf(@Valid @RequestBody UfDTO ufDTO) throws URISyntaxException {
        log.debug("REST request to update Uf : {}", ufDTO);
        if (ufDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UfDTO result = ufService.save(ufDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ufDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /ufs} : get all the ufs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ufs in body.
     */
    @GetMapping("/ufs")
    public ResponseEntity<List<UfDTO>> getAllUfs(Pageable pageable) {
        log.debug("REST request to get a page of Ufs");
        Page<UfDTO> page = ufService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ufs/:id} : get the "id" uf.
     *
     * @param id the id of the ufDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ufDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ufs/{id}")
    public ResponseEntity<UfDTO> getUf(@PathVariable String id) {
        log.debug("REST request to get Uf : {}", id);
        Optional<UfDTO> ufDTO = ufService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ufDTO);
    }

    /**
     * {@code DELETE  /ufs/:id} : delete the "id" uf.
     *
     * @param id the id of the ufDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ufs/{id}")
    public ResponseEntity<Void> deleteUf(@PathVariable String id) {
        log.debug("REST request to delete Uf : {}", id);

        ufService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
