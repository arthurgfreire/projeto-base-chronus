package com.chonus.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PessoaMapperTest {

    private PessoaMapper pessoaMapper;

    @BeforeEach
    public void setUp() {
        pessoaMapper = new PessoaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(pessoaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(pessoaMapper.fromId(null)).isNull();
    }
}
