package com.chonus.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RacaMapperTest {

    private RacaMapper racaMapper;

    @BeforeEach
    public void setUp() {
        racaMapper = new RacaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(racaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(racaMapper.fromId(null)).isNull();
    }
}
