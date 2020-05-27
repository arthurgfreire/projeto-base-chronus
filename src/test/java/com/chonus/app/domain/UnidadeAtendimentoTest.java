package com.chonus.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.chonus.app.web.rest.TestUtil;

public class UnidadeAtendimentoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UnidadeAtendimento.class);
        UnidadeAtendimento unidadeAtendimento1 = new UnidadeAtendimento();
        unidadeAtendimento1.setId("id1");
        UnidadeAtendimento unidadeAtendimento2 = new UnidadeAtendimento();
        unidadeAtendimento2.setId(unidadeAtendimento1.getId());
        assertThat(unidadeAtendimento1).isEqualTo(unidadeAtendimento2);
        unidadeAtendimento2.setId("id2");
        assertThat(unidadeAtendimento1).isNotEqualTo(unidadeAtendimento2);
        unidadeAtendimento1.setId(null);
        assertThat(unidadeAtendimento1).isNotEqualTo(unidadeAtendimento2);
    }
}
