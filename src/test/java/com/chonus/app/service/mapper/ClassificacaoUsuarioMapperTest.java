package com.chonus.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ClassificacaoUsuarioMapperTest {

    private ClassificacaoUsuarioMapper classificacaoUsuarioMapper;

    @BeforeEach
    public void setUp() {
        classificacaoUsuarioMapper = new ClassificacaoUsuarioMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(classificacaoUsuarioMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(classificacaoUsuarioMapper.fromId(null)).isNull();
    }
}
