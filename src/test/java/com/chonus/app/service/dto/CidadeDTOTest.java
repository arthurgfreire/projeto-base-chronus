package com.chonus.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.chonus.app.web.rest.TestUtil;

public class CidadeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CidadeDTO.class);
        CidadeDTO cidadeDTO1 = new CidadeDTO();
        cidadeDTO1.setId("id1");
        CidadeDTO cidadeDTO2 = new CidadeDTO();
        assertThat(cidadeDTO1).isNotEqualTo(cidadeDTO2);
        cidadeDTO2.setId(cidadeDTO1.getId());
        assertThat(cidadeDTO1).isEqualTo(cidadeDTO2);
        cidadeDTO2.setId("id2");
        assertThat(cidadeDTO1).isNotEqualTo(cidadeDTO2);
        cidadeDTO1.setId(null);
        assertThat(cidadeDTO1).isNotEqualTo(cidadeDTO2);
    }
}
