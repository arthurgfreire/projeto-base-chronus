package com.chonus.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UnidadeAtendimentoMapperTest {

    private UnidadeAtendimentoMapper unidadeAtendimentoMapper;

    @BeforeEach
    public void setUp() {
        unidadeAtendimentoMapper = new UnidadeAtendimentoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(unidadeAtendimentoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(unidadeAtendimentoMapper.fromId(null)).isNull();
    }
}
