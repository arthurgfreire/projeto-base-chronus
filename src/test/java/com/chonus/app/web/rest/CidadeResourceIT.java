package com.chonus.app.web.rest;

import com.chonus.app.ProjetoBaseChronusApp;
import com.chonus.app.domain.Cidade;
import com.chonus.app.domain.Uf;
import com.chonus.app.repository.CidadeRepository;
import com.chonus.app.service.CidadeService;
import com.chonus.app.service.dto.CidadeDTO;
import com.chonus.app.service.mapper.CidadeMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CidadeResource} REST controller.
 */
@SpringBootTest(classes = ProjetoBaseChronusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CidadeResourceIT {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeMapper cidadeMapper;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private MockMvc restCidadeMockMvc;

    private Cidade cidade;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cidade createEntity() {
        Cidade cidade = new Cidade();
        cidade.setDescricao(DEFAULT_DESCRICAO);
        // Add required entity
        Uf Uf;
        Uf = UfResourceIT.createEntity();
        Uf.setId("fixed-id-for-tests");
        cidade.setUf(Uf);
        return cidade;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cidade createUpdatedEntity() {
        Cidade cidade = new Cidade();
        cidade.setDescricao(UPDATED_DESCRICAO);
        // Add required entity
        Uf Uf;
        Uf = UfResourceIT.createUpdatedEntity();
        Uf.setId("fixed-id-for-tests");
        cidade.setUf(Uf);
        return cidade;
    }

    @BeforeEach
    public void initTest() {
        cidadeRepository.deleteAll();
        cidade = createEntity();
    }

    @Test
    public void createCidade() throws Exception {
        int databaseSizeBeforeCreate = cidadeRepository.findAll().size();
        // Create the Cidade
        CidadeDTO cidadeDTO = cidadeMapper.toDto(cidade);
        restCidadeMockMvc.perform(post("/api/cidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cidadeDTO)))
            .andExpect(status().isCreated());

        // Validate the Cidade in the database
        List<Cidade> cidadeList = cidadeRepository.findAll();
        assertThat(cidadeList).hasSize(databaseSizeBeforeCreate + 1);
        Cidade testCidade = cidadeList.get(cidadeList.size() - 1);
        assertThat(testCidade.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    public void createCidadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cidadeRepository.findAll().size();

        // Create the Cidade with an existing ID
        cidade.setId("existing_id");
        CidadeDTO cidadeDTO = cidadeMapper.toDto(cidade);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCidadeMockMvc.perform(post("/api/cidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cidadeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cidade in the database
        List<Cidade> cidadeList = cidadeRepository.findAll();
        assertThat(cidadeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllCidades() throws Exception {
        // Initialize the database
        cidadeRepository.save(cidade);

        // Get all the cidadeList
        restCidadeMockMvc.perform(get("/api/cidades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cidade.getId())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)));
    }
    
    @Test
    public void getCidade() throws Exception {
        // Initialize the database
        cidadeRepository.save(cidade);

        // Get the cidade
        restCidadeMockMvc.perform(get("/api/cidades/{id}", cidade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cidade.getId()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO));
    }
    @Test
    public void getNonExistingCidade() throws Exception {
        // Get the cidade
        restCidadeMockMvc.perform(get("/api/cidades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCidade() throws Exception {
        // Initialize the database
        cidadeRepository.save(cidade);

        int databaseSizeBeforeUpdate = cidadeRepository.findAll().size();

        // Update the cidade
        Cidade updatedCidade = cidadeRepository.findById(cidade.getId()).get();
        updatedCidade.setDescricao(UPDATED_DESCRICAO);
        CidadeDTO cidadeDTO = cidadeMapper.toDto(updatedCidade);

        restCidadeMockMvc.perform(put("/api/cidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cidadeDTO)))
            .andExpect(status().isOk());

        // Validate the Cidade in the database
        List<Cidade> cidadeList = cidadeRepository.findAll();
        assertThat(cidadeList).hasSize(databaseSizeBeforeUpdate);
        Cidade testCidade = cidadeList.get(cidadeList.size() - 1);
        assertThat(testCidade.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    public void updateNonExistingCidade() throws Exception {
        int databaseSizeBeforeUpdate = cidadeRepository.findAll().size();

        // Create the Cidade
        CidadeDTO cidadeDTO = cidadeMapper.toDto(cidade);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCidadeMockMvc.perform(put("/api/cidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cidadeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cidade in the database
        List<Cidade> cidadeList = cidadeRepository.findAll();
        assertThat(cidadeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCidade() throws Exception {
        // Initialize the database
        cidadeRepository.save(cidade);

        int databaseSizeBeforeDelete = cidadeRepository.findAll().size();

        // Delete the cidade
        restCidadeMockMvc.perform(delete("/api/cidades/{id}", cidade.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cidade> cidadeList = cidadeRepository.findAll();
        assertThat(cidadeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
