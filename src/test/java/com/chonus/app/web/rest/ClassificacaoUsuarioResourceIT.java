package com.chonus.app.web.rest;

import com.chonus.app.ProjetoBaseChronusApp;
import com.chonus.app.domain.ClassificacaoUsuario;
import com.chonus.app.repository.ClassificacaoUsuarioRepository;
import com.chonus.app.service.ClassificacaoUsuarioService;
import com.chonus.app.service.dto.ClassificacaoUsuarioDTO;
import com.chonus.app.service.mapper.ClassificacaoUsuarioMapper;

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
 * Integration tests for the {@link ClassificacaoUsuarioResource} REST controller.
 */
@SpringBootTest(classes = ProjetoBaseChronusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ClassificacaoUsuarioResourceIT {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_COR = "AAAAAAAAAA";
    private static final String UPDATED_COR = "BBBBBBBBBB";

    @Autowired
    private ClassificacaoUsuarioRepository classificacaoUsuarioRepository;

    @Autowired
    private ClassificacaoUsuarioMapper classificacaoUsuarioMapper;

    @Autowired
    private ClassificacaoUsuarioService classificacaoUsuarioService;

    @Autowired
    private MockMvc restClassificacaoUsuarioMockMvc;

    private ClassificacaoUsuario classificacaoUsuario;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClassificacaoUsuario createEntity() {
        ClassificacaoUsuario classificacaoUsuario = new ClassificacaoUsuario();
        classificacaoUsuario.setDescricao(DEFAULT_DESCRICAO);
        classificacaoUsuario.setCor(DEFAULT_COR);
        return classificacaoUsuario;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClassificacaoUsuario createUpdatedEntity() {
        ClassificacaoUsuario classificacaoUsuario = new ClassificacaoUsuario();
        classificacaoUsuario.setDescricao(UPDATED_DESCRICAO);
        classificacaoUsuario.setCor(UPDATED_COR);
        return classificacaoUsuario;
    }

    @BeforeEach
    public void initTest() {
        classificacaoUsuarioRepository.deleteAll();
        classificacaoUsuario = createEntity();
    }

    @Test
    public void createClassificacaoUsuario() throws Exception {
        int databaseSizeBeforeCreate = classificacaoUsuarioRepository.findAll().size();
        // Create the ClassificacaoUsuario
        ClassificacaoUsuarioDTO classificacaoUsuarioDTO = classificacaoUsuarioMapper.toDto(classificacaoUsuario);
        restClassificacaoUsuarioMockMvc.perform(post("/api/classificacao-usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(classificacaoUsuarioDTO)))
            .andExpect(status().isCreated());

        // Validate the ClassificacaoUsuario in the database
        List<ClassificacaoUsuario> classificacaoUsuarioList = classificacaoUsuarioRepository.findAll();
        assertThat(classificacaoUsuarioList).hasSize(databaseSizeBeforeCreate + 1);
        ClassificacaoUsuario testClassificacaoUsuario = classificacaoUsuarioList.get(classificacaoUsuarioList.size() - 1);
        assertThat(testClassificacaoUsuario.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testClassificacaoUsuario.getCor()).isEqualTo(DEFAULT_COR);
    }

    @Test
    public void createClassificacaoUsuarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = classificacaoUsuarioRepository.findAll().size();

        // Create the ClassificacaoUsuario with an existing ID
        classificacaoUsuario.setId("existing_id");
        ClassificacaoUsuarioDTO classificacaoUsuarioDTO = classificacaoUsuarioMapper.toDto(classificacaoUsuario);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClassificacaoUsuarioMockMvc.perform(post("/api/classificacao-usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(classificacaoUsuarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClassificacaoUsuario in the database
        List<ClassificacaoUsuario> classificacaoUsuarioList = classificacaoUsuarioRepository.findAll();
        assertThat(classificacaoUsuarioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllClassificacaoUsuarios() throws Exception {
        // Initialize the database
        classificacaoUsuarioRepository.save(classificacaoUsuario);

        // Get all the classificacaoUsuarioList
        restClassificacaoUsuarioMockMvc.perform(get("/api/classificacao-usuarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(classificacaoUsuario.getId())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)))
            .andExpect(jsonPath("$.[*].cor").value(hasItem(DEFAULT_COR)));
    }
    
    @Test
    public void getClassificacaoUsuario() throws Exception {
        // Initialize the database
        classificacaoUsuarioRepository.save(classificacaoUsuario);

        // Get the classificacaoUsuario
        restClassificacaoUsuarioMockMvc.perform(get("/api/classificacao-usuarios/{id}", classificacaoUsuario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(classificacaoUsuario.getId()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO))
            .andExpect(jsonPath("$.cor").value(DEFAULT_COR));
    }
    @Test
    public void getNonExistingClassificacaoUsuario() throws Exception {
        // Get the classificacaoUsuario
        restClassificacaoUsuarioMockMvc.perform(get("/api/classificacao-usuarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateClassificacaoUsuario() throws Exception {
        // Initialize the database
        classificacaoUsuarioRepository.save(classificacaoUsuario);

        int databaseSizeBeforeUpdate = classificacaoUsuarioRepository.findAll().size();

        // Update the classificacaoUsuario
        ClassificacaoUsuario updatedClassificacaoUsuario = classificacaoUsuarioRepository.findById(classificacaoUsuario.getId()).get();
        updatedClassificacaoUsuario.setDescricao(UPDATED_DESCRICAO);
        updatedClassificacaoUsuario.setCor(UPDATED_COR);
        ClassificacaoUsuarioDTO classificacaoUsuarioDTO = classificacaoUsuarioMapper.toDto(updatedClassificacaoUsuario);

        restClassificacaoUsuarioMockMvc.perform(put("/api/classificacao-usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(classificacaoUsuarioDTO)))
            .andExpect(status().isOk());

        // Validate the ClassificacaoUsuario in the database
        List<ClassificacaoUsuario> classificacaoUsuarioList = classificacaoUsuarioRepository.findAll();
        assertThat(classificacaoUsuarioList).hasSize(databaseSizeBeforeUpdate);
        ClassificacaoUsuario testClassificacaoUsuario = classificacaoUsuarioList.get(classificacaoUsuarioList.size() - 1);
        assertThat(testClassificacaoUsuario.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testClassificacaoUsuario.getCor()).isEqualTo(UPDATED_COR);
    }

    @Test
    public void updateNonExistingClassificacaoUsuario() throws Exception {
        int databaseSizeBeforeUpdate = classificacaoUsuarioRepository.findAll().size();

        // Create the ClassificacaoUsuario
        ClassificacaoUsuarioDTO classificacaoUsuarioDTO = classificacaoUsuarioMapper.toDto(classificacaoUsuario);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClassificacaoUsuarioMockMvc.perform(put("/api/classificacao-usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(classificacaoUsuarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClassificacaoUsuario in the database
        List<ClassificacaoUsuario> classificacaoUsuarioList = classificacaoUsuarioRepository.findAll();
        assertThat(classificacaoUsuarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteClassificacaoUsuario() throws Exception {
        // Initialize the database
        classificacaoUsuarioRepository.save(classificacaoUsuario);

        int databaseSizeBeforeDelete = classificacaoUsuarioRepository.findAll().size();

        // Delete the classificacaoUsuario
        restClassificacaoUsuarioMockMvc.perform(delete("/api/classificacao-usuarios/{id}", classificacaoUsuario.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClassificacaoUsuario> classificacaoUsuarioList = classificacaoUsuarioRepository.findAll();
        assertThat(classificacaoUsuarioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
