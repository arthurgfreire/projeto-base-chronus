package com.chonus.app.web.rest;

import com.chonus.app.ProjetoBaseChronusApp;
import com.chonus.app.domain.Entidade;
import com.chonus.app.domain.Endereco;
import com.chonus.app.repository.EntidadeRepository;
import com.chonus.app.service.EntidadeService;
import com.chonus.app.service.dto.EntidadeDTO;
import com.chonus.app.service.mapper.EntidadeMapper;

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

import com.chonus.app.domain.enumeration.TipoCadastro;
import com.chonus.app.domain.enumeration.TipoUnidade;
import com.chonus.app.domain.enumeration.TipoLicneca;
/**
 * Integration tests for the {@link EntidadeResource} REST controller.
 */
@SpringBootTest(classes = ProjetoBaseChronusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EntidadeResourceIT {

    private static final TipoCadastro DEFAULT_TIPO_CADASTRO = TipoCadastro.GOVERNO;
    private static final TipoCadastro UPDATED_TIPO_CADASTRO = TipoCadastro.ENTIDADE_MEDICA;

    private static final TipoUnidade DEFAULT_TIPO_UNIDADE = TipoUnidade.PUBLICA;
    private static final TipoUnidade UPDATED_TIPO_UNIDADE = TipoUnidade.PRIVADA;

    private static final String DEFAULT_RAZAO_SOCIAL = "AAAAAAAAAA";
    private static final String UPDATED_RAZAO_SOCIAL = "BBBBBBBBBB";

    private static final String DEFAULT_NOME_FANTASIA = "AAAAAAAAAA";
    private static final String UPDATED_NOME_FANTASIA = "BBBBBBBBBB";

    private static final String DEFAULT_CNPJ = "AAAAAAAAAA";
    private static final String UPDATED_CNPJ = "BBBBBBBBBB";

    private static final String DEFAULT_INSCRICAO_ESTADUAL = "AAAAAAAAAA";
    private static final String UPDATED_INSCRICAO_ESTADUAL = "BBBBBBBBBB";

    private static final String DEFAULT_INSCRICAO_MUNICIPAL = "AAAAAAAAAA";
    private static final String UPDATED_INSCRICAO_MUNICIPAL = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONE = "790 2162-6811";
    private static final String UPDATED_TELEFONE = "22051-9796";

    private static final String DEFAULT_EMAIL = "na@92prn.bxw";
    private static final String UPDATED_EMAIL = "tq@ue.sc";

    private static final TipoLicneca DEFAULT_TIPO_LICNECA = TipoLicneca.GOV;
    private static final TipoLicneca UPDATED_TIPO_LICNECA = TipoLicneca.PRIVADA;

    private static final Long DEFAULT_QUANT_LICENCAS = 1L;
    private static final Long UPDATED_QUANT_LICENCAS = 2L;

    @Autowired
    private EntidadeRepository entidadeRepository;

    @Autowired
    private EntidadeMapper entidadeMapper;

    @Autowired
    private EntidadeService entidadeService;

    @Autowired
    private MockMvc restEntidadeMockMvc;

    private Entidade entidade;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Entidade createEntity() {
        Entidade entidade = new Entidade();
        entidade.setTipoCadastro(DEFAULT_TIPO_CADASTRO);
        entidade.setTipoUnidade(DEFAULT_TIPO_UNIDADE);
        entidade.setRazaoSocial(DEFAULT_RAZAO_SOCIAL);
        entidade.setNomeFantasia(DEFAULT_NOME_FANTASIA);
        entidade.setCnpj(DEFAULT_CNPJ);
        entidade.setInscricaoEstadual(DEFAULT_INSCRICAO_ESTADUAL);
        entidade.setInscricaoMunicipal(DEFAULT_INSCRICAO_MUNICIPAL);
        entidade.setTelefone(DEFAULT_TELEFONE);
        entidade.setEmail(DEFAULT_EMAIL);
        entidade.setTipoLicneca(DEFAULT_TIPO_LICNECA);
        entidade.setQuantLicencas(DEFAULT_QUANT_LICENCAS);
        // Add required entity
        Endereco Endereco;
        Endereco = EnderecoResourceIT.createEntity();
        Endereco.setId("fixed-id-for-tests");
        entidade.setEndereco(Endereco);
        return entidade;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Entidade createUpdatedEntity() {
        Entidade entidade = new Entidade();
        entidade.setTipoCadastro(UPDATED_TIPO_CADASTRO);
        entidade.setTipoUnidade(UPDATED_TIPO_UNIDADE);
        entidade.setRazaoSocial(UPDATED_RAZAO_SOCIAL);
        entidade.setNomeFantasia(UPDATED_NOME_FANTASIA);
        entidade.setCnpj(UPDATED_CNPJ);
        entidade.setInscricaoEstadual(UPDATED_INSCRICAO_ESTADUAL);
        entidade.setInscricaoMunicipal(UPDATED_INSCRICAO_MUNICIPAL);
        entidade.setTelefone(UPDATED_TELEFONE);
        entidade.setEmail(UPDATED_EMAIL);
        entidade.setTipoLicneca(UPDATED_TIPO_LICNECA);
        entidade.setQuantLicencas(UPDATED_QUANT_LICENCAS);
        // Add required entity
        Endereco Endereco;
        Endereco = EnderecoResourceIT.createUpdatedEntity();
        Endereco.setId("fixed-id-for-tests");
        entidade.setEndereco(Endereco);
        return entidade;
    }

    @BeforeEach
    public void initTest() {
        entidadeRepository.deleteAll();
        entidade = createEntity();
    }

    @Test
    public void createEntidade() throws Exception {
        int databaseSizeBeforeCreate = entidadeRepository.findAll().size();
        // Create the Entidade
        EntidadeDTO entidadeDTO = entidadeMapper.toDto(entidade);
        restEntidadeMockMvc.perform(post("/api/entidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entidadeDTO)))
            .andExpect(status().isCreated());

        // Validate the Entidade in the database
        List<Entidade> entidadeList = entidadeRepository.findAll();
        assertThat(entidadeList).hasSize(databaseSizeBeforeCreate + 1);
        Entidade testEntidade = entidadeList.get(entidadeList.size() - 1);
        assertThat(testEntidade.getTipoCadastro()).isEqualTo(DEFAULT_TIPO_CADASTRO);
        assertThat(testEntidade.getTipoUnidade()).isEqualTo(DEFAULT_TIPO_UNIDADE);
        assertThat(testEntidade.getRazaoSocial()).isEqualTo(DEFAULT_RAZAO_SOCIAL);
        assertThat(testEntidade.getNomeFantasia()).isEqualTo(DEFAULT_NOME_FANTASIA);
        assertThat(testEntidade.getCnpj()).isEqualTo(DEFAULT_CNPJ);
        assertThat(testEntidade.getInscricaoEstadual()).isEqualTo(DEFAULT_INSCRICAO_ESTADUAL);
        assertThat(testEntidade.getInscricaoMunicipal()).isEqualTo(DEFAULT_INSCRICAO_MUNICIPAL);
        assertThat(testEntidade.getTelefone()).isEqualTo(DEFAULT_TELEFONE);
        assertThat(testEntidade.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEntidade.getTipoLicneca()).isEqualTo(DEFAULT_TIPO_LICNECA);
        assertThat(testEntidade.getQuantLicencas()).isEqualTo(DEFAULT_QUANT_LICENCAS);
    }

    @Test
    public void createEntidadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entidadeRepository.findAll().size();

        // Create the Entidade with an existing ID
        entidade.setId("existing_id");
        EntidadeDTO entidadeDTO = entidadeMapper.toDto(entidade);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntidadeMockMvc.perform(post("/api/entidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entidadeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Entidade in the database
        List<Entidade> entidadeList = entidadeRepository.findAll();
        assertThat(entidadeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkTipoCadastroIsRequired() throws Exception {
        int databaseSizeBeforeTest = entidadeRepository.findAll().size();
        // set the field null
        entidade.setTipoCadastro(null);

        // Create the Entidade, which fails.
        EntidadeDTO entidadeDTO = entidadeMapper.toDto(entidade);


        restEntidadeMockMvc.perform(post("/api/entidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entidadeDTO)))
            .andExpect(status().isBadRequest());

        List<Entidade> entidadeList = entidadeRepository.findAll();
        assertThat(entidadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTipoUnidadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = entidadeRepository.findAll().size();
        // set the field null
        entidade.setTipoUnidade(null);

        // Create the Entidade, which fails.
        EntidadeDTO entidadeDTO = entidadeMapper.toDto(entidade);


        restEntidadeMockMvc.perform(post("/api/entidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entidadeDTO)))
            .andExpect(status().isBadRequest());

        List<Entidade> entidadeList = entidadeRepository.findAll();
        assertThat(entidadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkRazaoSocialIsRequired() throws Exception {
        int databaseSizeBeforeTest = entidadeRepository.findAll().size();
        // set the field null
        entidade.setRazaoSocial(null);

        // Create the Entidade, which fails.
        EntidadeDTO entidadeDTO = entidadeMapper.toDto(entidade);


        restEntidadeMockMvc.perform(post("/api/entidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entidadeDTO)))
            .andExpect(status().isBadRequest());

        List<Entidade> entidadeList = entidadeRepository.findAll();
        assertThat(entidadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTelefoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = entidadeRepository.findAll().size();
        // set the field null
        entidade.setTelefone(null);

        // Create the Entidade, which fails.
        EntidadeDTO entidadeDTO = entidadeMapper.toDto(entidade);


        restEntidadeMockMvc.perform(post("/api/entidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entidadeDTO)))
            .andExpect(status().isBadRequest());

        List<Entidade> entidadeList = entidadeRepository.findAll();
        assertThat(entidadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTipoLicnecaIsRequired() throws Exception {
        int databaseSizeBeforeTest = entidadeRepository.findAll().size();
        // set the field null
        entidade.setTipoLicneca(null);

        // Create the Entidade, which fails.
        EntidadeDTO entidadeDTO = entidadeMapper.toDto(entidade);


        restEntidadeMockMvc.perform(post("/api/entidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entidadeDTO)))
            .andExpect(status().isBadRequest());

        List<Entidade> entidadeList = entidadeRepository.findAll();
        assertThat(entidadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllEntidades() throws Exception {
        // Initialize the database
        entidadeRepository.save(entidade);

        // Get all the entidadeList
        restEntidadeMockMvc.perform(get("/api/entidades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entidade.getId())))
            .andExpect(jsonPath("$.[*].tipoCadastro").value(hasItem(DEFAULT_TIPO_CADASTRO.toString())))
            .andExpect(jsonPath("$.[*].tipoUnidade").value(hasItem(DEFAULT_TIPO_UNIDADE.toString())))
            .andExpect(jsonPath("$.[*].razaoSocial").value(hasItem(DEFAULT_RAZAO_SOCIAL)))
            .andExpect(jsonPath("$.[*].nomeFantasia").value(hasItem(DEFAULT_NOME_FANTASIA)))
            .andExpect(jsonPath("$.[*].cnpj").value(hasItem(DEFAULT_CNPJ)))
            .andExpect(jsonPath("$.[*].inscricaoEstadual").value(hasItem(DEFAULT_INSCRICAO_ESTADUAL)))
            .andExpect(jsonPath("$.[*].inscricaoMunicipal").value(hasItem(DEFAULT_INSCRICAO_MUNICIPAL)))
            .andExpect(jsonPath("$.[*].telefone").value(hasItem(DEFAULT_TELEFONE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].tipoLicneca").value(hasItem(DEFAULT_TIPO_LICNECA.toString())))
            .andExpect(jsonPath("$.[*].quantLicencas").value(hasItem(DEFAULT_QUANT_LICENCAS.intValue())));
    }
    
    @Test
    public void getEntidade() throws Exception {
        // Initialize the database
        entidadeRepository.save(entidade);

        // Get the entidade
        restEntidadeMockMvc.perform(get("/api/entidades/{id}", entidade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entidade.getId()))
            .andExpect(jsonPath("$.tipoCadastro").value(DEFAULT_TIPO_CADASTRO.toString()))
            .andExpect(jsonPath("$.tipoUnidade").value(DEFAULT_TIPO_UNIDADE.toString()))
            .andExpect(jsonPath("$.razaoSocial").value(DEFAULT_RAZAO_SOCIAL))
            .andExpect(jsonPath("$.nomeFantasia").value(DEFAULT_NOME_FANTASIA))
            .andExpect(jsonPath("$.cnpj").value(DEFAULT_CNPJ))
            .andExpect(jsonPath("$.inscricaoEstadual").value(DEFAULT_INSCRICAO_ESTADUAL))
            .andExpect(jsonPath("$.inscricaoMunicipal").value(DEFAULT_INSCRICAO_MUNICIPAL))
            .andExpect(jsonPath("$.telefone").value(DEFAULT_TELEFONE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.tipoLicneca").value(DEFAULT_TIPO_LICNECA.toString()))
            .andExpect(jsonPath("$.quantLicencas").value(DEFAULT_QUANT_LICENCAS.intValue()));
    }
    @Test
    public void getNonExistingEntidade() throws Exception {
        // Get the entidade
        restEntidadeMockMvc.perform(get("/api/entidades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEntidade() throws Exception {
        // Initialize the database
        entidadeRepository.save(entidade);

        int databaseSizeBeforeUpdate = entidadeRepository.findAll().size();

        // Update the entidade
        Entidade updatedEntidade = entidadeRepository.findById(entidade.getId()).get();
        updatedEntidade.setTipoCadastro(UPDATED_TIPO_CADASTRO);
        updatedEntidade.setTipoUnidade(UPDATED_TIPO_UNIDADE);
        updatedEntidade.setRazaoSocial(UPDATED_RAZAO_SOCIAL);
        updatedEntidade.setNomeFantasia(UPDATED_NOME_FANTASIA);
        updatedEntidade.setCnpj(UPDATED_CNPJ);
        updatedEntidade.setInscricaoEstadual(UPDATED_INSCRICAO_ESTADUAL);
        updatedEntidade.setInscricaoMunicipal(UPDATED_INSCRICAO_MUNICIPAL);
        updatedEntidade.setTelefone(UPDATED_TELEFONE);
        updatedEntidade.setEmail(UPDATED_EMAIL);
        updatedEntidade.setTipoLicneca(UPDATED_TIPO_LICNECA);
        updatedEntidade.setQuantLicencas(UPDATED_QUANT_LICENCAS);
        EntidadeDTO entidadeDTO = entidadeMapper.toDto(updatedEntidade);

        restEntidadeMockMvc.perform(put("/api/entidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entidadeDTO)))
            .andExpect(status().isOk());

        // Validate the Entidade in the database
        List<Entidade> entidadeList = entidadeRepository.findAll();
        assertThat(entidadeList).hasSize(databaseSizeBeforeUpdate);
        Entidade testEntidade = entidadeList.get(entidadeList.size() - 1);
        assertThat(testEntidade.getTipoCadastro()).isEqualTo(UPDATED_TIPO_CADASTRO);
        assertThat(testEntidade.getTipoUnidade()).isEqualTo(UPDATED_TIPO_UNIDADE);
        assertThat(testEntidade.getRazaoSocial()).isEqualTo(UPDATED_RAZAO_SOCIAL);
        assertThat(testEntidade.getNomeFantasia()).isEqualTo(UPDATED_NOME_FANTASIA);
        assertThat(testEntidade.getCnpj()).isEqualTo(UPDATED_CNPJ);
        assertThat(testEntidade.getInscricaoEstadual()).isEqualTo(UPDATED_INSCRICAO_ESTADUAL);
        assertThat(testEntidade.getInscricaoMunicipal()).isEqualTo(UPDATED_INSCRICAO_MUNICIPAL);
        assertThat(testEntidade.getTelefone()).isEqualTo(UPDATED_TELEFONE);
        assertThat(testEntidade.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEntidade.getTipoLicneca()).isEqualTo(UPDATED_TIPO_LICNECA);
        assertThat(testEntidade.getQuantLicencas()).isEqualTo(UPDATED_QUANT_LICENCAS);
    }

    @Test
    public void updateNonExistingEntidade() throws Exception {
        int databaseSizeBeforeUpdate = entidadeRepository.findAll().size();

        // Create the Entidade
        EntidadeDTO entidadeDTO = entidadeMapper.toDto(entidade);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntidadeMockMvc.perform(put("/api/entidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entidadeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Entidade in the database
        List<Entidade> entidadeList = entidadeRepository.findAll();
        assertThat(entidadeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteEntidade() throws Exception {
        // Initialize the database
        entidadeRepository.save(entidade);

        int databaseSizeBeforeDelete = entidadeRepository.findAll().size();

        // Delete the entidade
        restEntidadeMockMvc.perform(delete("/api/entidades/{id}", entidade.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Entidade> entidadeList = entidadeRepository.findAll();
        assertThat(entidadeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
