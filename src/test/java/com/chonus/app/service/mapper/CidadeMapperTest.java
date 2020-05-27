package com.chonus.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CidadeMapperTest {

    private CidadeMapper cidadeMapper;

    @BeforeEach
    public void setUp() {
        cidadeMapper = new CidadeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(cidadeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cidadeMapper.fromId(null)).isNull();
    }
}
