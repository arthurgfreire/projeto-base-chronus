package com.chonus.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EntidadeMapperTest {

    private EntidadeMapper entidadeMapper;

    @BeforeEach
    public void setUp() {
        entidadeMapper = new EntidadeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(entidadeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(entidadeMapper.fromId(null)).isNull();
    }
}
