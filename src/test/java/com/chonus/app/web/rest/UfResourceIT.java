package com.chonus.app.web.rest;

import com.chonus.app.ProjetoBaseChronusApp;
import com.chonus.app.domain.Uf;
import com.chonus.app.domain.Pais;
import com.chonus.app.repository.UfRepository;
import com.chonus.app.service.UfService;
import com.chonus.app.service.dto.UfDTO;
import com.chonus.app.service.mapper.UfMapper;

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
 * Integration tests for the {@link UfResource} REST controller.
 */
@SpringBootTest(classes = ProjetoBaseChronusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UfResourceIT {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private UfRepository ufRepository;

    @Autowired
    private UfMapper ufMapper;

    @Autowired
    private UfService ufService;

    @Autowired
    private MockMvc restUfMockMvc;

    private Uf uf;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Uf createEntity() {
        Uf uf = new Uf();
        uf.setDescricao(DEFAULT_DESCRICAO);
        // Add required entity
        Pais Pais;
        Pais = PaisResourceIT.createEntity();
        Pais.setId("fixed-id-for-tests");
        uf.setPais(Pais);
        return uf;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Uf createUpdatedEntity() {
        Uf uf = new Uf();
        uf.setDescricao(UPDATED_DESCRICAO);
        // Add required entity
        Pais Pais;
        Pais = PaisResourceIT.createUpdatedEntity();
        Pais.setId("fixed-id-for-tests");
        uf.setPais(Pais);
        return uf;
    }

    @BeforeEach
    public void initTest() {
        ufRepository.deleteAll();
        uf = createEntity();
    }

    @Test
    public void createUf() throws Exception {
        int databaseSizeBeforeCreate = ufRepository.findAll().size();
        // Create the Uf
        UfDTO ufDTO = ufMapper.toDto(uf);
        restUfMockMvc.perform(post("/api/ufs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ufDTO)))
            .andExpect(status().isCreated());

        // Validate the Uf in the database
        List<Uf> ufList = ufRepository.findAll();
        assertThat(ufList).hasSize(databaseSizeBeforeCreate + 1);
        Uf testUf = ufList.get(ufList.size() - 1);
        assertThat(testUf.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    public void createUfWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ufRepository.findAll().size();

        // Create the Uf with an existing ID
        uf.setId("existing_id");
        UfDTO ufDTO = ufMapper.toDto(uf);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUfMockMvc.perform(post("/api/ufs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ufDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Uf in the database
        List<Uf> ufList = ufRepository.findAll();
        assertThat(ufList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllUfs() throws Exception {
        // Initialize the database
        ufRepository.save(uf);

        // Get all the ufList
        restUfMockMvc.perform(get("/api/ufs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uf.getId())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)));
    }
    
    @Test
    public void getUf() throws Exception {
        // Initialize the database
        ufRepository.save(uf);

        // Get the uf
        restUfMockMvc.perform(get("/api/ufs/{id}", uf.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(uf.getId()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO));
    }
    @Test
    public void getNonExistingUf() throws Exception {
        // Get the uf
        restUfMockMvc.perform(get("/api/ufs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateUf() throws Exception {
        // Initialize the database
        ufRepository.save(uf);

        int databaseSizeBeforeUpdate = ufRepository.findAll().size();

        // Update the uf
        Uf updatedUf = ufRepository.findById(uf.getId()).get();
        updatedUf.setDescricao(UPDATED_DESCRICAO);
        UfDTO ufDTO = ufMapper.toDto(updatedUf);

        restUfMockMvc.perform(put("/api/ufs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ufDTO)))
            .andExpect(status().isOk());

        // Validate the Uf in the database
        List<Uf> ufList = ufRepository.findAll();
        assertThat(ufList).hasSize(databaseSizeBeforeUpdate);
        Uf testUf = ufList.get(ufList.size() - 1);
        assertThat(testUf.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    public void updateNonExistingUf() throws Exception {
        int databaseSizeBeforeUpdate = ufRepository.findAll().size();

        // Create the Uf
        UfDTO ufDTO = ufMapper.toDto(uf);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUfMockMvc.perform(put("/api/ufs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ufDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Uf in the database
        List<Uf> ufList = ufRepository.findAll();
        assertThat(ufList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteUf() throws Exception {
        // Initialize the database
        ufRepository.save(uf);

        int databaseSizeBeforeDelete = ufRepository.findAll().size();

        // Delete the uf
        restUfMockMvc.perform(delete("/api/ufs/{id}", uf.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Uf> ufList = ufRepository.findAll();
        assertThat(ufList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
