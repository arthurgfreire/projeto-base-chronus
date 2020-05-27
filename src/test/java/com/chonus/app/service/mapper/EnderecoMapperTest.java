package com.chonus.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EnderecoMapperTest {

    private EnderecoMapper enderecoMapper;

    @BeforeEach
    public void setUp() {
        enderecoMapper = new EnderecoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(enderecoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(enderecoMapper.fromId(null)).isNull();
    }
}
