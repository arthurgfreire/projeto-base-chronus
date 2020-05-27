package com.chonus.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UfMapperTest {

    private UfMapper ufMapper;

    @BeforeEach
    public void setUp() {
        ufMapper = new UfMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(ufMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ufMapper.fromId(null)).isNull();
    }
}
