package com.chonus.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.chonus.app.web.rest.TestUtil;

public class EntidadeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntidadeDTO.class);
        EntidadeDTO entidadeDTO1 = new EntidadeDTO();
        entidadeDTO1.setId("id1");
        EntidadeDTO entidadeDTO2 = new EntidadeDTO();
        assertThat(entidadeDTO1).isNotEqualTo(entidadeDTO2);
        entidadeDTO2.setId(entidadeDTO1.getId());
        assertThat(entidadeDTO1).isEqualTo(entidadeDTO2);
        entidadeDTO2.setId("id2");
        assertThat(entidadeDTO1).isNotEqualTo(entidadeDTO2);
        entidadeDTO1.setId(null);
        assertThat(entidadeDTO1).isNotEqualTo(entidadeDTO2);
    }
}
