package com.chonus.app.web.rest;

import com.chonus.app.ProjetoBaseChronusApp;
import com.chonus.app.domain.Pais;
import com.chonus.app.repository.PaisRepository;
import com.chonus.app.service.PaisService;
import com.chonus.app.service.dto.PaisDTO;
import com.chonus.app.service.mapper.PaisMapper;

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
 * Integration tests for the {@link PaisResource} REST controller.
 */
@SpringBootTest(classes = ProjetoBaseChronusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PaisResourceIT {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private PaisMapper paisMapper;

    @Autowired
    private PaisService paisService;

    @Autowired
    private MockMvc restPaisMockMvc;

    private Pais pais;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pais createEntity() {
        Pais pais = new Pais();
        pais.setDescricao(DEFAULT_DESCRICAO);
        return pais;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pais createUpdatedEntity() {
        Pais pais = new Pais();
        pais.setDescricao(UPDATED_DESCRICAO);
        return pais;
    }

    @BeforeEach
    public void initTest() {
        paisRepository.deleteAll();
        pais = createEntity();
    }

    @Test
    public void createPais() throws Exception {
        int databaseSizeBeforeCreate = paisRepository.findAll().size();
        // Create the Pais
        PaisDTO paisDTO = paisMapper.toDto(pais);
        restPaisMockMvc.perform(post("/api/pais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paisDTO)))
            .andExpect(status().isCreated());

        // Validate the Pais in the database
        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeCreate + 1);
        Pais testPais = paisList.get(paisList.size() - 1);
        assertThat(testPais.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    public void createPaisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paisRepository.findAll().size();

        // Create the Pais with an existing ID
        pais.setId("existing_id");
        PaisDTO paisDTO = paisMapper.toDto(pais);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaisMockMvc.perform(post("/api/pais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pais in the database
        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllPais() throws Exception {
        // Initialize the database
        paisRepository.save(pais);

        // Get all the paisList
        restPaisMockMvc.perform(get("/api/pais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pais.getId())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)));
    }
    
    @Test
    public void getPais() throws Exception {
        // Initialize the database
        paisRepository.save(pais);

        // Get the pais
        restPaisMockMvc.perform(get("/api/pais/{id}", pais.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pais.getId()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO));
    }
    @Test
    public void getNonExistingPais() throws Exception {
        // Get the pais
        restPaisMockMvc.perform(get("/api/pais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updatePais() throws Exception {
        // Initialize the database
        paisRepository.save(pais);

        int databaseSizeBeforeUpdate = paisRepository.findAll().size();

        // Update the pais
        Pais updatedPais = paisRepository.findById(pais.getId()).get();
        updatedPais.setDescricao(UPDATED_DESCRICAO);
        PaisDTO paisDTO = paisMapper.toDto(updatedPais);

        restPaisMockMvc.perform(put("/api/pais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paisDTO)))
            .andExpect(status().isOk());

        // Validate the Pais in the database
        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeUpdate);
        Pais testPais = paisList.get(paisList.size() - 1);
        assertThat(testPais.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    public void updateNonExistingPais() throws Exception {
        int databaseSizeBeforeUpdate = paisRepository.findAll().size();

        // Create the Pais
        PaisDTO paisDTO = paisMapper.toDto(pais);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaisMockMvc.perform(put("/api/pais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pais in the database
        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deletePais() throws Exception {
        // Initialize the database
        paisRepository.save(pais);

        int databaseSizeBeforeDelete = paisRepository.findAll().size();

        // Delete the pais
        restPaisMockMvc.perform(delete("/api/pais/{id}", pais.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
