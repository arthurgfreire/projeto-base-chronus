package com.chonus.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.chonus.app.web.rest.TestUtil;

public class CidadeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cidade.class);
        Cidade cidade1 = new Cidade();
        cidade1.setId("id1");
        Cidade cidade2 = new Cidade();
        cidade2.setId(cidade1.getId());
        assertThat(cidade1).isEqualTo(cidade2);
        cidade2.setId("id2");
        assertThat(cidade1).isNotEqualTo(cidade2);
        cidade1.setId(null);
        assertThat(cidade1).isNotEqualTo(cidade2);
    }
}
