package com.chonus.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TipoUsuarioMapperTest {

    private TipoUsuarioMapper tipoUsuarioMapper;

    @BeforeEach
    public void setUp() {
        tipoUsuarioMapper = new TipoUsuarioMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(tipoUsuarioMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tipoUsuarioMapper.fromId(null)).isNull();
    }
}
