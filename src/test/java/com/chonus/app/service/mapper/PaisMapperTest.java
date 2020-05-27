package com.chonus.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PaisMapperTest {

    private PaisMapper paisMapper;

    @BeforeEach
    public void setUp() {
        paisMapper = new PaisMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(paisMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(paisMapper.fromId(null)).isNull();
    }
}
