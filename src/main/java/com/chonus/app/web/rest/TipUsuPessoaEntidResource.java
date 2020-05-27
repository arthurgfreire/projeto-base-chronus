package com.chonus.app.web.rest;

import com.chonus.app.service.TipUsuPessoaEntidService;
import com.chonus.app.web.rest.errors.BadRequestAlertException;
import com.chonus.app.service.dto.TipUsuPessoaEntidDTO;

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
 * REST controller for managing {@link com.chonus.app.domain.TipUsuPessoaEntid}.
 */
@RestController
@RequestMapping("/api")
public class TipUsuPessoaEntidResource {

    private final Logger log = LoggerFactory.getLogger(TipUsuPessoaEntidResource.class);

    private static final String ENTITY_NAME = "projetoBaseChronusTipUsuPessoaEntid";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipUsuPessoaEntidService tipUsuPessoaEntidService;

    public TipUsuPessoaEntidResource(TipUsuPessoaEntidService tipUsuPessoaEntidService) {
        this.tipUsuPessoaEntidService = tipUsuPessoaEntidService;
    }

    /**
     * {@code POST  /tip-usu-pessoa-entids} : Create a new tipUsuPessoaEntid.
     *
     * @param tipUsuPessoaEntidDTO the tipUsuPessoaEntidDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipUsuPessoaEntidDTO, or with status {@code 400 (Bad Request)} if the tipUsuPessoaEntid has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tip-usu-pessoa-entids")
    public ResponseEntity<TipUsuPessoaEntidDTO> createTipUsuPessoaEntid(@Valid @RequestBody TipUsuPessoaEntidDTO tipUsuPessoaEntidDTO) throws URISyntaxException {
        log.debug("REST request to save TipUsuPessoaEntid : {}", tipUsuPessoaEntidDTO);
        if (tipUsuPessoaEntidDTO.getId() != null) {
            throw new BadRequestAlertException("A new tipUsuPessoaEntid cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipUsuPessoaEntidDTO result = tipUsuPessoaEntidService.save(tipUsuPessoaEntidDTO);
        return ResponseEntity.created(new URI("/api/tip-usu-pessoa-entids/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /tip-usu-pessoa-entids} : Updates an existing tipUsuPessoaEntid.
     *
     * @param tipUsuPessoaEntidDTO the tipUsuPessoaEntidDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipUsuPessoaEntidDTO,
     * or with status {@code 400 (Bad Request)} if the tipUsuPessoaEntidDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipUsuPessoaEntidDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tip-usu-pessoa-entids")
    public ResponseEntity<TipUsuPessoaEntidDTO> updateTipUsuPessoaEntid(@Valid @RequestBody TipUsuPessoaEntidDTO tipUsuPessoaEntidDTO) throws URISyntaxException {
        log.debug("REST request to update TipUsuPessoaEntid : {}", tipUsuPessoaEntidDTO);
        if (tipUsuPessoaEntidDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipUsuPessoaEntidDTO result = tipUsuPessoaEntidService.save(tipUsuPessoaEntidDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tipUsuPessoaEntidDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /tip-usu-pessoa-entids} : get all the tipUsuPessoaEntids.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipUsuPessoaEntids in body.
     */
    @GetMapping("/tip-usu-pessoa-entids")
    public ResponseEntity<List<TipUsuPessoaEntidDTO>> getAllTipUsuPessoaEntids(Pageable pageable) {
        log.debug("REST request to get a page of TipUsuPessoaEntids");
        Page<TipUsuPessoaEntidDTO> page = tipUsuPessoaEntidService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tip-usu-pessoa-entids/:id} : get the "id" tipUsuPessoaEntid.
     *
     * @param id the id of the tipUsuPessoaEntidDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipUsuPessoaEntidDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tip-usu-pessoa-entids/{id}")
    public ResponseEntity<TipUsuPessoaEntidDTO> getTipUsuPessoaEntid(@PathVariable String id) {
        log.debug("REST request to get TipUsuPessoaEntid : {}", id);
        Optional<TipUsuPessoaEntidDTO> tipUsuPessoaEntidDTO = tipUsuPessoaEntidService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipUsuPessoaEntidDTO);
    }

    /**
     * {@code DELETE  /tip-usu-pessoa-entids/:id} : delete the "id" tipUsuPessoaEntid.
     *
     * @param id the id of the tipUsuPessoaEntidDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tip-usu-pessoa-entids/{id}")
    public ResponseEntity<Void> deleteTipUsuPessoaEntid(@PathVariable String id) {
        log.debug("REST request to delete TipUsuPessoaEntid : {}", id);

        tipUsuPessoaEntidService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
