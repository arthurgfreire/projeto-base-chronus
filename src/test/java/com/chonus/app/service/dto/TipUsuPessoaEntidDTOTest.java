package com.chonus.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.chonus.app.web.rest.TestUtil;

public class TipUsuPessoaEntidDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipUsuPessoaEntidDTO.class);
        TipUsuPessoaEntidDTO tipUsuPessoaEntidDTO1 = new TipUsuPessoaEntidDTO();
        tipUsuPessoaEntidDTO1.setId("id1");
        TipUsuPessoaEntidDTO tipUsuPessoaEntidDTO2 = new TipUsuPessoaEntidDTO();
        assertThat(tipUsuPessoaEntidDTO1).isNotEqualTo(tipUsuPessoaEntidDTO2);
        tipUsuPessoaEntidDTO2.setId(tipUsuPessoaEntidDTO1.getId());
        assertThat(tipUsuPessoaEntidDTO1).isEqualTo(tipUsuPessoaEntidDTO2);
        tipUsuPessoaEntidDTO2.setId("id2");
        assertThat(tipUsuPessoaEntidDTO1).isNotEqualTo(tipUsuPessoaEntidDTO2);
        tipUsuPessoaEntidDTO1.setId(null);
        assertThat(tipUsuPessoaEntidDTO1).isNotEqualTo(tipUsuPessoaEntidDTO2);
    }
}
