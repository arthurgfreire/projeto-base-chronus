package com.chonus.app.web.rest;

import com.chonus.app.ProjetoBaseChronusApp;
import com.chonus.app.domain.TipUsuPessoaEntid;
import com.chonus.app.domain.Pessoa;
import com.chonus.app.domain.Entidade;
import com.chonus.app.repository.TipUsuPessoaEntidRepository;
import com.chonus.app.service.TipUsuPessoaEntidService;
import com.chonus.app.service.dto.TipUsuPessoaEntidDTO;
import com.chonus.app.service.mapper.TipUsuPessoaEntidMapper;

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
 * Integration tests for the {@link TipUsuPessoaEntidResource} REST controller.
 */
@SpringBootTest(classes = ProjetoBaseChronusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TipUsuPessoaEntidResourceIT {

    @Autowired
    private TipUsuPessoaEntidRepository tipUsuPessoaEntidRepository;

    @Autowired
    private TipUsuPessoaEntidMapper tipUsuPessoaEntidMapper;

    @Autowired
    private TipUsuPessoaEntidService tipUsuPessoaEntidService;

    @Autowired
    private MockMvc restTipUsuPessoaEntidMockMvc;

    private TipUsuPessoaEntid tipUsuPessoaEntid;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipUsuPessoaEntid createEntity() {
        TipUsuPessoaEntid tipUsuPessoaEntid = new TipUsuPessoaEntid();
        // Add required entity
        Pessoa Pessoa;
        Pessoa = PessoaResourceIT.createEntity();
        Pessoa.setId("fixed-id-for-tests");
        tipUsuPessoaEntid.setPessoa(Pessoa);
        // Add required entity
        Entidade Entidade;
        Entidade = EntidadeResourceIT.createEntity();
        Entidade.setId("fixed-id-for-tests");
        tipUsuPessoaEntid.setEntidade(Entidade);
        return tipUsuPessoaEntid;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipUsuPessoaEntid createUpdatedEntity() {
        TipUsuPessoaEntid tipUsuPessoaEntid = new TipUsuPessoaEntid();
        // Add required entity
        Pessoa Pessoa;
        Pessoa = PessoaResourceIT.createUpdatedEntity();
        Pessoa.setId("fixed-id-for-tests");
        tipUsuPessoaEntid.setPessoa(Pessoa);
        // Add required entity
        Entidade Entidade;
        Entidade = EntidadeResourceIT.createUpdatedEntity();
        Entidade.setId("fixed-id-for-tests");
        tipUsuPessoaEntid.setEntidade(Entidade);
        return tipUsuPessoaEntid;
    }

    @BeforeEach
    public void initTest() {
        tipUsuPessoaEntidRepository.deleteAll();
        tipUsuPessoaEntid = createEntity();
    }

    @Test
    public void createTipUsuPessoaEntid() throws Exception {
        int databaseSizeBeforeCreate = tipUsuPessoaEntidRepository.findAll().size();
        // Create the TipUsuPessoaEntid
        TipUsuPessoaEntidDTO tipUsuPessoaEntidDTO = tipUsuPessoaEntidMapper.toDto(tipUsuPessoaEntid);
        restTipUsuPessoaEntidMockMvc.perform(post("/api/tip-usu-pessoa-entids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipUsuPessoaEntidDTO)))
            .andExpect(status().isCreated());

        // Validate the TipUsuPessoaEntid in the database
        List<TipUsuPessoaEntid> tipUsuPessoaEntidList = tipUsuPessoaEntidRepository.findAll();
        assertThat(tipUsuPessoaEntidList).hasSize(databaseSizeBeforeCreate + 1);
        TipUsuPessoaEntid testTipUsuPessoaEntid = tipUsuPessoaEntidList.get(tipUsuPessoaEntidList.size() - 1);
    }

    @Test
    public void createTipUsuPessoaEntidWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipUsuPessoaEntidRepository.findAll().size();

        // Create the TipUsuPessoaEntid with an existing ID
        tipUsuPessoaEntid.setId("existing_id");
        TipUsuPessoaEntidDTO tipUsuPessoaEntidDTO = tipUsuPessoaEntidMapper.toDto(tipUsuPessoaEntid);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipUsuPessoaEntidMockMvc.perform(post("/api/tip-usu-pessoa-entids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipUsuPessoaEntidDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipUsuPessoaEntid in the database
        List<TipUsuPessoaEntid> tipUsuPessoaEntidList = tipUsuPessoaEntidRepository.findAll();
        assertThat(tipUsuPessoaEntidList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllTipUsuPessoaEntids() throws Exception {
        // Initialize the database
        tipUsuPessoaEntidRepository.save(tipUsuPessoaEntid);

        // Get all the tipUsuPessoaEntidList
        restTipUsuPessoaEntidMockMvc.perform(get("/api/tip-usu-pessoa-entids?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipUsuPessoaEntid.getId())));
    }
    
    @Test
    public void getTipUsuPessoaEntid() throws Exception {
        // Initialize the database
        tipUsuPessoaEntidRepository.save(tipUsuPessoaEntid);

        // Get the tipUsuPessoaEntid
        restTipUsuPessoaEntidMockMvc.perform(get("/api/tip-usu-pessoa-entids/{id}", tipUsuPessoaEntid.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tipUsuPessoaEntid.getId()));
    }
    @Test
    public void getNonExistingTipUsuPessoaEntid() throws Exception {
        // Get the tipUsuPessoaEntid
        restTipUsuPessoaEntidMockMvc.perform(get("/api/tip-usu-pessoa-entids/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTipUsuPessoaEntid() throws Exception {
        // Initialize the database
        tipUsuPessoaEntidRepository.save(tipUsuPessoaEntid);

        int databaseSizeBeforeUpdate = tipUsuPessoaEntidRepository.findAll().size();

        // Update the tipUsuPessoaEntid
        TipUsuPessoaEntid updatedTipUsuPessoaEntid = tipUsuPessoaEntidRepository.findById(tipUsuPessoaEntid.getId()).get();
        TipUsuPessoaEntidDTO tipUsuPessoaEntidDTO = tipUsuPessoaEntidMapper.toDto(updatedTipUsuPessoaEntid);

        restTipUsuPessoaEntidMockMvc.perform(put("/api/tip-usu-pessoa-entids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipUsuPessoaEntidDTO)))
            .andExpect(status().isOk());

        // Validate the TipUsuPessoaEntid in the database
        List<TipUsuPessoaEntid> tipUsuPessoaEntidList = tipUsuPessoaEntidRepository.findAll();
        assertThat(tipUsuPessoaEntidList).hasSize(databaseSizeBeforeUpdate);
        TipUsuPessoaEntid testTipUsuPessoaEntid = tipUsuPessoaEntidList.get(tipUsuPessoaEntidList.size() - 1);
    }

    @Test
    public void updateNonExistingTipUsuPessoaEntid() throws Exception {
        int databaseSizeBeforeUpdate = tipUsuPessoaEntidRepository.findAll().size();

        // Create the TipUsuPessoaEntid
        TipUsuPessoaEntidDTO tipUsuPessoaEntidDTO = tipUsuPessoaEntidMapper.toDto(tipUsuPessoaEntid);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipUsuPessoaEntidMockMvc.perform(put("/api/tip-usu-pessoa-entids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipUsuPessoaEntidDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipUsuPessoaEntid in the database
        List<TipUsuPessoaEntid> tipUsuPessoaEntidList = tipUsuPessoaEntidRepository.findAll();
        assertThat(tipUsuPessoaEntidList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteTipUsuPessoaEntid() throws Exception {
        // Initialize the database
        tipUsuPessoaEntidRepository.save(tipUsuPessoaEntid);

        int databaseSizeBeforeDelete = tipUsuPessoaEntidRepository.findAll().size();

        // Delete the tipUsuPessoaEntid
        restTipUsuPessoaEntidMockMvc.perform(delete("/api/tip-usu-pessoa-entids/{id}", tipUsuPessoaEntid.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TipUsuPessoaEntid> tipUsuPessoaEntidList = tipUsuPessoaEntidRepository.findAll();
        assertThat(tipUsuPessoaEntidList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
