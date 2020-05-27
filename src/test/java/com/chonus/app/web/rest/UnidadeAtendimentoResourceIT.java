package com.chonus.app.web.rest;

import com.chonus.app.ProjetoBaseChronusApp;
import com.chonus.app.domain.UnidadeAtendimento;
import com.chonus.app.domain.Endereco;
import com.chonus.app.domain.Entidade;
import com.chonus.app.repository.UnidadeAtendimentoRepository;
import com.chonus.app.service.UnidadeAtendimentoService;
import com.chonus.app.service.dto.UnidadeAtendimentoDTO;
import com.chonus.app.service.mapper.UnidadeAtendimentoMapper;

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
 * Integration tests for the {@link UnidadeAtendimentoResource} REST controller.
 */
@SpringBootTest(classes = ProjetoBaseChronusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UnidadeAtendimentoResourceIT {

    private static final String DEFAULT_RAZAO_SOCIAL = "AAAAAAAAAA";
    private static final String UPDATED_RAZAO_SOCIAL = "BBBBBBBBBB";

    private static final String DEFAULT_NOME_FANTASIA = "AAAAAAAAAA";
    private static final String UPDATED_NOME_FANTASIA = "BBBBBBBBBB";

    @Autowired
    private UnidadeAtendimentoRepository unidadeAtendimentoRepository;

    @Autowired
    private UnidadeAtendimentoMapper unidadeAtendimentoMapper;

    @Autowired
    private UnidadeAtendimentoService unidadeAtendimentoService;

    @Autowired
    private MockMvc restUnidadeAtendimentoMockMvc;

    private UnidadeAtendimento unidadeAtendimento;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UnidadeAtendimento createEntity() {
        UnidadeAtendimento unidadeAtendimento = new UnidadeAtendimento();
        unidadeAtendimento.setRazaoSocial(DEFAULT_RAZAO_SOCIAL);
        unidadeAtendimento.setNomeFantasia(DEFAULT_NOME_FANTASIA);
        // Add required entity
        Endereco Endereco;
        Endereco = EnderecoResourceIT.createEntity();
        Endereco.setId("fixed-id-for-tests");
        unidadeAtendimento.setEndereco(Endereco);
        // Add required entity
        Entidade Entidade;
        Entidade = EntidadeResourceIT.createEntity();
        Entidade.setId("fixed-id-for-tests");
        unidadeAtendimento.setEntidade(Entidade);
        return unidadeAtendimento;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UnidadeAtendimento createUpdatedEntity() {
        UnidadeAtendimento unidadeAtendimento = new UnidadeAtendimento();
        unidadeAtendimento.setRazaoSocial(UPDATED_RAZAO_SOCIAL);
        unidadeAtendimento.setNomeFantasia(UPDATED_NOME_FANTASIA);
        // Add required entity
        Endereco Endereco;
        Endereco = EnderecoResourceIT.createUpdatedEntity();
        Endereco.setId("fixed-id-for-tests");
        unidadeAtendimento.setEndereco(Endereco);
        // Add required entity
        Entidade Entidade;
        Entidade = EntidadeResourceIT.createUpdatedEntity();
        Entidade.setId("fixed-id-for-tests");
        unidadeAtendimento.setEntidade(Entidade);
        return unidadeAtendimento;
    }

    @BeforeEach
    public void initTest() {
        unidadeAtendimentoRepository.deleteAll();
        unidadeAtendimento = createEntity();
    }

    @Test
    public void createUnidadeAtendimento() throws Exception {
        int databaseSizeBeforeCreate = unidadeAtendimentoRepository.findAll().size();
        // Create the UnidadeAtendimento
        UnidadeAtendimentoDTO unidadeAtendimentoDTO = unidadeAtendimentoMapper.toDto(unidadeAtendimento);
        restUnidadeAtendimentoMockMvc.perform(post("/api/unidade-atendimentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unidadeAtendimentoDTO)))
            .andExpect(status().isCreated());

        // Validate the UnidadeAtendimento in the database
        List<UnidadeAtendimento> unidadeAtendimentoList = unidadeAtendimentoRepository.findAll();
        assertThat(unidadeAtendimentoList).hasSize(databaseSizeBeforeCreate + 1);
        UnidadeAtendimento testUnidadeAtendimento = unidadeAtendimentoList.get(unidadeAtendimentoList.size() - 1);
        assertThat(testUnidadeAtendimento.getRazaoSocial()).isEqualTo(DEFAULT_RAZAO_SOCIAL);
        assertThat(testUnidadeAtendimento.getNomeFantasia()).isEqualTo(DEFAULT_NOME_FANTASIA);
    }

    @Test
    public void createUnidadeAtendimentoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = unidadeAtendimentoRepository.findAll().size();

        // Create the UnidadeAtendimento with an existing ID
        unidadeAtendimento.setId("existing_id");
        UnidadeAtendimentoDTO unidadeAtendimentoDTO = unidadeAtendimentoMapper.toDto(unidadeAtendimento);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUnidadeAtendimentoMockMvc.perform(post("/api/unidade-atendimentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unidadeAtendimentoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UnidadeAtendimento in the database
        List<UnidadeAtendimento> unidadeAtendimentoList = unidadeAtendimentoRepository.findAll();
        assertThat(unidadeAtendimentoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkRazaoSocialIsRequired() throws Exception {
        int databaseSizeBeforeTest = unidadeAtendimentoRepository.findAll().size();
        // set the field null
        unidadeAtendimento.setRazaoSocial(null);

        // Create the UnidadeAtendimento, which fails.
        UnidadeAtendimentoDTO unidadeAtendimentoDTO = unidadeAtendimentoMapper.toDto(unidadeAtendimento);


        restUnidadeAtendimentoMockMvc.perform(post("/api/unidade-atendimentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unidadeAtendimentoDTO)))
            .andExpect(status().isBadRequest());

        List<UnidadeAtendimento> unidadeAtendimentoList = unidadeAtendimentoRepository.findAll();
        assertThat(unidadeAtendimentoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllUnidadeAtendimentos() throws Exception {
        // Initialize the database
        unidadeAtendimentoRepository.save(unidadeAtendimento);

        // Get all the unidadeAtendimentoList
        restUnidadeAtendimentoMockMvc.perform(get("/api/unidade-atendimentos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(unidadeAtendimento.getId())))
            .andExpect(jsonPath("$.[*].razaoSocial").value(hasItem(DEFAULT_RAZAO_SOCIAL)))
            .andExpect(jsonPath("$.[*].nomeFantasia").value(hasItem(DEFAULT_NOME_FANTASIA)));
    }
    
    @Test
    public void getUnidadeAtendimento() throws Exception {
        // Initialize the database
        unidadeAtendimentoRepository.save(unidadeAtendimento);

        // Get the unidadeAtendimento
        restUnidadeAtendimentoMockMvc.perform(get("/api/unidade-atendimentos/{id}", unidadeAtendimento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(unidadeAtendimento.getId()))
            .andExpect(jsonPath("$.razaoSocial").value(DEFAULT_RAZAO_SOCIAL))
            .andExpect(jsonPath("$.nomeFantasia").value(DEFAULT_NOME_FANTASIA));
    }
    @Test
    public void getNonExistingUnidadeAtendimento() throws Exception {
        // Get the unidadeAtendimento
        restUnidadeAtendimentoMockMvc.perform(get("/api/unidade-atendimentos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateUnidadeAtendimento() throws Exception {
        // Initialize the database
        unidadeAtendimentoRepository.save(unidadeAtendimento);

        int databaseSizeBeforeUpdate = unidadeAtendimentoRepository.findAll().size();

        // Update the unidadeAtendimento
        UnidadeAtendimento updatedUnidadeAtendimento = unidadeAtendimentoRepository.findById(unidadeAtendimento.getId()).get();
        updatedUnidadeAtendimento.setRazaoSocial(UPDATED_RAZAO_SOCIAL);
        updatedUnidadeAtendimento.setNomeFantasia(UPDATED_NOME_FANTASIA);
        UnidadeAtendimentoDTO unidadeAtendimentoDTO = unidadeAtendimentoMapper.toDto(updatedUnidadeAtendimento);

        restUnidadeAtendimentoMockMvc.perform(put("/api/unidade-atendimentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unidadeAtendimentoDTO)))
            .andExpect(status().isOk());

        // Validate the UnidadeAtendimento in the database
        List<UnidadeAtendimento> unidadeAtendimentoList = unidadeAtendimentoRepository.findAll();
        assertThat(unidadeAtendimentoList).hasSize(databaseSizeBeforeUpdate);
        UnidadeAtendimento testUnidadeAtendimento = unidadeAtendimentoList.get(unidadeAtendimentoList.size() - 1);
        assertThat(testUnidadeAtendimento.getRazaoSocial()).isEqualTo(UPDATED_RAZAO_SOCIAL);
        assertThat(testUnidadeAtendimento.getNomeFantasia()).isEqualTo(UPDATED_NOME_FANTASIA);
    }

    @Test
    public void updateNonExistingUnidadeAtendimento() throws Exception {
        int databaseSizeBeforeUpdate = unidadeAtendimentoRepository.findAll().size();

        // Create the UnidadeAtendimento
        UnidadeAtendimentoDTO unidadeAtendimentoDTO = unidadeAtendimentoMapper.toDto(unidadeAtendimento);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUnidadeAtendimentoMockMvc.perform(put("/api/unidade-atendimentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unidadeAtendimentoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UnidadeAtendimento in the database
        List<UnidadeAtendimento> unidadeAtendimentoList = unidadeAtendimentoRepository.findAll();
        assertThat(unidadeAtendimentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteUnidadeAtendimento() throws Exception {
        // Initialize the database
        unidadeAtendimentoRepository.save(unidadeAtendimento);

        int databaseSizeBeforeDelete = unidadeAtendimentoRepository.findAll().size();

        // Delete the unidadeAtendimento
        restUnidadeAtendimentoMockMvc.perform(delete("/api/unidade-atendimentos/{id}", unidadeAtendimento.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UnidadeAtendimento> unidadeAtendimentoList = unidadeAtendimentoRepository.findAll();
        assertThat(unidadeAtendimentoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
