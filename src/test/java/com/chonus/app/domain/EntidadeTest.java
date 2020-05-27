package com.chonus.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.chonus.app.web.rest.TestUtil;

public class EntidadeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Entidade.class);
        Entidade entidade1 = new Entidade();
        entidade1.setId("id1");
        Entidade entidade2 = new Entidade();
        entidade2.setId(entidade1.getId());
        assertThat(entidade1).isEqualTo(entidade2);
        entidade2.setId("id2");
        assertThat(entidade1).isNotEqualTo(entidade2);
        entidade1.setId(null);
        assertThat(entidade1).isNotEqualTo(entidade2);
    }
}
