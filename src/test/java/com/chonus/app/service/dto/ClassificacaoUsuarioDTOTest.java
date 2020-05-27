package com.chonus.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.chonus.app.web.rest.TestUtil;

public class ClassificacaoUsuarioDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClassificacaoUsuarioDTO.class);
        ClassificacaoUsuarioDTO classificacaoUsuarioDTO1 = new ClassificacaoUsuarioDTO();
        classificacaoUsuarioDTO1.setId("id1");
        ClassificacaoUsuarioDTO classificacaoUsuarioDTO2 = new ClassificacaoUsuarioDTO();
        assertThat(classificacaoUsuarioDTO1).isNotEqualTo(classificacaoUsuarioDTO2);
        classificacaoUsuarioDTO2.setId(classificacaoUsuarioDTO1.getId());
        assertThat(classificacaoUsuarioDTO1).isEqualTo(classificacaoUsuarioDTO2);
        classificacaoUsuarioDTO2.setId("id2");
        assertThat(classificacaoUsuarioDTO1).isNotEqualTo(classificacaoUsuarioDTO2);
        classificacaoUsuarioDTO1.setId(null);
        assertThat(classificacaoUsuarioDTO1).isNotEqualTo(classificacaoUsuarioDTO2);
    }
}
