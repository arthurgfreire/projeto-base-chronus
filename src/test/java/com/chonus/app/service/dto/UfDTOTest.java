package com.chonus.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.chonus.app.web.rest.TestUtil;

public class UfDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UfDTO.class);
        UfDTO ufDTO1 = new UfDTO();
        ufDTO1.setId("id1");
        UfDTO ufDTO2 = new UfDTO();
        assertThat(ufDTO1).isNotEqualTo(ufDTO2);
        ufDTO2.setId(ufDTO1.getId());
        assertThat(ufDTO1).isEqualTo(ufDTO2);
        ufDTO2.setId("id2");
        assertThat(ufDTO1).isNotEqualTo(ufDTO2);
        ufDTO1.setId(null);
        assertThat(ufDTO1).isNotEqualTo(ufDTO2);
    }
}
