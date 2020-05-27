package com.chonus.app.web.rest;

import com.chonus.app.ProjetoBaseChronusApp;
import com.chonus.app.domain.TipoUsuario;
import com.chonus.app.repository.TipoUsuarioRepository;
import com.chonus.app.service.TipoUsuarioService;
import com.chonus.app.service.dto.TipoUsuarioDTO;
import com.chonus.app.service.mapper.TipoUsuarioMapper;

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
 * Integration tests for the {@link TipoUsuarioResource} REST controller.
 */
@SpringBootTest(classes = ProjetoBaseChronusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TipoUsuarioResourceIT {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    private TipoUsuarioMapper tipoUsuarioMapper;

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @Autowired
    private MockMvc restTipoUsuarioMockMvc;

    private TipoUsuario tipoUsuario;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoUsuario createEntity() {
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setDescricao(DEFAULT_DESCRICAO);
        return tipoUsuario;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoUsuario createUpdatedEntity() {
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setDescricao(UPDATED_DESCRICAO);
        return tipoUsuario;
    }

    @BeforeEach
    public void initTest() {
        tipoUsuarioRepository.deleteAll();
        tipoUsuario = createEntity();
    }

    @Test
    public void createTipoUsuario() throws Exception {
        int databaseSizeBeforeCreate = tipoUsuarioRepository.findAll().size();
        // Create the TipoUsuario
        TipoUsuarioDTO tipoUsuarioDTO = tipoUsuarioMapper.toDto(tipoUsuario);
        restTipoUsuarioMockMvc.perform(post("/api/tipo-usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoUsuarioDTO)))
            .andExpect(status().isCreated());

        // Validate the TipoUsuario in the database
        List<TipoUsuario> tipoUsuarioList = tipoUsuarioRepository.findAll();
        assertThat(tipoUsuarioList).hasSize(databaseSizeBeforeCreate + 1);
        TipoUsuario testTipoUsuario = tipoUsuarioList.get(tipoUsuarioList.size() - 1);
        assertThat(testTipoUsuario.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    public void createTipoUsuarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoUsuarioRepository.findAll().size();

        // Create the TipoUsuario with an existing ID
        tipoUsuario.setId("existing_id");
        TipoUsuarioDTO tipoUsuarioDTO = tipoUsuarioMapper.toDto(tipoUsuario);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoUsuarioMockMvc.perform(post("/api/tipo-usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoUsuarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoUsuario in the database
        List<TipoUsuario> tipoUsuarioList = tipoUsuarioRepository.findAll();
        assertThat(tipoUsuarioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllTipoUsuarios() throws Exception {
        // Initialize the database
        tipoUsuarioRepository.save(tipoUsuario);

        // Get all the tipoUsuarioList
        restTipoUsuarioMockMvc.perform(get("/api/tipo-usuarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoUsuario.getId())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)));
    }
    
    @Test
    public void getTipoUsuario() throws Exception {
        // Initialize the database
        tipoUsuarioRepository.save(tipoUsuario);

        // Get the tipoUsuario
        restTipoUsuarioMockMvc.perform(get("/api/tipo-usuarios/{id}", tipoUsuario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tipoUsuario.getId()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO));
    }
    @Test
    public void getNonExistingTipoUsuario() throws Exception {
        // Get the tipoUsuario
        restTipoUsuarioMockMvc.perform(get("/api/tipo-usuarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTipoUsuario() throws Exception {
        // Initialize the database
        tipoUsuarioRepository.save(tipoUsuario);

        int databaseSizeBeforeUpdate = tipoUsuarioRepository.findAll().size();

        // Update the tipoUsuario
        TipoUsuario updatedTipoUsuario = tipoUsuarioRepository.findById(tipoUsuario.getId()).get();
        updatedTipoUsuario.setDescricao(UPDATED_DESCRICAO);
        TipoUsuarioDTO tipoUsuarioDTO = tipoUsuarioMapper.toDto(updatedTipoUsuario);

        restTipoUsuarioMockMvc.perform(put("/api/tipo-usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoUsuarioDTO)))
            .andExpect(status().isOk());

        // Validate the TipoUsuario in the database
        List<TipoUsuario> tipoUsuarioList = tipoUsuarioRepository.findAll();
        assertThat(tipoUsuarioList).hasSize(databaseSizeBeforeUpdate);
        TipoUsuario testTipoUsuario = tipoUsuarioList.get(tipoUsuarioList.size() - 1);
        assertThat(testTipoUsuario.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    public void updateNonExistingTipoUsuario() throws Exception {
        int databaseSizeBeforeUpdate = tipoUsuarioRepository.findAll().size();

        // Create the TipoUsuario
        TipoUsuarioDTO tipoUsuarioDTO = tipoUsuarioMapper.toDto(tipoUsuario);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoUsuarioMockMvc.perform(put("/api/tipo-usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoUsuarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoUsuario in the database
        List<TipoUsuario> tipoUsuarioList = tipoUsuarioRepository.findAll();
        assertThat(tipoUsuarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteTipoUsuario() throws Exception {
        // Initialize the database
        tipoUsuarioRepository.save(tipoUsuario);

        int databaseSizeBeforeDelete = tipoUsuarioRepository.findAll().size();

        // Delete the tipoUsuario
        restTipoUsuarioMockMvc.perform(delete("/api/tipo-usuarios/{id}", tipoUsuario.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TipoUsuario> tipoUsuarioList = tipoUsuarioRepository.findAll();
        assertThat(tipoUsuarioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
