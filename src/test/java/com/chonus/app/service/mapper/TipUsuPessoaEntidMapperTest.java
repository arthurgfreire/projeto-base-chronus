package com.chonus.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TipUsuPessoaEntidMapperTest {

    private TipUsuPessoaEntidMapper tipUsuPessoaEntidMapper;

    @BeforeEach
    public void setUp() {
        tipUsuPessoaEntidMapper = new TipUsuPessoaEntidMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(tipUsuPessoaEntidMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tipUsuPessoaEntidMapper.fromId(null)).isNull();
    }
}
