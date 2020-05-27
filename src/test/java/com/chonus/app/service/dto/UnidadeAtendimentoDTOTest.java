package com.chonus.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.chonus.app.web.rest.TestUtil;

public class UnidadeAtendimentoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UnidadeAtendimentoDTO.class);
        UnidadeAtendimentoDTO unidadeAtendimentoDTO1 = new UnidadeAtendimentoDTO();
        unidadeAtendimentoDTO1.setId("id1");
        UnidadeAtendimentoDTO unidadeAtendimentoDTO2 = new UnidadeAtendimentoDTO();
        assertThat(unidadeAtendimentoDTO1).isNotEqualTo(unidadeAtendimentoDTO2);
        unidadeAtendimentoDTO2.setId(unidadeAtendimentoDTO1.getId());
        assertThat(unidadeAtendimentoDTO1).isEqualTo(unidadeAtendimentoDTO2);
        unidadeAtendimentoDTO2.setId("id2");
        assertThat(unidadeAtendimentoDTO1).isNotEqualTo(unidadeAtendimentoDTO2);
        unidadeAtendimentoDTO1.setId(null);
        assertThat(unidadeAtendimentoDTO1).isNotEqualTo(unidadeAtendimentoDTO2);
    }
}
