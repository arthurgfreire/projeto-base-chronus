package com.chonus.app.web.rest;

import com.chonus.app.ProjetoBaseChronusApp;
import com.chonus.app.domain.Endereco;
import com.chonus.app.domain.Pais;
import com.chonus.app.domain.Uf;
import com.chonus.app.domain.Cidade;
import com.chonus.app.repository.EnderecoRepository;
import com.chonus.app.service.EnderecoService;
import com.chonus.app.service.dto.EnderecoDTO;
import com.chonus.app.service.mapper.EnderecoMapper;

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
 * Integration tests for the {@link EnderecoResource} REST controller.
 */
@SpringBootTest(classes = ProjetoBaseChronusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EnderecoResourceIT {

    private static final String DEFAULT_BAIRRO = "AAAAAAAAAA";
    private static final String UPDATED_BAIRRO = "BBBBBBBBBB";

    private static final String DEFAULT_RUA = "AAAAAAAAAA";
    private static final String UPDATED_RUA = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_CEP = "39000-991";
    private static final String UPDATED_CEP = "88459-403";

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private MockMvc restEnderecoMockMvc;

    private Endereco endereco;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Endereco createEntity() {
        Endereco endereco = new Endereco();
        endereco.setBairro(DEFAULT_BAIRRO);
        endereco.setRua(DEFAULT_RUA);
        endereco.setNumero(DEFAULT_NUMERO);
        endereco.setCep(DEFAULT_CEP);
        // Add required entity
        Pais Pais;
        Pais = PaisResourceIT.createEntity();
        Pais.setId("fixed-id-for-tests");
        endereco.setPais(Pais);
        // Add required entity
        Uf Uf;
        Uf = UfResourceIT.createEntity();
        Uf.setId("fixed-id-for-tests");
        endereco.setUf(Uf);
        // Add required entity
        Cidade Cidade;
        Cidade = CidadeResourceIT.createEntity();
        Cidade.setId("fixed-id-for-tests");
        endereco.setCidade(Cidade);
        return endereco;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Endereco createUpdatedEntity() {
        Endereco endereco = new Endereco();
        endereco.setBairro(UPDATED_BAIRRO);
        endereco.setRua(UPDATED_RUA);
        endereco.setNumero(UPDATED_NUMERO);
        endereco.setCep(UPDATED_CEP);
        // Add required entity
        Pais Pais;
        Pais = PaisResourceIT.createUpdatedEntity();
        Pais.setId("fixed-id-for-tests");
        endereco.setPais(Pais);
        // Add required entity
        Uf Uf;
        Uf = UfResourceIT.createUpdatedEntity();
        Uf.setId("fixed-id-for-tests");
        endereco.setUf(Uf);
        // Add required entity
        Cidade Cidade;
        Cidade = CidadeResourceIT.createUpdatedEntity();
        Cidade.setId("fixed-id-for-tests");
        endereco.setCidade(Cidade);
        return endereco;
    }

    @BeforeEach
    public void initTest() {
        enderecoRepository.deleteAll();
        endereco = createEntity();
    }

    @Test
    public void createEndereco() throws Exception {
        int databaseSizeBeforeCreate = enderecoRepository.findAll().size();
        // Create the Endereco
        EnderecoDTO enderecoDTO = enderecoMapper.toDto(endereco);
        restEnderecoMockMvc.perform(post("/api/enderecos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(enderecoDTO)))
            .andExpect(status().isCreated());

        // Validate the Endereco in the database
        List<Endereco> enderecoList = enderecoRepository.findAll();
        assertThat(enderecoList).hasSize(databaseSizeBeforeCreate + 1);
        Endereco testEndereco = enderecoList.get(enderecoList.size() - 1);
        assertThat(testEndereco.getBairro()).isEqualTo(DEFAULT_BAIRRO);
        assertThat(testEndereco.getRua()).isEqualTo(DEFAULT_RUA);
        assertThat(testEndereco.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testEndereco.getCep()).isEqualTo(DEFAULT_CEP);
    }

    @Test
    public void createEnderecoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enderecoRepository.findAll().size();

        // Create the Endereco with an existing ID
        endereco.setId("existing_id");
        EnderecoDTO enderecoDTO = enderecoMapper.toDto(endereco);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnderecoMockMvc.perform(post("/api/enderecos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(enderecoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Endereco in the database
        List<Endereco> enderecoList = enderecoRepository.findAll();
        assertThat(enderecoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkBairroIsRequired() throws Exception {
        int databaseSizeBeforeTest = enderecoRepository.findAll().size();
        // set the field null
        endereco.setBairro(null);

        // Create the Endereco, which fails.
        EnderecoDTO enderecoDTO = enderecoMapper.toDto(endereco);


        restEnderecoMockMvc.perform(post("/api/enderecos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(enderecoDTO)))
            .andExpect(status().isBadRequest());

        List<Endereco> enderecoList = enderecoRepository.findAll();
        assertThat(enderecoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllEnderecos() throws Exception {
        // Initialize the database
        enderecoRepository.save(endereco);

        // Get all the enderecoList
        restEnderecoMockMvc.perform(get("/api/enderecos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(endereco.getId())))
            .andExpect(jsonPath("$.[*].bairro").value(hasItem(DEFAULT_BAIRRO)))
            .andExpect(jsonPath("$.[*].rua").value(hasItem(DEFAULT_RUA)))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].cep").value(hasItem(DEFAULT_CEP)));
    }
    
    @Test
    public void getEndereco() throws Exception {
        // Initialize the database
        enderecoRepository.save(endereco);

        // Get the endereco
        restEnderecoMockMvc.perform(get("/api/enderecos/{id}", endereco.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(endereco.getId()))
            .andExpect(jsonPath("$.bairro").value(DEFAULT_BAIRRO))
            .andExpect(jsonPath("$.rua").value(DEFAULT_RUA))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.cep").value(DEFAULT_CEP));
    }
    @Test
    public void getNonExistingEndereco() throws Exception {
        // Get the endereco
        restEnderecoMockMvc.perform(get("/api/enderecos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEndereco() throws Exception {
        // Initialize the database
        enderecoRepository.save(endereco);

        int databaseSizeBeforeUpdate = enderecoRepository.findAll().size();

        // Update the endereco
        Endereco updatedEndereco = enderecoRepository.findById(endereco.getId()).get();
        updatedEndereco.setBairro(UPDATED_BAIRRO);
        updatedEndereco.setRua(UPDATED_RUA);
        updatedEndereco.setNumero(UPDATED_NUMERO);
        updatedEndereco.setCep(UPDATED_CEP);
        EnderecoDTO enderecoDTO = enderecoMapper.toDto(updatedEndereco);

        restEnderecoMockMvc.perform(put("/api/enderecos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(enderecoDTO)))
            .andExpect(status().isOk());

        // Validate the Endereco in the database
        List<Endereco> enderecoList = enderecoRepository.findAll();
        assertThat(enderecoList).hasSize(databaseSizeBeforeUpdate);
        Endereco testEndereco = enderecoList.get(enderecoList.size() - 1);
        assertThat(testEndereco.getBairro()).isEqualTo(UPDATED_BAIRRO);
        assertThat(testEndereco.getRua()).isEqualTo(UPDATED_RUA);
        assertThat(testEndereco.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testEndereco.getCep()).isEqualTo(UPDATED_CEP);
    }

    @Test
    public void updateNonExistingEndereco() throws Exception {
        int databaseSizeBeforeUpdate = enderecoRepository.findAll().size();

        // Create the Endereco
        EnderecoDTO enderecoDTO = enderecoMapper.toDto(endereco);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnderecoMockMvc.perform(put("/api/enderecos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(enderecoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Endereco in the database
        List<Endereco> enderecoList = enderecoRepository.findAll();
        assertThat(enderecoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteEndereco() throws Exception {
        // Initialize the database
        enderecoRepository.save(endereco);

        int databaseSizeBeforeDelete = enderecoRepository.findAll().size();

        // Delete the endereco
        restEnderecoMockMvc.perform(delete("/api/enderecos/{id}", endereco.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Endereco> enderecoList = enderecoRepository.findAll();
        assertThat(enderecoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
