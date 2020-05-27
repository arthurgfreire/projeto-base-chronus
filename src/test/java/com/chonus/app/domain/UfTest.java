package com.chonus.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.chonus.app.web.rest.TestUtil;

public class UfTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Uf.class);
        Uf uf1 = new Uf();
        uf1.setId("id1");
        Uf uf2 = new Uf();
        uf2.setId(uf1.getId());
        assertThat(uf1).isEqualTo(uf2);
        uf2.setId("id2");
        assertThat(uf1).isNotEqualTo(uf2);
        uf1.setId(null);
        assertThat(uf1).isNotEqualTo(uf2);
    }
}
