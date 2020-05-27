package com.chonus.app.web.rest;

import com.chonus.app.ProjetoBaseChronusApp;
import com.chonus.app.domain.Raca;
import com.chonus.app.repository.RacaRepository;
import com.chonus.app.service.RacaService;
import com.chonus.app.service.dto.RacaDTO;
import com.chonus.app.service.mapper.RacaMapper;

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
 * Integration tests for the {@link RacaResource} REST controller.
 */
@SpringBootTest(classes = ProjetoBaseChronusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RacaResourceIT {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private RacaRepository racaRepository;

    @Autowired
    private RacaMapper racaMapper;

    @Autowired
    private RacaService racaService;

    @Autowired
    private MockMvc restRacaMockMvc;

    private Raca raca;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Raca createEntity() {
        Raca raca = new Raca();
        raca.setDescricao(DEFAULT_DESCRICAO);
        return raca;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Raca createUpdatedEntity() {
        Raca raca = new Raca();
        raca.setDescricao(UPDATED_DESCRICAO);
        return raca;
    }

    @BeforeEach
    public void initTest() {
        racaRepository.deleteAll();
        raca = createEntity();
    }

    @Test
    public void createRaca() throws Exception {
        int databaseSizeBeforeCreate = racaRepository.findAll().size();
        // Create the Raca
        RacaDTO racaDTO = racaMapper.toDto(raca);
        restRacaMockMvc.perform(post("/api/racas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(racaDTO)))
            .andExpect(status().isCreated());

        // Validate the Raca in the database
        List<Raca> racaList = racaRepository.findAll();
        assertThat(racaList).hasSize(databaseSizeBeforeCreate + 1);
        Raca testRaca = racaList.get(racaList.size() - 1);
        assertThat(testRaca.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    public void createRacaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = racaRepository.findAll().size();

        // Create the Raca with an existing ID
        raca.setId("existing_id");
        RacaDTO racaDTO = racaMapper.toDto(raca);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRacaMockMvc.perform(post("/api/racas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(racaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Raca in the database
        List<Raca> racaList = racaRepository.findAll();
        assertThat(racaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllRacas() throws Exception {
        // Initialize the database
        racaRepository.save(raca);

        // Get all the racaList
        restRacaMockMvc.perform(get("/api/racas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(raca.getId())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)));
    }
    
    @Test
    public void getRaca() throws Exception {
        // Initialize the database
        racaRepository.save(raca);

        // Get the raca
        restRacaMockMvc.perform(get("/api/racas/{id}", raca.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(raca.getId()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO));
    }
    @Test
    public void getNonExistingRaca() throws Exception {
        // Get the raca
        restRacaMockMvc.perform(get("/api/racas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateRaca() throws Exception {
        // Initialize the database
        racaRepository.save(raca);

        int databaseSizeBeforeUpdate = racaRepository.findAll().size();

        // Update the raca
        Raca updatedRaca = racaRepository.findById(raca.getId()).get();
        updatedRaca.setDescricao(UPDATED_DESCRICAO);
        RacaDTO racaDTO = racaMapper.toDto(updatedRaca);

        restRacaMockMvc.perform(put("/api/racas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(racaDTO)))
            .andExpect(status().isOk());

        // Validate the Raca in the database
        List<Raca> racaList = racaRepository.findAll();
        assertThat(racaList).hasSize(databaseSizeBeforeUpdate);
        Raca testRaca = racaList.get(racaList.size() - 1);
        assertThat(testRaca.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    public void updateNonExistingRaca() throws Exception {
        int databaseSizeBeforeUpdate = racaRepository.findAll().size();

        // Create the Raca
        RacaDTO racaDTO = racaMapper.toDto(raca);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRacaMockMvc.perform(put("/api/racas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(racaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Raca in the database
        List<Raca> racaList = racaRepository.findAll();
        assertThat(racaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteRaca() throws Exception {
        // Initialize the database
        racaRepository.save(raca);

        int databaseSizeBeforeDelete = racaRepository.findAll().size();

        // Delete the raca
        restRacaMockMvc.perform(delete("/api/racas/{id}", raca.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Raca> racaList = racaRepository.findAll();
        assertThat(racaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
