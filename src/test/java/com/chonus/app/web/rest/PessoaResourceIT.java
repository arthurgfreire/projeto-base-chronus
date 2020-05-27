package com.chonus.app.web.rest;

import com.chonus.app.ProjetoBaseChronusApp;
import com.chonus.app.domain.Pessoa;
import com.chonus.app.domain.Pais;
import com.chonus.app.domain.Uf;
import com.chonus.app.domain.Cidade;
import com.chonus.app.domain.Raca;
import com.chonus.app.repository.PessoaRepository;
import com.chonus.app.service.PessoaService;
import com.chonus.app.service.dto.PessoaDTO;
import com.chonus.app.service.mapper.PessoaMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.chonus.app.domain.enumeration.Genero;
import com.chonus.app.domain.enumeration.EstadoCivil;
/**
 * Integration tests for the {@link PessoaResource} REST controller.
 */
@SpringBootTest(classes = ProjetoBaseChronusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PessoaResourceIT {

    private static final byte[] DEFAULT_FOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_CPF = "AAAAAAAAAA";
    private static final String UPDATED_CPF = "BBBBBBBBBB";

    private static final String DEFAULT_RG = "AAAAAAAAAA";
    private static final String UPDATED_RG = "BBBBBBBBBB";

    private static final String DEFAULT_CNH = "AAAAAAAAAA";
    private static final String UPDATED_CNH = "BBBBBBBBBB";

    private static final String DEFAULT_PASSAPORTE = "AAAAAAAAAA";
    private static final String UPDATED_PASSAPORTE = "BBBBBBBBBB";

    private static final String DEFAULT_CERTIDAO_NASCIMENTO = "AAAAAAAAAA";
    private static final String UPDATED_CERTIDAO_NASCIMENTO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ESTRANGEIRO = false;
    private static final Boolean UPDATED_ESTRANGEIRO = true;

    private static final Boolean DEFAULT_MORA_OUTRAS_PESSOAS = false;
    private static final Boolean UPDATED_MORA_OUTRAS_PESSOAS = true;

    private static final String DEFAULT_TELEFONE = "943 5986-5523";
    private static final String UPDATED_TELEFONE = "(665) 47698-6104";

    private static final String DEFAULT_EMAIL = "aye4@1lz.cixp";
    private static final String UPDATED_EMAIL = "ffs@h69y.rr";

    private static final Boolean DEFAULT_TEM_FILHOS = false;
    private static final Boolean UPDATED_TEM_FILHOS = true;

    private static final Long DEFAULT_QUANT_FILHOS = 1L;
    private static final Long UPDATED_QUANT_FILHOS = 2L;

    private static final Genero DEFAULT_GENERO = Genero.HOMEM;
    private static final Genero UPDATED_GENERO = Genero.MULHER;

    private static final EstadoCivil DEFAULT_ESTADO_CIVIL = EstadoCivil.CASADO;
    private static final EstadoCivil UPDATED_ESTADO_CIVIL = EstadoCivil.SOLTEIRO;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaMapper pessoaMapper;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private MockMvc restPessoaMockMvc;

    private Pessoa pessoa;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pessoa createEntity() {
        Pessoa pessoa = new Pessoa();
        pessoa.setFoto(DEFAULT_FOTO);
        pessoa.setFotoContentType(DEFAULT_FOTO_CONTENT_TYPE);
        pessoa.setNome(DEFAULT_NOME);
        pessoa.setCpf(DEFAULT_CPF);
        pessoa.setRg(DEFAULT_RG);
        pessoa.setCnh(DEFAULT_CNH);
        pessoa.setPassaporte(DEFAULT_PASSAPORTE);
        pessoa.setCertidaoNascimento(DEFAULT_CERTIDAO_NASCIMENTO);
        pessoa.setEstrangeiro(DEFAULT_ESTRANGEIRO);
        pessoa.setMoraOutrasPessoas(DEFAULT_MORA_OUTRAS_PESSOAS);
        pessoa.setTelefone(DEFAULT_TELEFONE);
        pessoa.setEmail(DEFAULT_EMAIL);
        pessoa.setTemFilhos(DEFAULT_TEM_FILHOS);
        pessoa.setQuantFilhos(DEFAULT_QUANT_FILHOS);
        pessoa.setGenero(DEFAULT_GENERO);
        pessoa.setEstadoCivil(DEFAULT_ESTADO_CIVIL);
        // Add required entity
        Pais Pais;
        Pais = PaisResourceIT.createEntity();
        Pais.setId("fixed-id-for-tests");
        pessoa.setPais(Pais);
        // Add required entity
        Uf Uf;
        Uf = UfResourceIT.createEntity();
        Uf.setId("fixed-id-for-tests");
        pessoa.setUf(Uf);
        // Add required entity
        Cidade Cidade;
        Cidade = CidadeResourceIT.createEntity();
        Cidade.setId("fixed-id-for-tests");
        pessoa.setCidade(Cidade);
        // Add required entity
        Raca Raca;
        Raca = RacaResourceIT.createEntity();
        Raca.setId("fixed-id-for-tests");
        pessoa.setRaca(Raca);
        return pessoa;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pessoa createUpdatedEntity() {
        Pessoa pessoa = new Pessoa();
        pessoa.setFoto(UPDATED_FOTO);
        pessoa.setFotoContentType(UPDATED_FOTO_CONTENT_TYPE);
        pessoa.setNome(UPDATED_NOME);
        pessoa.setCpf(UPDATED_CPF);
        pessoa.setRg(UPDATED_RG);
        pessoa.setCnh(UPDATED_CNH);
        pessoa.setPassaporte(UPDATED_PASSAPORTE);
        pessoa.setCertidaoNascimento(UPDATED_CERTIDAO_NASCIMENTO);
        pessoa.setEstrangeiro(UPDATED_ESTRANGEIRO);
        pessoa.setMoraOutrasPessoas(UPDATED_MORA_OUTRAS_PESSOAS);
        pessoa.setTelefone(UPDATED_TELEFONE);
        pessoa.setEmail(UPDATED_EMAIL);
        pessoa.setTemFilhos(UPDATED_TEM_FILHOS);
        pessoa.setQuantFilhos(UPDATED_QUANT_FILHOS);
        pessoa.setGenero(UPDATED_GENERO);
        pessoa.setEstadoCivil(UPDATED_ESTADO_CIVIL);
        // Add required entity
        Pais Pais;
        Pais = PaisResourceIT.createUpdatedEntity();
        Pais.setId("fixed-id-for-tests");
        pessoa.setPais(Pais);
        // Add required entity
        Uf Uf;
        Uf = UfResourceIT.createUpdatedEntity();
        Uf.setId("fixed-id-for-tests");
        pessoa.setUf(Uf);
        // Add required entity
        Cidade Cidade;
        Cidade = CidadeResourceIT.createUpdatedEntity();
        Cidade.setId("fixed-id-for-tests");
        pessoa.setCidade(Cidade);
        // Add required entity
        Raca Raca;
        Raca = RacaResourceIT.createUpdatedEntity();
        Raca.setId("fixed-id-for-tests");
        pessoa.setRaca(Raca);
        return pessoa;
    }

    @BeforeEach
    public void initTest() {
        pessoaRepository.deleteAll();
        pessoa = createEntity();
    }

    @Test
    public void createPessoa() throws Exception {
        int databaseSizeBeforeCreate = pessoaRepository.findAll().size();
        // Create the Pessoa
        PessoaDTO pessoaDTO = pessoaMapper.toDto(pessoa);
        restPessoaMockMvc.perform(post("/api/pessoas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pessoaDTO)))
            .andExpect(status().isCreated());

        // Validate the Pessoa in the database
        List<Pessoa> pessoaList = pessoaRepository.findAll();
        assertThat(pessoaList).hasSize(databaseSizeBeforeCreate + 1);
        Pessoa testPessoa = pessoaList.get(pessoaList.size() - 1);
        assertThat(testPessoa.getFoto()).isEqualTo(DEFAULT_FOTO);
        assertThat(testPessoa.getFotoContentType()).isEqualTo(DEFAULT_FOTO_CONTENT_TYPE);
        assertThat(testPessoa.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testPessoa.getCpf()).isEqualTo(DEFAULT_CPF);
        assertThat(testPessoa.getRg()).isEqualTo(DEFAULT_RG);
        assertThat(testPessoa.getCnh()).isEqualTo(DEFAULT_CNH);
        assertThat(testPessoa.getPassaporte()).isEqualTo(DEFAULT_PASSAPORTE);
        assertThat(testPessoa.getCertidaoNascimento()).isEqualTo(DEFAULT_CERTIDAO_NASCIMENTO);
        assertThat(testPessoa.isEstrangeiro()).isEqualTo(DEFAULT_ESTRANGEIRO);
        assertThat(testPessoa.isMoraOutrasPessoas()).isEqualTo(DEFAULT_MORA_OUTRAS_PESSOAS);
        assertThat(testPessoa.getTelefone()).isEqualTo(DEFAULT_TELEFONE);
        assertThat(testPessoa.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPessoa.isTemFilhos()).isEqualTo(DEFAULT_TEM_FILHOS);
        assertThat(testPessoa.getQuantFilhos()).isEqualTo(DEFAULT_QUANT_FILHOS);
        assertThat(testPessoa.getGenero()).isEqualTo(DEFAULT_GENERO);
        assertThat(testPessoa.getEstadoCivil()).isEqualTo(DEFAULT_ESTADO_CIVIL);
    }

    @Test
    public void createPessoaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pessoaRepository.findAll().size();

        // Create the Pessoa with an existing ID
        pessoa.setId("existing_id");
        PessoaDTO pessoaDTO = pessoaMapper.toDto(pessoa);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPessoaMockMvc.perform(post("/api/pessoas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pessoaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pessoa in the database
        List<Pessoa> pessoaList = pessoaRepository.findAll();
        assertThat(pessoaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pessoaRepository.findAll().size();
        // set the field null
        pessoa.setNome(null);

        // Create the Pessoa, which fails.
        PessoaDTO pessoaDTO = pessoaMapper.toDto(pessoa);


        restPessoaMockMvc.perform(post("/api/pessoas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pessoaDTO)))
            .andExpect(status().isBadRequest());

        List<Pessoa> pessoaList = pessoaRepository.findAll();
        assertThat(pessoaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkEstrangeiroIsRequired() throws Exception {
        int databaseSizeBeforeTest = pessoaRepository.findAll().size();
        // set the field null
        pessoa.setEstrangeiro(null);

        // Create the Pessoa, which fails.
        PessoaDTO pessoaDTO = pessoaMapper.toDto(pessoa);


        restPessoaMockMvc.perform(post("/api/pessoas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pessoaDTO)))
            .andExpect(status().isBadRequest());

        List<Pessoa> pessoaList = pessoaRepository.findAll();
        assertThat(pessoaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkMoraOutrasPessoasIsRequired() throws Exception {
        int databaseSizeBeforeTest = pessoaRepository.findAll().size();
        // set the field null
        pessoa.setMoraOutrasPessoas(null);

        // Create the Pessoa, which fails.
        PessoaDTO pessoaDTO = pessoaMapper.toDto(pessoa);


        restPessoaMockMvc.perform(post("/api/pessoas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pessoaDTO)))
            .andExpect(status().isBadRequest());

        List<Pessoa> pessoaList = pessoaRepository.findAll();
        assertThat(pessoaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTelefoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = pessoaRepository.findAll().size();
        // set the field null
        pessoa.setTelefone(null);

        // Create the Pessoa, which fails.
        PessoaDTO pessoaDTO = pessoaMapper.toDto(pessoa);


        restPessoaMockMvc.perform(post("/api/pessoas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pessoaDTO)))
            .andExpect(status().isBadRequest());

        List<Pessoa> pessoaList = pessoaRepository.findAll();
        assertThat(pessoaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkGeneroIsRequired() throws Exception {
        int databaseSizeBeforeTest = pessoaRepository.findAll().size();
        // set the field null
        pessoa.setGenero(null);

        // Create the Pessoa, which fails.
        PessoaDTO pessoaDTO = pessoaMapper.toDto(pessoa);


        restPessoaMockMvc.perform(post("/api/pessoas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pessoaDTO)))
            .andExpect(status().isBadRequest());

        List<Pessoa> pessoaList = pessoaRepository.findAll();
        assertThat(pessoaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkEstadoCivilIsRequired() throws Exception {
        int databaseSizeBeforeTest = pessoaRepository.findAll().size();
        // set the field null
        pessoa.setEstadoCivil(null);

        // Create the Pessoa, which fails.
        PessoaDTO pessoaDTO = pessoaMapper.toDto(pessoa);


        restPessoaMockMvc.perform(post("/api/pessoas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pessoaDTO)))
            .andExpect(status().isBadRequest());

        List<Pessoa> pessoaList = pessoaRepository.findAll();
        assertThat(pessoaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllPessoas() throws Exception {
        // Initialize the database
        pessoaRepository.save(pessoa);

        // Get all the pessoaList
        restPessoaMockMvc.perform(get("/api/pessoas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pessoa.getId())))
            .andExpect(jsonPath("$.[*].fotoContentType").value(hasItem(DEFAULT_FOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO))))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].cpf").value(hasItem(DEFAULT_CPF)))
            .andExpect(jsonPath("$.[*].rg").value(hasItem(DEFAULT_RG)))
            .andExpect(jsonPath("$.[*].cnh").value(hasItem(DEFAULT_CNH)))
            .andExpect(jsonPath("$.[*].passaporte").value(hasItem(DEFAULT_PASSAPORTE)))
            .andExpect(jsonPath("$.[*].certidaoNascimento").value(hasItem(DEFAULT_CERTIDAO_NASCIMENTO)))
            .andExpect(jsonPath("$.[*].estrangeiro").value(hasItem(DEFAULT_ESTRANGEIRO.booleanValue())))
            .andExpect(jsonPath("$.[*].MoraOutrasPessoas").value(hasItem(DEFAULT_MORA_OUTRAS_PESSOAS.booleanValue())))
            .andExpect(jsonPath("$.[*].telefone").value(hasItem(DEFAULT_TELEFONE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].temFilhos").value(hasItem(DEFAULT_TEM_FILHOS.booleanValue())))
            .andExpect(jsonPath("$.[*].quantFilhos").value(hasItem(DEFAULT_QUANT_FILHOS.intValue())))
            .andExpect(jsonPath("$.[*].genero").value(hasItem(DEFAULT_GENERO.toString())))
            .andExpect(jsonPath("$.[*].estadoCivil").value(hasItem(DEFAULT_ESTADO_CIVIL.toString())));
    }
    
    @Test
    public void getPessoa() throws Exception {
        // Initialize the database
        pessoaRepository.save(pessoa);

        // Get the pessoa
        restPessoaMockMvc.perform(get("/api/pessoas/{id}", pessoa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pessoa.getId()))
            .andExpect(jsonPath("$.fotoContentType").value(DEFAULT_FOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto").value(Base64Utils.encodeToString(DEFAULT_FOTO)))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.cpf").value(DEFAULT_CPF))
            .andExpect(jsonPath("$.rg").value(DEFAULT_RG))
            .andExpect(jsonPath("$.cnh").value(DEFAULT_CNH))
            .andExpect(jsonPath("$.passaporte").value(DEFAULT_PASSAPORTE))
            .andExpect(jsonPath("$.certidaoNascimento").value(DEFAULT_CERTIDAO_NASCIMENTO))
            .andExpect(jsonPath("$.estrangeiro").value(DEFAULT_ESTRANGEIRO.booleanValue()))
            .andExpect(jsonPath("$.MoraOutrasPessoas").value(DEFAULT_MORA_OUTRAS_PESSOAS.booleanValue()))
            .andExpect(jsonPath("$.telefone").value(DEFAULT_TELEFONE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.temFilhos").value(DEFAULT_TEM_FILHOS.booleanValue()))
            .andExpect(jsonPath("$.quantFilhos").value(DEFAULT_QUANT_FILHOS.intValue()))
            .andExpect(jsonPath("$.genero").value(DEFAULT_GENERO.toString()))
            .andExpect(jsonPath("$.estadoCivil").value(DEFAULT_ESTADO_CIVIL.toString()));
    }
    @Test
    public void getNonExistingPessoa() throws Exception {
        // Get the pessoa
        restPessoaMockMvc.perform(get("/api/pessoas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updatePessoa() throws Exception {
        // Initialize the database
        pessoaRepository.save(pessoa);

        int databaseSizeBeforeUpdate = pessoaRepository.findAll().size();

        // Update the pessoa
        Pessoa updatedPessoa = pessoaRepository.findById(pessoa.getId()).get();
        updatedPessoa.setFoto(UPDATED_FOTO);
        updatedPessoa.setFotoContentType(UPDATED_FOTO_CONTENT_TYPE);
        updatedPessoa.setNome(UPDATED_NOME);
        updatedPessoa.setCpf(UPDATED_CPF);
        updatedPessoa.setRg(UPDATED_RG);
        updatedPessoa.setCnh(UPDATED_CNH);
        updatedPessoa.setPassaporte(UPDATED_PASSAPORTE);
        updatedPessoa.setCertidaoNascimento(UPDATED_CERTIDAO_NASCIMENTO);
        updatedPessoa.setEstrangeiro(UPDATED_ESTRANGEIRO);
        updatedPessoa.setMoraOutrasPessoas(UPDATED_MORA_OUTRAS_PESSOAS);
        updatedPessoa.setTelefone(UPDATED_TELEFONE);
        updatedPessoa.setEmail(UPDATED_EMAIL);
        updatedPessoa.setTemFilhos(UPDATED_TEM_FILHOS);
        updatedPessoa.setQuantFilhos(UPDATED_QUANT_FILHOS);
        updatedPessoa.setGenero(UPDATED_GENERO);
        updatedPessoa.setEstadoCivil(UPDATED_ESTADO_CIVIL);
        PessoaDTO pessoaDTO = pessoaMapper.toDto(updatedPessoa);

        restPessoaMockMvc.perform(put("/api/pessoas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pessoaDTO)))
            .andExpect(status().isOk());

        // Validate the Pessoa in the database
        List<Pessoa> pessoaList = pessoaRepository.findAll();
        assertThat(pessoaList).hasSize(databaseSizeBeforeUpdate);
        Pessoa testPessoa = pessoaList.get(pessoaList.size() - 1);
        assertThat(testPessoa.getFoto()).isEqualTo(UPDATED_FOTO);
        assertThat(testPessoa.getFotoContentType()).isEqualTo(UPDATED_FOTO_CONTENT_TYPE);
        assertThat(testPessoa.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testPessoa.getCpf()).isEqualTo(UPDATED_CPF);
        assertThat(testPessoa.getRg()).isEqualTo(UPDATED_RG);
        assertThat(testPessoa.getCnh()).isEqualTo(UPDATED_CNH);
        assertThat(testPessoa.getPassaporte()).isEqualTo(UPDATED_PASSAPORTE);
        assertThat(testPessoa.getCertidaoNascimento()).isEqualTo(UPDATED_CERTIDAO_NASCIMENTO);
        assertThat(testPessoa.isEstrangeiro()).isEqualTo(UPDATED_ESTRANGEIRO);
        assertThat(testPessoa.isMoraOutrasPessoas()).isEqualTo(UPDATED_MORA_OUTRAS_PESSOAS);
        assertThat(testPessoa.getTelefone()).isEqualTo(UPDATED_TELEFONE);
        assertThat(testPessoa.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPessoa.isTemFilhos()).isEqualTo(UPDATED_TEM_FILHOS);
        assertThat(testPessoa.getQuantFilhos()).isEqualTo(UPDATED_QUANT_FILHOS);
        assertThat(testPessoa.getGenero()).isEqualTo(UPDATED_GENERO);
        assertThat(testPessoa.getEstadoCivil()).isEqualTo(UPDATED_ESTADO_CIVIL);
    }

    @Test
    public void updateNonExistingPessoa() throws Exception {
        int databaseSizeBeforeUpdate = pessoaRepository.findAll().size();

        // Create the Pessoa
        PessoaDTO pessoaDTO = pessoaMapper.toDto(pessoa);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPessoaMockMvc.perform(put("/api/pessoas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pessoaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pessoa in the database
        List<Pessoa> pessoaList = pessoaRepository.findAll();
        assertThat(pessoaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deletePessoa() throws Exception {
        // Initialize the database
        pessoaRepository.save(pessoa);

        int databaseSizeBeforeDelete = pessoaRepository.findAll().size();

        // Delete the pessoa
        restPessoaMockMvc.perform(delete("/api/pessoas/{id}", pessoa.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Pessoa> pessoaList = pessoaRepository.findAll();
        assertThat(pessoaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
