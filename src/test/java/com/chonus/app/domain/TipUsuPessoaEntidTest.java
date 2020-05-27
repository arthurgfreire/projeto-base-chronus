package com.chonus.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.chonus.app.web.rest.TestUtil;

public class TipUsuPessoaEntidTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipUsuPessoaEntid.class);
        TipUsuPessoaEntid tipUsuPessoaEntid1 = new TipUsuPessoaEntid();
        tipUsuPessoaEntid1.setId("id1");
        TipUsuPessoaEntid tipUsuPessoaEntid2 = new TipUsuPessoaEntid();
        tipUsuPessoaEntid2.setId(tipUsuPessoaEntid1.getId());
        assertThat(tipUsuPessoaEntid1).isEqualTo(tipUsuPessoaEntid2);
        tipUsuPessoaEntid2.setId("id2");
        assertThat(tipUsuPessoaEntid1).isNotEqualTo(tipUsuPessoaEntid2);
        tipUsuPessoaEntid1.setId(null);
        assertThat(tipUsuPessoaEntid1).isNotEqualTo(tipUsuPessoaEntid2);
    }
}
