package com.chonus.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.chonus.app.web.rest.TestUtil;

public class TipoUsuarioDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoUsuarioDTO.class);
        TipoUsuarioDTO tipoUsuarioDTO1 = new TipoUsuarioDTO();
        tipoUsuarioDTO1.setId("id1");
        TipoUsuarioDTO tipoUsuarioDTO2 = new TipoUsuarioDTO();
        assertThat(tipoUsuarioDTO1).isNotEqualTo(tipoUsuarioDTO2);
        tipoUsuarioDTO2.setId(tipoUsuarioDTO1.getId());
        assertThat(tipoUsuarioDTO1).isEqualTo(tipoUsuarioDTO2);
        tipoUsuarioDTO2.setId("id2");
        assertThat(tipoUsuarioDTO1).isNotEqualTo(tipoUsuarioDTO2);
        tipoUsuarioDTO1.setId(null);
        assertThat(tipoUsuarioDTO1).isNotEqualTo(tipoUsuarioDTO2);
    }
}
